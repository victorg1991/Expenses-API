package controllers;

import java.util.List;

import models.AdvisedUser;
import models.Consultant;
import play.data.Form;
import play.i18n.Messages;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import utils.RequestUtils;

public class Consultants extends Controller{

	public Result create(){
		Form<Consultant> form = Form.form(Consultant.class).bindFromRequest();
    
    	if(form.hasErrors()){
    		return badRequest(form.errorsAsJson());
    	}
    	
    	Consultant consultant = form.get();
    	
    	if(!Consultant.existsConsultantWithId(consultant.getIdConsultant())){
        	consultant.save();
        	return created();
    	}
    	
        return Results.status(409, Messages.get("consultantregistered"));
	}
	
	public Result delete(Integer cId){
		Consultant consultant = Consultant.findConsultantWithId(cId);
		
		if(consultant == null){
			return notFound();
		}
		
		consultant.delete();
		
		return ok();
	}
	
	public Result retrieve(Integer cId){
		Consultant consultant = Consultant.findConsultantWithId(cId);
		
		if(consultant == null){
			return notFound();
		}
		
		if(RequestUtils.acceptsJson(request())){
			return ok(Json.toJson(consultant));
		} else if (RequestUtils.acceptsXml(request())){
			return ok(views.xml.consultant.render(consultant));
		}
		
		return badRequest("unsupported format");
	}
	
	public Result update(Integer cId){
		Consultant consultant = Consultant.findConsultantWithId(cId);
		
		if(consultant == null){
			return notFound();
		}
		
		Form<Consultant> form = Form.form(Consultant.class).bindFromRequest();
	    
    	if(form.hasErrors()){
    		return badRequest(form.errorsAsJson()); 
    	}
    	
    	Consultant newConsultant = form.get();
	
		if(consultant.updateConsultant(newConsultant)){
			consultant.update();
			return ok();
		}
		
		return Results.status(NOT_MODIFIED);
	}
	
	public Result getAdvisedUsers(Integer cId){
		Consultant consultant = Consultant.findConsultantWithId(cId);
		
		if(consultant == null){
			return notFound();
		}
		
		List<AdvisedUser> users = consultant.getUsers();
		
		if(RequestUtils.acceptsJson(request())){
			return ok(Json.toJson(users));
		} else if (RequestUtils.acceptsXml(request())){
			return ok(views.xml.advisedusers.render(users));
		}
		
		return badRequest("unsupported format");
		
	
	}
	
	public Result getAllConsultants(){
		
		List<Consultant> consultants = Consultant.findAllConsultants();
		
		if(RequestUtils.acceptsJson(request())){
			return ok(Json.toJson(consultants));
		} else if (RequestUtils.acceptsXml(request())){
			return ok(views.xml.consultants.render(consultants));
		}
		
		return badRequest("unsupported format");
	}
}
