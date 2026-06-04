package com.example.UserManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.UserManagement.Entity.UserEntity;
import com.example.UserManagement.Security.JwtUtil;
import com.example.UserManagement.Service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

   

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	

   
	
	@PostMapping("/register") 
	public UserEntity registerUser(
			@RequestBody UserEntity user 
			) {
		return userService.registerUser(user);
	}
	
	@PostMapping("/login")
	public String login(
			@RequestParam String email,
			
			@RequestParam String password)
	{

        authManager.authenticate(

                new UsernamePasswordAuthenticationToken(

                        email,

                        password
                )
        );

        return jwtUtil.generateToken(
                email
        );
    }
}
