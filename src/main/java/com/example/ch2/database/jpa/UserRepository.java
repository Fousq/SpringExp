package com.example.ch2.database.jpa;

import org.springframework.data.repository.CrudRepository;

import com.example.ch2.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	User findByUsername(String username);
	
}
