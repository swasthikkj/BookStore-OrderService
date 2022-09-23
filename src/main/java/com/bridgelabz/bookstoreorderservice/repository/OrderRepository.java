package com.bridgelabz.bookstoreorderservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstoreorderservice.model.OrderModel;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long> {

	List<OrderModel> findByUserId(Long userId);

}
