package com.bridgelabz.bookstoreorderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.bookstoreorderservice.model.AddressModel;

public interface AddressRepository extends JpaRepository<AddressModel, Long> {

}
