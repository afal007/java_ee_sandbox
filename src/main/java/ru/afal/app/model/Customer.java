package ru.afal.app.model;


import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

import static ru.afal.app.model.Customer.FIND_ALL;

@Entity
@NamedQuery( name = FIND_ALL, query = "SELECT c FROM Customer c")
public class Customer {
	public final static String FIND_ALL = "Customer.findAll";

	@Id
	@GeneratedValue( strategy = GenerationType.TABLE )
	private Long id;

	private String name;

	/**
	 * Ограничения, заданные в {@link Column}, проверяются на уровне БД, то есть прямым образом влияют на схему данных
	 */
	@Column( unique = true, nullable = false, length = 50 )
	private String login;

	@Column( nullable = false )
	private String password;

	private BigDecimal balance;

	/**
	 * Используем аннотацию {@link Basic} чтобы определить базовые параметры для сохранения данных.
	 * {@link FetchType}    - определяет как доставать данные из БД - жадно или лениво
	 * optional             - дает возможность определить опциональность данного поля
 	 */
	@OneToMany( targetEntity = User.class, fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private List<User> userList;

	/**
	 * Предположим, что нам не нужно хранить какую-то информацию в базе, но при этом она будет полезна в рамках классов.
	 * Используем аннотацию {@link Transient}.
	 */
	@Transient
	private byte[] loginPassEncoded;

	@Transient
	private static ThreadLocal<Base64.Encoder> encoder = ThreadLocal.withInitial( Base64::getEncoder );

	public Customer() {}

	public Customer( String name, String login, String password, BigDecimal balance ) {
		this.name               = name;
		this.login              = login;
		this.password           = password;
		this.balance            = balance;
		this.loginPassEncoded   = encoder.get().encode( ( login + password ).getBytes() );
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

	@Override
	public boolean equals( Object o ) {
		if ( this == o ) return true;
		if ( o == null || getClass() != o.getClass() ) return false;
		Customer customer = ( Customer ) o;
		return Objects.equals( id, customer.id ) &&
				       Objects.equals( name, customer.name ) &&
				       Objects.equals( login, customer.login ) &&
				       Objects.equals( password, customer.password );
	}

	@Override
	public int hashCode() {

		return Objects.hash( id, name, login, password );
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

	public String getLogin() {
		return login;
	}

	public void setLogin( String login ) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword( String password ) {
		this.password = password;
	}

	public byte[] getLoginPassEncoded() {
		return loginPassEncoded;
	}

	public void setLoginPassEncoded( byte[] loginPassEncoded ) {
		this.loginPassEncoded = loginPassEncoded;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList( List<User> userList ) {
		this.userList = userList;
	}
}
