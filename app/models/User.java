package models;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import play.data.validation.Constraints.Required;
import validators.Email;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public class User extends Model {
  
	@Id
	@JsonIgnore
	protected long id;

	@Required
	protected String name;
	
	@Required
	protected String dni;
	
	@Required
	protected String city;
	
	@Email
	protected String email;
	
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}