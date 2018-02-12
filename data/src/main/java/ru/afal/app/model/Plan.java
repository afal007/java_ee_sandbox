package ru.afal.app.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Access( AccessType.PROPERTY )
public class Plan {

	private Long id;
	private BigDecimal fee;
	private String name;
	private String description;


	public Plan() {}

	public Plan( BigDecimal fee, String name, String description ) {
		this.fee = fee;
		this.name = name;
		this.description = description;
	}

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	@Column( name = "FEE" )
	public BigDecimal getFee() {
		return fee;
	}

	public void setFee( BigDecimal fee ) {
		this.fee = fee;
	}

	@Column( name = "NAME")
	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	@Column( name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription( String description ) {
		this.description = description;
	}
}
