package com.Project3.Project3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(
		name = "user17",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = {"username"}),
				@UniqueConstraint(name = "username", columnNames = {"username"})
		}
)
public class Users {
	public Users()
	{
		
	}
	public Users(long userid, String username, String email, String password, String role, String contact_number) {
		//super();
		this.userid = userid;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
		this.contact_number = contact_number;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "USER_SEQ", allocationSize = 1)
	private long userid;
	//@Column(name="name", nullable=false)
	@NotNull
	//@Column(unique=true,nullable = false)
	private String username;
	
	//@Column(name="email", nullable=false,unique=true)
	@Email(message="Check your mail")
	private String email;
	//@Column(name="password", nullable=false)
//	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
	private String password;
	//@Column(name="role", nullable=false)
	@NotNull
	private String role;
	//@Column(name="contact_number", nullable=false)
	@NotNull
	@Pattern(regexp="^\\d{10}$",message="Enter only 10 digits")
	private String contact_number;
	public long getUserId() {
		return userid;
	}
	public void setUserId(long userId) {
		this.userid = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getContact_number() {
		return contact_number;
	}
	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}
	@Override
	public String toString() {
		return "User [userId=" + userid + ", name=" + username + ", email=" + email + ", password=" + password + ", role="
				+ role + ", contact_number=" + contact_number + "]";
	}
	
	

}
