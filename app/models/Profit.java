package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.Required;
import serializer.JsonAdvisedUserSerializer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class Profit extends CashFlow{

	@Required
	private Integer idProfit;
	
	@ManyToOne
	@JsonProperty("idAdvisedUser")
	@JsonSerialize(using = JsonAdvisedUserSerializer.class)
	private AdvisedUser user;
	
	public static final Find<Long, Profit> find = new Find<Long, Profit>(){};

	public static boolean existsProfitWithId(int id){
		
		if(Profit.findProfitWithId(id) != null){
			return true;
		}
		return false;
	}
	
	public static Profit findProfitWithId(int id){
		
		return find.where().eq("idProfit", id).findUnique();	
	}
	
	public static List<Profit> findAllProfits(){
		return find.findList();
	}
	
	public Integer getIdProfit() {
		return idProfit;
	}

	public void setIdProfit(Integer idProfit) {
		this.idProfit = idProfit;
	}

	public AdvisedUser getUser() {
		return user;
	}

	public void setUser(AdvisedUser user) {
		this.user = user;
	}
	
}
