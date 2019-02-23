package com.example.ch2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.ch2.Ingredient;
import com.example.ch2.database.IngredientRepository;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient>{
	
	private IngredientRepository ingredientRep;
	
	@Autowired
	public IngredientByIdConverter(IngredientRepository ingredientRep ) {
		this.ingredientRep = ingredientRep;
	}
	
	@Override
	public Ingredient convert(String id) {
		return ingredientRep.findById(id);
	}
	
}
