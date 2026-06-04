package com.example.UserManagement.Security;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.UserManagement.Entity.Role;
import com.example.UserManagement.Entity.UserEntity;
import com.example.UserManagement.Repo.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(
            String email
    ) throws UsernameNotFoundException {

        UserEntity user =

                userRepo.findByEmail(
                        email
                )

                .orElseThrow(() ->

                        new UsernameNotFoundException(
                                "User not found"
                        ));

        return new org.springframework.security.core.userdetails.User(

                user.getEmail(),

                user.getPassword(),

                user.getRoles()

                        .stream()

                        .map(Role::getName)

                        .map(SimpleGrantedAuthority::new)

                        .collect(
                                Collectors.toList()
                        )
        );
    }
}
	
	

	

