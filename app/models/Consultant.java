package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import play.data.validation.Constraints.Required;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"users"})
public class Consultant extends User{	
	
	@Required
	private Integer idConsultant;
	
	
	@OneToMany(mappedBy="consultant")
	@Valid
	private List<AdvisedUser> users = new ArrayList<AdvisedUser>();

	public static final Find<Long, Consultant> find = new Find<Long, Consultant>(){};
	
	public static boolean existsConsultantWithId(int id){
		
		if(Consultant.findConsultantWithId(id) != null){
			return true;
		}
		return false;
	}
	
	public static Consultant findConsultantWithId(int id){
		
		return find.where().eq("idConsultant", id).findUnique();	
	}
	
	public static List<Consultant> findAllConsultants(){
		return find.findList();
	}
	
	public boolean updateConsultant(Consultant newConsultant){
		boolean changed = false;
		
		if(newConsultant.name != null){
			if(!newConsultant.name.equals(this.name)){
				this.name = newConsultant.name;
				changed = true;
			}
		}
		
		if(newConsultant.idConsultant != null){
			if(!newConsultant.idConsultant.equals(this.idConsultant)){
				this.idConsultant = newConsultant.idConsultant;
				changed = true;
			}
		}
		
		return changed;
	}
	
	public List<AdvisedUser> getUsers() {
		return users;
	}

	public void setUsers(List<AdvisedUser> users) {
		this.users = users;
	}

	public Integer getIdConsultant() {
		return idConsultant;
	}

	public void setIdConsultant(Integer idConsultant) {
		this.idConsultant = idConsultant;
	}
	
	
}
