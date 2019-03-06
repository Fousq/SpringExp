package com.example.ch2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
@Entity
@Table(name="Taco_Order")
public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Date placedAt;
	
	@NotBlank(message="Name is requiered")
	private String deliveryName;
	
	@NotBlank(message="Street is requiered")
	private String deliveryStreet;
	
	@NotBlank(message="City is requiered")
	private String deliveryCity;
	
	@NotBlank(message="State is requiered")
	private String deliveryState;
	
	@NotBlank(message="Zip is requiered")
	private String deliveryZip;
	
	@CreditCardNumber(message="Not Valid")
	private String ccNumber;
	
	@Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
			message="Must be formatted MM/YY")
	private String ccExpiration;
	
	@Digits(integer=3, fraction=0, message="Not Valid CVV")
	private String ccCVV;
	
	@ManyToMany(targetEntity=Taco.class)
	private List<Taco> tacos = new ArrayList<>();
	
	@ManyToOne
	private User user;
	
	public void addDesign(Taco design) {
		this.tacos.add(design);
	}
	
	@PrePersist
	void placedAt() {
		placedAt = new Date();
	}
	
}
