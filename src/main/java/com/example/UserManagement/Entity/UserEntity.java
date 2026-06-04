package com.example.UserManagement.Entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="users")
public class UserEntity {
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private long id;
	
	@NotBlank
	private String name;
	
	@Email
	@Column(unique=true)
	private String email;
	
	@Pattern
	(regexp= "^(?=.*[A-Z])(?=.*\\d).{8,}$",
    message =
    "Password must contain uppercase and number")
	
	@JsonProperty(
            access =
            JsonProperty.Access.WRITE_ONLY
    )
	
	private String password ;
	
	@ManyToMany(fetch=FetchType.EAGER)
	
	@JoinTable(
			name="user_roles",
	 
			joinColumns=@JoinColumn(name="user_id"),
	 
	 inverseJoinColumns=@JoinColumn(name="role_id")
			
			
			)
	
	private Set<Role> roles;

	public UserEntity() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
	
	
	
	
	
	
	

}
