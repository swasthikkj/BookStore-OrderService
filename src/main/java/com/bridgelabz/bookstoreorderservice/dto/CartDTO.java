package com.bridgelabz.bookstoreorderservice.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
public class CartDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long cartId;
	private long userId;
	private long bookId;
	private long quantity;
	private long totalPrice;
}
