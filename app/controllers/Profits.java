package controllers;


import models.AdvisedUser;
import models.Profit;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import utils.RequestUtils;

import com.fasterxml.jackson.databind.node.ObjectNode;

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
	
	public Result retrieve(Integer pId){
		
		Profit profit = Profit.findProfitWithId(pId);
		
		if(profit == null){
			return notFound();
		}
		
		if(RequestUtils.acceptsJson(request())){
			ObjectNode jsonResponse = (ObjectNode) Json.toJson(profit);
			jsonResponse.put("idAdviseruser", profit.getUser().getIdAdvisedUser());
			
			return ok(jsonResponse);
		} else if (RequestUtils.acceptsXml(request())){
			return ok(views.xml.profit.render(profit));
		}
		
		return badRequest("unsupported format");
	}

}
