package com.example.UserManagement.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.UserManagement.Entity.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {

	
	Optional<Role>
	findByName(String name);
}
