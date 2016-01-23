package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import play.data.validation.Constraints.Required;
import serializer.JsonAdvisedUserSerializer;
import serializer.JsonConsultantSerializer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class AdvisedUser extends User{

	@Required
	private Integer idAdvisedUser;
	
	@ManyToOne
	@JsonProperty("idConsultant")
	@JsonSerialize(using = JsonConsultantSerializer.class)
	private Consultant consultant;
	
	@OneToMany(mappedBy="user")
	@Valid
	private List<Spending> spendings = new ArrayList<Spending>();

	public static final Find<Long, AdvisedUser> find = new Find<Long, AdvisedUser>(){};
	
	public static boolean existsAdvisedUserWithId(Integer id){
		
		if(AdvisedUser.findAdvisedUserWithId(id) != null){
			return true;
		}
		return false;
	}
	
	public static AdvisedUser findAdvisedUserWithId(int id){
		
		return find.where().eq("idAdvisedUser", id).findUnique();	
	}
	
	public static List<AdvisedUser> findAllAdvisedUsers(){
		return find.findList();
	}
	

	public boolean updateAdvisedUser(AdvisedUser newAdvisedUser){
		boolean changed = false;
		
		if(newAdvisedUser.name != null){
			if(!newAdvisedUser.name.equals(this.name)){
				this.name = newAdvisedUser.name;
				changed = true;
			}
		}  
		
		if(newAdvisedUser.idAdvisedUser != null){
			if(!newAdvisedUser.idAdvisedUser.equals(this.idAdvisedUser)){
				this.idAdvisedUser = newAdvisedUser.idAdvisedUser;
				changed = true;
			}
		}
		
		return changed;
	}
	public Integer getIdAdvisedUser() {
		return idAdvisedUser;
	}

	public void setIdAdvisedUser(Integer idAdvisedUser) {
		this.idAdvisedUser = idAdvisedUser;
	}

	public Consultant getConsultant() {
		return consultant;
	}

	public void setConsultant(Consultant consultant) {
		this.consultant = consultant;
	}
	
	
}
