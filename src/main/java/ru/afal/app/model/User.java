package ru.afal.app.model;


import ru.afal.app.model.enums.UserType;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Сущность заводится кокретным клиентом ({@link Customer}) и в отрыве от него существовать не может.
 * Для того, чтобы это подчеркнуть, используется аннотация {@link Embeddable}.
 * Используется accessType - propertyAccess. То есть аннотации, связанные с полями, навешиваются на аксессоры.
 * По умолчанию используется accessType сущности, в которую встраивается {@link Embeddable}. Поэтому
 * рекомендуется явно указывать {@link Access}.
 */
@Embeddable
@Access( value = AccessType.PROPERTY )
public class User{

	private String name;
	private String login;
	private String password;

	/**
	 * {@link Enumerated} позволяет определять что хранить в базе - порядковый номер элемента перечисления
	 * или точное название (String)
	 */
	@Enumerated( value = EnumType.STRING )
	private UserType userType;

	public User() {}

	public User( String name, String login, String password, UserType userType ) {
		this.name = name;
		this.login = login;
		this.password = password;
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "User{" +
				       "name='" + name + '\'' +
				       ", login='" + login + '\'' +
				       ", password='" + password + '\'' +
				       '}';
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	@Column( unique = true, nullable = false )
	public String getLogin() {
		return login;
	}

	public void setLogin( String login ) {
		this.login = login;
	}

	@Column( nullable = false )
	public String getPassword() {
		return password;
	}

	public void setPassword( String password ) {
		this.password = password;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType( UserType userType ) {
		this.userType = userType;
	}
}
