package com.bridgelabz.bookstoreorderservice.service;

import java.util.List;

import com.bridgelabz.bookstoreorderservice.model.OrderModel;

/**
 * Purpose:Interface for order service
 * @version 4.15.1.RELEASE
 * @author Swasthik KJ
 */

public interface IOrderService {

	OrderModel placeOrder(Long cartId, Long addressId, String token);

	OrderModel cancelOrder(Long orderId, String token);

	List<OrderModel> getAllOrders(String token);

	List<OrderModel> getUserOrders(String token);

}
