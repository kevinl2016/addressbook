package com.example.addressbook.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
 
 
@Entity
@Table(name="address")
@TableGenerator(name="addr", initialValue=10, allocationSize=50)
public class Address {
 
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="addr")
	private Long id;
	private String name;
	private String address;
 
	@ManyToOne
	@JoinColumn(name = "account_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Account account;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
