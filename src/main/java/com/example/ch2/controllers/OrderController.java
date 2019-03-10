package com.example.ch2.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.ch2.Order;
import com.example.ch2.OrderProps;
import com.example.ch2.User;
import com.example.ch2.database.jpa.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
	
	private OrderRepository orderRep;
	private OrderProps orderProps;
	
	@Autowired
	public OrderController(OrderRepository orderRep,
							OrderProps orderProps) {
		this.orderRep = orderRep;
		this.orderProps = orderProps;
	}

	@GetMapping("/current")
	public String orderForm(@ModelAttribute Order order,
			@AuthenticationPrincipal User user) {
		if (order.getDeliveryName() == null) {
	      order.setDeliveryName(user.getFullname());
		}
		if (order.getDeliveryStreet() == null) {
	      order.setDeliveryStreet(user.getStreet());
		}
		if (order.getDeliveryCity() == null) {
	      order.setDeliveryCity(user.getCity());
		}
	    if (order.getDeliveryState() == null) {
	      order.setDeliveryState(user.getState());
	    }
	    if (order.getDeliveryZip() == null) {
	      order.setDeliveryZip(user.getZip());
	    }
		    
		return "orderForm";
	}
	
	@PostMapping
	public String processOrder(@Valid Order order, Errors errors,
								SessionStatus sessionStatus,
								@AuthenticationPrincipal User user) {
		if (errors.hasErrors()) {
			return "orderForm";
		}
		
		order.setUser(user);
		
		orderRep.save(order);
		sessionStatus.setComplete();
		
		return "redirect:/";
	}
	
	@GetMapping
	public String ordersForUsers(@AuthenticationPrincipal User user,
								Model model) {
		
		Pageable pegable = PageRequest.of(0, orderProps.getPageSize());
		model.addAttribute("orders", orderRep.findByUserOrderByPlacedAtDesc(user, pegable));
		
		return "orderList";
	}
	
}
