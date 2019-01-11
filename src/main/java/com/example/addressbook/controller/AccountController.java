package com.example.addressbook.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.addressbook.entity.Account;
import com.example.addressbook.exception.ObjectNotFoundException;
import com.example.addressbook.repository.AccountRepository;

@RestController
public class AccountController {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@GetMapping("/accounts")
	public List<Account> getAllAccounts() {
		List<Account> list = new ArrayList<>();
		accountRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@GetMapping("/accounts/{id}")
	public ResponseEntity<Account> retrieveAccount(@PathVariable Long id) {
		Optional<Account> account = accountRepository.findById(id);
		if(!account.isPresent())
			throw new ObjectNotFoundException("Account id = " + id);

		return new ResponseEntity<Account>(account.get(), HttpStatus.OK);
	}

	@DeleteMapping("/accounts/{id}")
	public ResponseEntity<Void> deleteAccount(@PathVariable long id) {
		accountRepository.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/accounts")
	public ResponseEntity<Account> createAccount(@RequestBody Account account) {
		Account savedAccount = accountRepository.save(account);
		return new ResponseEntity<Account>(savedAccount, HttpStatus.OK);
	}
	
	@PutMapping("/accounts/{id}")
	public ResponseEntity<Account> updateAccount(@RequestBody Account account, @PathVariable long id) {
		accountRepository.save(account);
		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}
	
}
