package com.example.ch2.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.ch2.Ingredient;
import com.example.ch2.database.jpa.IngredientRepository;

import org.springframework.core.convert.converter.Converter;

@Component
public class IngredientByIdConverter 
		implements Converter<String, Ingredient>{
	
	private IngredientRepository ingredientRepo;
	
	@Autowired
	public IngredientByIdConverter(IngredientRepository IngredientRepo) {
		this.ingredientRepo = ingredientRepo;
	}
	
	@Override
	public Ingredient convert(String id) {
		Optional<Ingredient> optionalIngredient = ingredientRepo.findById(id);
		System.out.println("IngredientRepo: " + (optionalIngredient.isPresent() ? 
				optionalIngredient.get() : null));
		return optionalIngredient.isPresent() ? 
				optionalIngredient.get() : null;
	}
	
}
