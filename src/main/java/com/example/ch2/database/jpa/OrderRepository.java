package com.example.ch2.database.jpa;

import org.springframework.data.repository.CrudRepository;

import com.example.ch2.Order;

public interface OrderRepository 
		extends CrudRepository<Order, Long>{

}
