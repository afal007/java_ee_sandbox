package ru.afal.app.model.testclasses;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Access( value = AccessType.PROPERTY )
public abstract class EmbeddableId {
	private long id;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	public long getId() {
		return id;
	}

	public void setId( long id ) {
		this.id = id;
	}
}
