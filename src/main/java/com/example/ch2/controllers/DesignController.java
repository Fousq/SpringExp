package com.example.ch2.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.ch2.Ingredient;
import com.example.ch2.Ingredient.Type;
import com.example.ch2.Order;
import com.example.ch2.Taco;
import com.example.ch2.User;
import com.example.ch2.database.jpa.IngredientRepository;
import com.example.ch2.database.jpa.TacoRepository;
import com.example.ch2.database.jpa.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignController {
	
	private final IngredientRepository ingredientRep;
	
	private TacoRepository designRep;
	private UserRepository userRepo;
	
	@Autowired
	public DesignController(IngredientRepository ingredientRep,
			TacoRepository designRep, UserRepository userRepo) {
		this.ingredientRep = ingredientRep;
		this.designRep = designRep;
		this.userRepo = userRepo;
	}
	
	@GetMapping
	public String showDesignForm(Model model, Principal principal) {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRep.findAll().forEach(i -> ingredients.add(i));
		
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			log.info("Type " + type.toString());
			log.info("Filter " + filterByType(ingredients, type));
			model.addAttribute(type.toString().toLowerCase(), 
					filterByType(ingredients, type));
		}
		
		String username = principal.getName();
		User user = userRepo.findByUsername(username);
		model.addAttribute("user", user);
		
		return "designForm";
	}
	
	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}
	
	@ModelAttribute(name = "design")
	public Taco taco() {
		return new Taco();
	}
	
	@PostMapping
	public String processDesign(
			@Valid Taco design,
			Errors errors, @ModelAttribute Order order) {
		if (errors.hasErrors()) {
			return "designForm";
		}
		
		Taco saved = designRep.save(design);
		order.addDesign(saved);
		
		return "redirect:/orders/current";
	}
	
	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients
				.stream()
				.filter(x -> x.getType().equals(type))
				.collect(Collectors.toList());
	}
	
}
