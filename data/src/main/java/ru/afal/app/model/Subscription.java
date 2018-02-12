package ru.afal.app.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@Access( AccessType.PROPERTY )
public class Subscription extends Plan{

	Date startDate;
	Date endDate;

	public Subscription() {
	}

	public Subscription( Date startDate, Date endDate ) {
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Subscription( Plan plan, Date startDate, Date endDate ) {
		super( plan.getFee(), plan.getName(), plan.getDescription() );
		this.startDate = startDate;
		this.endDate = endDate;
	}

	@Column( name = "STARTDATE")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate( Date startDate ) {
		this.startDate = startDate;
	}

	@Column( name = "ENDDATE")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate( Date endDate ) {
		this.endDate = endDate;
	}
}
