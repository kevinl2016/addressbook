package com.example.addressbook.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.addressbook.entity.Address;
import com.example.addressbook.exception.ObjectNotFoundException;
import com.example.addressbook.repository.AccountRepository;
import com.example.addressbook.repository.AddressRepository;

@RestController
public class AddressController {
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@GetMapping("/addresses")
	public List<Address> getAllAddresss() {
		List<Address> list = new ArrayList<>();
		addressRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@GetMapping("/addresses/{id}")
	public ResponseEntity<Address> retrieveAddress(@PathVariable Long id) {
		Optional<Address> address =  addressRepository.findById(id);
		if(!address.isPresent())
			throw new ObjectNotFoundException("Address id = " + id);
		return new ResponseEntity<Address>(address.get(), HttpStatus.OK);
	}

	@GetMapping("/accounts/{accountId}/address")
	public List<Address> retrieveAccountAddress(@PathVariable Long accountId) {
		return addressRepository.findByAccountId(accountId);
	}

	@GetMapping("/accounts/{accountId}/address/{id}")
	public ResponseEntity<Address> retrieveAddress(@PathVariable Long accountId, @PathVariable Long id) {
		Optional<Address> address =  addressRepository.findById(id);
		if(!address.isPresent())
			throw new ObjectNotFoundException("Address id = " + id);
		return new ResponseEntity<Address>(address.get(), HttpStatus.OK);
		
	}

	@DeleteMapping("/accounts/{accountId}/address/{id}")
	public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
		addressRepository.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/accounts/{accountId}/address")
	public Address createAddress(@PathVariable Long accountId,
			@RequestBody Address address) {
		return accountRepository.findById(accountId).map(account -> {
			address.setAccount(account);
			return addressRepository.save(address);
		}).orElseThrow(() -> new ResourceNotFoundException("AccountId " + accountId + " not found"));
	}
	
	@PutMapping("/accounts/{accountId}/address/{id}")
	public Address updateAddress(@PathVariable Long accountId,
			@RequestBody Address address,
			@PathVariable long id) {
		return accountRepository.findById(accountId).map(account -> {
			address.setAccount(account);
			return addressRepository.save(address);
		}).orElseThrow(() -> new ResourceNotFoundException("AccountId " + accountId + " not found"));
	}
}
