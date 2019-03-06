package com.example.ch2.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.ch2.User;

import lombok.Data;

@Data
public class RegistrationForm {
	
	private String username;
	private String password;
	private String fullname;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String phoneNumber;
	
	public User toUser(PasswordEncoder passwordEncoder) {
		return new User(username, passwordEncoder.encode(password),
				fullname, street, city, state, zip, phoneNumber);
	}
	
}
