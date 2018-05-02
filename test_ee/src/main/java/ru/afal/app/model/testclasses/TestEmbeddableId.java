package ru.afal.app.model.testclasses;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access( AccessType.PROPERTY )
public class TestEmbeddableId extends EmbeddableId {

	private String property;

	public TestEmbeddableId() {}

	public TestEmbeddableId( String property ) {
		this.property = property;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty( String property ) {
		this.property = property;
	}
}
