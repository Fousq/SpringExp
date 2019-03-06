package com.example.ch2.database;

import com.example.ch2.Order;

public interface OrderRepository {
	public Order save(Order order);
}
