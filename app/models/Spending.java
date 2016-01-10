package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.Required;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Spending extends CashFlow{

	@Required
	private Integer idSpending;
	
	@ManyToOne
	@JsonIgnore
	@Required
	private AdvisedUser user;
	
	
}
