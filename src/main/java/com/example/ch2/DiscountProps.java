package com.example.ch2;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix="taco.discount")
public class DiscountProps {
	
	private Map<String, Integer> codes = new HashMap<>();
	
}
