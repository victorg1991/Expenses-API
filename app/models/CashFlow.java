package models;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import play.data.validation.Constraints.Required;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;

@MappedSuperclass
public class CashFlow extends Model{
	
	@Id
	protected long id;
	
	@Required
	protected float quantity;
	
	@Required
	protected String description;
	
    @CreatedTimestamp
    Timestamp creationDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date geteDate() {
		return creationDate;
	}

	public void seteDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

}
