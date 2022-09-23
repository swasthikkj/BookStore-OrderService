package com.bridgelabz.bookstoreorderservice.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.bookstoreorderservice.exception.OrderNotFoundException;
import com.bridgelabz.bookstoreorderservice.model.AddressModel;
import com.bridgelabz.bookstoreorderservice.model.OrderModel;
import com.bridgelabz.bookstoreorderservice.repository.AddressRepository;
import com.bridgelabz.bookstoreorderservice.repository.OrderRepository;
import com.bridgelabz.bookstoreorderservice.util.BookResponse;
import com.bridgelabz.bookstoreorderservice.util.CartResponse;
import com.bridgelabz.bookstoreorderservice.util.TokenUtil;
import com.bridgelabz.bookstoreorderservice.util.UserResponse;

/**
 * Purpose:Service for order
 * @version 4.15.1.RELEASE
 * @author Swasthik KJ
 */

@Service
public class OrderService implements IOrderService {
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	TokenUtil tokenUtil;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	AddressRepository addressRepository;
	
	/**
	 *  Purpose:To place order
	 */
	
	@Override
	public OrderModel placeOrder(Long cartId, Long addressId, String token) {
		Long userId = tokenUtil.decodeToken(token);
		UserResponse isUserPresent = restTemplate.getForObject("http://BS-UserService:8090/userService/verifyToken/" + userId, UserResponse.class);
		if (isUserPresent.getErrorcode() == 200) {
			CartResponse isCartPresent = restTemplate.getForObject("http://BS-CartService:8092/cartModel/getCart/" + cartId, CartResponse.class);
			if (isUserPresent.getErrorcode() == 200) {
				if (isUserPresent.getToken().getUserId() == isCartPresent.getToken().getUserId()) {
					Optional<AddressModel> isAddressPresent = addressRepository.findById(addressId);
					OrderModel order = new OrderModel();
					order.setOrderDate(LocalDate.now());
					order.setBookId(isCartPresent.getToken().getBookId());
					order.setPrice(isCartPresent.getToken().getTotalPrice());
					order.setQuantity(isCartPresent.getToken().getQuantity());
					order.setUserId(isCartPresent.getToken().getUserId());
					order.setCartId(cartId);
					order.setCancel(false);
					if (isAddressPresent.isPresent()) {
						if (isAddressPresent.get().getUserId() == isUserPresent.getToken().getUserId()) {
							order.setAddress(isAddressPresent.get());
						}
						else {
							throw new OrderNotFoundException(500, "Address UserId and User Id Not Match");
						}
					}
					else {
						throw new OrderNotFoundException(500, "Address Not Found This User Id");
					}
					orderRepository.save(order);
					String body = "Order placed with Order Id is :" +order.getOrderId();
					String subject = "Order Successfully Placed";
					mailService.send(isUserPresent.getToken().getEmailId(), subject, body);
					CartResponse isPresent = restTemplate.getForObject("http://BS-CartService:8092/cartModel/delete/" + order.getCartId(), CartResponse.class);
					return order;
				}
				throw new OrderNotFoundException(500, "Cart not Found");			
			}
			throw new OrderNotFoundException(500, "Invalid token");
		}
		return null;
	}
	
	/**
	 *  Purpose:To cancel order
	 */
	
	@Override
	public OrderModel cancelOrder(Long orderId, String token) {
		Long userId = tokenUtil.decodeToken(token);
		UserResponse isUserPresent = restTemplate.getForObject("http://BS-UserService:8090/userService/verifyToken/" + userId, UserResponse.class);
		if (isUserPresent.getErrorcode() == 200) {
			Optional<OrderModel> isOrderPresent = orderRepository.findById(orderId);
			if (isOrderPresent.isPresent()) {
				isOrderPresent.get().setCancel(true);
				BookResponse isBookIdPresent = restTemplate.getForObject("http://BS-BookModel:8091/bookService/updateQuantity/" + 
						isOrderPresent.get().getBookId() +"/"+ isOrderPresent.get().getQuantity(), BookResponse.class);
				orderRepository.delete(isOrderPresent.get());
				return isOrderPresent.get();
			}
			throw new OrderNotFoundException(500, "Cart not Found");
		}
		throw new OrderNotFoundException(500, "Invalid token");
	}
	
	/**
	 *  Purpose:To fetch all orders
	 */
	
	@Override
	public List<OrderModel> getAllOrders(String token) {
		Long userId = tokenUtil.decodeToken(token);
		UserResponse isUserPresent = restTemplate.getForObject("http://BS-UserService:8090/userService/verifyToken/" + userId, UserResponse.class);
		if (isUserPresent.getErrorcode() == 200) {
			List<OrderModel> isOrdersPresent = orderRepository.findAll();
			if (isOrdersPresent.size()>0) {
				return isOrdersPresent;
			}
			throw new OrderNotFoundException(500, "No Orders Found");
		}
		throw new OrderNotFoundException(500, "Invalid User");
	}
	
	/**
	 *  Purpose:To fetch all user orders
	 */
	
	@Override
	public List<OrderModel> getUserOrders(String token) {
		Long userId = tokenUtil.decodeToken(token);
		UserResponse isUserPresent = restTemplate.getForObject("http://BS-UserService:8090/userService/verifyToken/" + userId, UserResponse.class);
		if (isUserPresent.getErrorcode() == 200) {
			List<OrderModel> isOrdersPresent = orderRepository.findByUserId(userId);
			if (isOrdersPresent.size()>0) {
				return isOrdersPresent;
			}
		}
		throw new OrderNotFoundException(500, "Invalid User");
	}
}
