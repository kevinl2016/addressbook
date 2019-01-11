package com.example.addressbook.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.addressbook.entity.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
