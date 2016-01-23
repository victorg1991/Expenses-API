package controllers;


import java.util.List;

import models.AdvisedUser;
import models.Profit;
import models.Spending;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import serializer.JsonSerializeHelper;
import utils.RequestUtils;

import com.fasterxml.jackson.databind.node.ArrayNode;

public class Profits extends Controller{
	
	public Result create(){
		Form<Profit> form = Form.form(Profit.class).bindFromRequest();
    
    	if(form.hasErrors()){
    		return badRequest(form.errorsAsJson());
    	}
    	
    	Profit profit = form.get();
    	
    	if(Profit.existsProfitWithId(profit.getIdProfit())){
    		return Results.status(409, "already exists");
    	}
    	
    	Integer idAdvisedUser = RequestUtils.getIntegerFromBody(request(), "idAdvisedUser");
    	
    	if(idAdvisedUser == null){  
    		return badRequest("You need to add the id of the adviseduser");
    	}
    	
    	AdvisedUser advisedUser = AdvisedUser.findAdvisedUserWithId(idAdvisedUser);
    	
    	if(advisedUser == null){
			return Results.status(409, "there is no adviseduser with this id");
		}
    	
    	profit.setUser(advisedUser);
    
    	profit.save();
    	
        return created();
	}
	
	public Result delete(Integer pId){
		Profit profit = Profit.findProfitWithId(pId);
		
		if(profit == null){
			return notFound();
		}
		
		profit.delete();
		
		return ok();
	}
	
	public Result retrieve(Integer pId){
		
		Profit profit = Profit.findProfitWithId(pId);
		
		if(profit == null){
			return notFound();
		}
		
		if(RequestUtils.acceptsJson(request())){
			return ok(Json.toJson(profit));
		} else if (RequestUtils.acceptsXml(request())){
			return ok(views.xml.profit.render(profit));
		}
		
		return badRequest("unsupported format");
	}
	
	public Result getAllProfits(){
		
		List<Profit> profits = Profit.findAllProfits();
		
		
		if(RequestUtils.acceptsJson(request())){
			return ok(Json.toJson(profits));
		} else if (RequestUtils.acceptsXml(request())){
			return ok(views.xml.profits.render(profits));
		}
		
		return badRequest("unsupported format");
	}
	
	public Result getAdvisedUserFromProfit(Integer pId){
		Profit profit = Profit.findProfitWithId(pId);
		
		if(profit == null){
			return notFound();
		}
		
		AdvisedUser advisedUser = profit.getUser();
		
		if(RequestUtils.acceptsJson(request())){
			return ok(Json.toJson(advisedUser));
		} else if (RequestUtils.acceptsXml(request())){
			return ok(views.xml.adviseduser.render(advisedUser));
		}
		
		return badRequest("unsupported format");
	
	}
	
	public Result updateQuantity(Integer pId){
		
		float newQuantity;
		
		try {
			newQuantity = Float.valueOf(request().getQueryString("value"));
		} catch(Exception ex) {
			return badRequest("You have to include argument 'value' to the call with numberic value");
		}
		
		Profit profit = Profit.findProfitWithId(pId);
		
		if(profit == null){
			return notFound();
		}
		
		profit.setQuantity(newQuantity);
		
		profit.update();
		
		return ok();
	}
	
	public Result updateDescription(Integer pId) {
		
		String newDescription = request().getQueryString("value");
		
		if(newDescription == null) {
			return badRequest("You have to include argument 'value'");
		}
		
		Profit profit = Profit.findProfitWithId(pId);
		
		if(profit == null){
			return notFound();
		}
		
		profit.setDescription(newDescription);
		
		profit.update();
		
		return ok();
	}

}
