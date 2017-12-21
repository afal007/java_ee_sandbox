package ru.afal.app.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Customer {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;

	private String name;

	@Column( unique = true, nullable = false )
	private String login;

	@Column( nullable = false )
	private String password;

	private BigDecimal balance;

	public Customer() {}

	public Customer( String name, String login, String password, BigDecimal balance ) {
		this.name = name;
		this.login = login;
		this.password = password;
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Customer{" +
				       "id=" + id +
				       ", name='" + name + '\'' +
				       ", login='" + login + '\'' +
				       ", password='" + password + '\'' +
				       ", balance=" + balance +
				       '}';
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
