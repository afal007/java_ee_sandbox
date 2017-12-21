package ru.afal.app.model;

import com.sun.istack.internal.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Customer {
	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@NotNull
	private String login;

	@NotNull
	private String password;

	private BigDecimal balance;

	public Customer() {}

	public Customer( String name, String login, String password, BigDecimal balance ) {
		this.name = name;
		this.login = login;
		this.password = password;
		this.balance = balance;
	}

	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance( BigDecimal balance ) {
		this.balance = balance;
	}
}
