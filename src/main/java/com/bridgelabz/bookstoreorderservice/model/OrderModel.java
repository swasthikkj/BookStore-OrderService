package com.bridgelabz.bookstoreorderservice.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Purpose:Model for order service
 * @version 4.15.1.RELEASE
 * @author Swasthik KJ
 */

@Entity
@Table(name = "OrderService")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long orderId;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate orderDate;
	private long price;
	private long quantity;
	@OneToOne
	private AddressModel address;
	private long userId;
	private long bookId;
	private long cartId;
	private boolean cancel = false;
	
}
