package com.example.addressbook.repository;

import com.example.addressbook.entity.Address;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
	List<Address> findByAccountId(Long accountId);
}
