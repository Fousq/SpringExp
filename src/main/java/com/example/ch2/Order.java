package com.example.ch2;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
public class Order {
	
	@NotBlank(message="Name is requiered")
	private String name;
	
	@NotBlank(message="Street is requiered")
	private String street;
	
	@NotBlank(message="City is requiered")
	private String city;
	
	@NotBlank(message="State is requiered")
	private String state;
	
	@NotBlank(message="Zip is requiered")
	private String zip;
	
	@CreditCardNumber(message="Not Valid")
	private String ccNumber;
	
	@Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
			message="Must be formatted MM/YY")
	private String ccExpiration;
	
	@Digits(integer=3, fraction=0, message="Not Valid CVV")
	private String ccCVV;
	
}
