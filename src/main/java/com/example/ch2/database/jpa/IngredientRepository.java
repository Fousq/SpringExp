package com.example.ch2.database.jpa;

import org.springframework.data.repository.CrudRepository;

import com.example.ch2.Ingredient;

public interface IngredientRepository 
		extends CrudRepository<Ingredient, String>{

}
