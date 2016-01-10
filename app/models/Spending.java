package models;

import java.util.List;

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
	private AdvisedUser user;
	
	public static final Find<Long, Spending> find = new Find<Long, Spending>(){};

	public static boolean existsSpendingWithId(int id){
		
		if(Spending.findSpendingWithId(id) != null){
			return true;
		}
		return false;
	}
	
	public static Spending findSpendingWithId(int id){
		
		return find.where().eq("idSpending", id).findUnique();	
	}
	
	public static List<Spending> findAllSpendings(){
		return find.findList();
	}
	
	public Integer getIdSpending() {
		return idSpending;
	}

	public void setIdSpending(Integer idSpending) {
		this.idSpending = idSpending;
	}

	public AdvisedUser getUser() {
		return user;
	}

	public void setUser(AdvisedUser user) {
		this.user = user;
	}
	
}
