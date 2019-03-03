package com.example.ch2.database.jpa;

import org.springframework.data.repository.CrudRepository;

import com.example.ch2.Taco;

public interface TacoRepository 
		extends CrudRepository<Taco, Long>{

}
