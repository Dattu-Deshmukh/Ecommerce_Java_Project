package com.example.Entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="Ecommerce")
public class Details {
	private String id;
	private String Name;
	private String Email;
	private String password;
	private String confirmPassword;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	@Override
	public String toString() {
		return "details [id=" + id + ", Name=" + Name + ", Email=" + Email + ", password=" + password + "]"+ ", confirmPassword=" + confirmPassword
				;
	}
	public Details() {
		super();
	}
	
	
}
