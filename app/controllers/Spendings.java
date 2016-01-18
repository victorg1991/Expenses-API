package controllers;


import java.util.List;

import models.AdvisedUser;
import models.Spending;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import serializer.JsonHelper;
import utils.RequestUtils;

import com.fasterxml.jackson.databind.node.ArrayNode;

public class Spendings extends Controller{
	
	public Result create(){
		Form<Spending> form = Form.form(Spending.class).bindFromRequest();
    
    	if(form.hasErrors()){
    		return badRequest(form.errorsAsJson());
    	}
    	
    	Spending spending = form.get();
    	
    	if(Spending.existsSpendingWithId(spending.getIdSpending())){
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
    	
    	spending.setUser(advisedUser);
    
    	spending.save();
    	
        return created();
	}
	
	public Result delete(Integer sId){
		Spending spending = Spending.findSpendingWithId(sId);
		
		if(spending == null){
			return notFound();
		}
		
		spending.delete();
		
		return ok();
	}
	
	public Result retrieve(Integer sId){
		
		Spending spending = Spending.findSpendingWithId(sId);
		
		if(spending == null){
			return notFound();
		}
		
		if(RequestUtils.acceptsJson(request())){
			return ok(JsonHelper.<Spending>getJsonNode(spending));
		} else if (RequestUtils.acceptsXml(request())){
			return ok(views.xml.spending.render(spending));
		}
		
		return badRequest("unsupported format");
	}
	
	public Result getAllSpendings(){
		
		List<Spending> spendings = Spending.findAllSpendings();
		
		
		if(RequestUtils.acceptsJson(request())){
			ArrayNode spendingJson = Json.newArray();
			
			for(Spending spending : spendings){
				spendingJson.add(JsonHelper.<Spending>getJsonNode(spending));
			}
			
			return ok(spendingJson);
		} else if (RequestUtils.acceptsXml(request())){
			return ok(views.xml.spendings.render(spendings));
		}
		
		return badRequest("unsupported format");
	}
	
	public Result getAdvisedUserFromSpending(Integer sId){
		Spending spending = Spending.findSpendingWithId(sId);
		
		if(spending == null){
			return notFound();
		}
		
		AdvisedUser advisedUser = spending.getUser();
		
		if(RequestUtils.acceptsJson(request())){
			return ok(Json.toJson(advisedUser));
		} else if (RequestUtils.acceptsXml(request())){
			return ok(views.xml.adviseduser.render(advisedUser));
		}
		
		return badRequest("unsupported format");
	
	}

}
