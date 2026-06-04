package com.example.UserManagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.UserManagement.Entity.Task;
import com.example.UserManagement.Service.TaskService;

@RestController
@RequestMapping("/manager")

public class ManagerController {
	
	@Autowired
	private TaskService taskService;
	
	@PostMapping("/tasks/{userId}")
	public Task assignTask(
	
			  @PathVariable Long userId,

	            @RequestBody Task task
	    ) {

	        return taskService.assignTask(
	                userId,
	                task
	        );
	    }

	    @GetMapping("/tasks")
	    public List<Task> getTasks() {

	        return taskService.getTasks();
	    }
	}


