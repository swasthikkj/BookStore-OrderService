package com.bridgelabz.bookstoreorderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstoreorderservice.model.OrderModel;
import com.bridgelabz.bookstoreorderservice.service.IOrderService;
import com.bridgelabz.bookstoreorderservice.util.OrderResponse;

/**
 * Purpose:Controller for order service
 * @version 4.15.1.RELEASE
 * @author Swasthik KJ
 */

@RestController
@RequestMapping("/orderService")
public class OrderController {
	@Autowired
	IOrderService orderService;
	
	/*
     *@Purpose : To place order
     *@Param : cartId ,token and addressId
     */
	
	@PostMapping("/placeOrder")
	public ResponseEntity<OrderResponse> placeOrder(@RequestParam Long cartId, @RequestParam Long addressId, @RequestHeader String token){
		OrderModel order = orderService.placeOrder(cartId, addressId, token);
		OrderResponse orderResponse = new OrderResponse(200, "Placed order Successfully", order);
		return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.OK);
	}
	
	/*
     *@Purpose :To Cancel order
     *@Param : orderId ,token 
     */
	
	@DeleteMapping("/cancelOrder/{orderId}")
	public ResponseEntity<OrderResponse> cancelOrder(@PathVariable Long orderId,@RequestHeader String token){
		OrderModel order = orderService.cancelOrder(orderId, token);
		OrderResponse orderResponse = new OrderResponse(200, "Order cancelled", order);
		return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.OK);
	}
	
	/*
     *@Purpose : To fetch all orders
     *@Param : token
     */
	
	@GetMapping("/getAllOrders")
	public ResponseEntity<OrderResponse> getAllOrders(@RequestHeader String token){
		List<OrderModel> order = orderService.getAllOrders(token);
		OrderResponse orderResponse = new OrderResponse(200, "Fetched All Orders", order);
		return new ResponseEntity<OrderResponse>(orderResponse,HttpStatus.OK);
	}
	
	/*
     *@Purpose : To fetch all orders of user
     *@Param : token
     */
	
	@GetMapping("/getUserOrders")
	public ResponseEntity<OrderResponse> getUserOrders(@RequestHeader String token){
		List<OrderModel> order = orderService.getUserOrders(token);
		OrderResponse orderResponse = new OrderResponse(200, "Fetched all user orders", order);
		return new ResponseEntity<OrderResponse>(orderResponse,HttpStatus.OK);
	}
}
