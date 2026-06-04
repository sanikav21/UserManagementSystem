package com.example.UserManagement.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.UserManagement.Entity.Task;

public interface TaskRepo extends JpaRepository<Task, Long> {


	
}
