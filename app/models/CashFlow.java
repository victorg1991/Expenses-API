package models;

import java.sql.Timestamp;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import play.data.validation.Constraints.Required;
import serializer.JsonDateSerializer;
import serializer.JsonFloatSerializer;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@MappedSuperclass
public class CashFlow extends Model{
	
	@Id
	@JsonIgnore
	protected long id;
	
	@Required
	//@JsonSerialize(using = JsonFloatSerializer.class)
	protected float quantity;
	
	@Required
	protected String description;
	
    @CreatedTimestamp
    @JsonSerialize(using = JsonDateSerializer.class)
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
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

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
}
