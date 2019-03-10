package com.example.ch2.database.jpa;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.example.ch2.Order;
import com.example.ch2.User;

public interface OrderRepository 
		extends CrudRepository<Order, Long>{
	List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
