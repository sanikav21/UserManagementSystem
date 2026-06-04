package com.example.UserManagement.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.UserManagement.Entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Long>

{
  Optional<UserEntity>
  findByEmail(String email);
  
  boolean existsByEmail(String email);

}
