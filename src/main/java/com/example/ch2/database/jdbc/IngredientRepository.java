package com.example.ch2.database;

import com.example.ch2.Ingredient;

public interface IngredientRepository {
	public Iterable<Ingredient> findAll();
	public Ingredient findById(String id);
	public Ingredient save(Ingredient ingredient);
}
