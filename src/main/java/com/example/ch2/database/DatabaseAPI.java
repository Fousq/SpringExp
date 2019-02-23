package com.example.ch2.database;

import com.example.ch2.Ingredient;

public interface DatabaseAPI {
	public Iterable<Ingredient> findAll();
	public Ingredient findOne(String id);
	public void save(Ingredient ingredient);
}
