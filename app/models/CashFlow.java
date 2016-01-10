package models;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import play.data.format.Formats;
import play.data.validation.Constraints.Required;

@MappedSuperclass
public class CashFlow {
	
	@Id
	protected long id;
	
	@Required
	protected float quantity;
	
	@Required
	protected String description;
	
	@Required
	@Formats.DateTime(pattern = "dd/MM/yyyy")
	protected Date eDate;

}
