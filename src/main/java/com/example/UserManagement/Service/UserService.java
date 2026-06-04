package com.example.UserManagement.Service;



import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.UserManagement.Entity.Role;
import com.example.UserManagement.Entity.UserEntity;
import com.example.UserManagement.Repo.RoleRepo;
import com.example.UserManagement.Repo.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	public UserEntity registerUser(
            UserEntity user
    ) {

        if(userRepo.existsByEmail(
                user.getEmail())) {

            throw new RuntimeException(
                    "Email already exists"
            );
        }

        user.setPassword(

                encoder.encode(
                        user.getPassword()
                )
        );
        
        Role userRole =

                roleRepo.findByName(
                        "USER"
                )

                .orElseThrow(() ->

                        new RuntimeException(
                                "Role not found"
                        ));

        user.setRoles(

                Set.of(
                        userRole
                )
        );


        return userRepo.save(
                user
        );
    }

    public List<UserEntity> getUsers() {

        return userRepo.findAll();
    }

    public UserEntity getUserById(
            Long id
    ) {

        return userRepo.findById(id)

                .orElseThrow(() ->

                        new RuntimeException(
                                "User not found"
                        ));
    }
    
    public UserEntity updateUser(
            Long id,
            UserEntity updatedUser
    ) {

        UserEntity existingUser =
                userRepo.findById(id)

                .orElseThrow(() ->

                        new RuntimeException(
                                "User not found"
                        ));

        existingUser.setName(
                updatedUser.getName()
        );

        existingUser.setEmail(
                updatedUser.getEmail()
        );

        if (updatedUser.getPassword() != null
                && !updatedUser.getPassword().isEmpty()) {

            existingUser.setPassword(

                    encoder.encode(
                            updatedUser.getPassword()
                    )
            );
        }

        return userRepo.save(
                existingUser
        );
    }

    public void deleteUser(
            Long id
    ) {

        if (!userRepo.existsById(
                id
        )) {

            throw new RuntimeException(
                    "User not found"
            );
        }

        userRepo.deleteById(
                id
        );
    }

}
	


