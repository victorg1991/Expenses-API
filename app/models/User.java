package models;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import play.data.validation.Constraints.Required;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
@MappedSuperclass
public class User extends Model {
  
	@Id
	@JsonIgnore
	protected long id;

	@Required
	protected String name;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}