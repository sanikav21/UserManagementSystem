package com.example.UserManagement.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.UserManagement.Entity.Role;
import com.example.UserManagement.Repo.RoleRepo;

@Configuration
public class DataLoader {

	 @Bean
	    CommandLineRunner init(
	            RoleRepo roleRepo
	    ) {

	        return args -> {

	            if(roleRepo.count()==0){

	                Role admin = new Role();
	                admin.setName("ADMIN");

	                Role manager = new Role();
	                manager.setName("MANAGER");

	                Role user = new Role();
	                user.setName("USER");

	                roleRepo.save(admin);
	                roleRepo.save(manager);
	                roleRepo.save(user);
	            }
	        };
	    }
	}
