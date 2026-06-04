package com.example.UserManagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.UserManagement.Entity.UserEntity;
import com.example.UserManagement.Service.UserService;

@RestController
@RequestMapping("/admin")

public class AdminController {

	 @Autowired
	 private UserService userService;
	 
	 @GetMapping("/users")
	 public List<UserEntity> getUsers(){
		 return userService.getUsers();
	 }
	 
	 @GetMapping("/users/{id}")
	 public UserEntity getUsers(
			 @PathVariable Long id
			 ) {
		 return userService.getUserById(id);
	 }
	 
	 @PutMapping("/users/{id}")
	 public UserEntity updateUsers(
			 @PathVariable Long id ,
			 
			 @RequestBody UserEntity user
			 ) {
		  return userService.updateUser(id,user);
	 }
	 
	 @DeleteMapping("/isers/{id}")
	 public String deleteUser(@PathVariable Long id) {
		  userService.deleteUser(id);
		  return "User Deleted!";
	 }
	

}
