package com.example.UserManagement.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.UserManagement.Entity.Task;
import com.example.UserManagement.Entity.UserEntity;
import com.example.UserManagement.Repo.TaskRepo;
import com.example.UserManagement.Repo.UserRepo;

@Service
public class TaskService {

	@Autowired
    private TaskRepo taskRepo;

    @Autowired
    private UserRepo userRepo;

    public Task assignTask(
            Long userId,
            Task task
    ) {

        UserEntity user =
                userRepo.findById(userId)

                .orElseThrow(() ->

                        new IllegalArgumentException(
                                "User not found"
                        ));

        task.setAssignedUser(
                user
        );

        return taskRepo.save(
                task
        );
    }

    public List<Task> getTasks() {

        return taskRepo.findAll();
    }
}


