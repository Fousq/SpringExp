package com.example.ch2.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ch2.DiscountProps;

@Controller
@RequestMapping("/discounts")
public class DiscountController {

	private DiscountProps codesProps;
	
	public DiscountController(DiscountProps codesProps) {
		this.codesProps = codesProps;
	}
	
	@GetMapping
	public String showDiscountCodes(Model model) {
		
		Map<String, Integer> codes = codesProps.getCodes();
		System.out.println("Discount controller: " + codes.toString());
		model.addAttribute("codes", codes);
		
		return "discountList";
	}
	
}
