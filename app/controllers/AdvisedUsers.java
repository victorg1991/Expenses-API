package controllers;

import java.util.List;

import models.AdvisedUser;
import models.Consultant;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import utils.RequestUtils;

public class AdvisedUsers extends Controller{

	public Result create(){
		Form<AdvisedUser> form = Form.form(AdvisedUser.class).bindFromRequest();
    
    	if(form.hasErrors()){
    		return badRequest(form.errorsAsJson());
    	}
    	
    	AdvisedUser advisedUser = form.get();
    	
    	if(AdvisedUser.existsAdvisedUserWithId(advisedUser.getIdAdvisedUser())){
    		return Results.status(409, "already exists");
    	}
    	
    	Integer idConsultant = RequestUtils.getIntegerFromBody(request(), "idConsultant");
    	
    	if(idConsultant != null){   		
    		Consultant consultant = Consultant.findConsultantWithId(idConsultant);
    		
    		if(consultant == null){
    			return Results.status(409, "there is no consultant with this id");
    		}
    		
    		advisedUser.setConsultant(consultant);
    	}
    
    	advisedUser.save();
    	
        return created();
	}
	
	public Result delete(Integer aId){
		AdvisedUser advisedUser = AdvisedUser.findAdvisedUserWithId(aId);
		
		if(advisedUser == null){
			return notFound();
		}
		
		advisedUser.delete();
		
		return ok();
	}
	
	public Result retrieve(Integer aId){
		AdvisedUser advisedUser = AdvisedUser.findAdvisedUserWithId(aId);
		
		if(advisedUser == null){
			return notFound();
		}
		
		if(RequestUtils.acceptsJson(request())){
			return ok(Json.toJson(advisedUser));
		} else if (RequestUtils.acceptsXml(request())){
			return ok(views.xml.adviseduser.render(advisedUser));
		}
		
		return badRequest("unsupported format");
	
	}
	
	public Result update(Integer aId){
		AdvisedUser advisedUser = AdvisedUser.findAdvisedUserWithId(aId);
		
		if(advisedUser == null){
			return notFound();
		}
		
		Form<AdvisedUser> form = Form.form(AdvisedUser.class).bindFromRequest();
		
    	if(form.hasErrors()){
    		return badRequest(form.errorsAsJson());
    	}
    	
    	AdvisedUser newAdvisedUser = form.get();
	
		if(advisedUser.updateAdvisedUser(newAdvisedUser)){
			advisedUser.update();
			return ok();
		}
		
		return Results.status(NOT_MODIFIED);
	}
	
	public Result getAllAdvisedUsers(){
		
		List<AdvisedUser> advisedUsers = AdvisedUser.findAllAdvisedUsers();
		
		if(RequestUtils.acceptsJson(request())){
			return ok(Json.toJson(advisedUsers));
		} else if (RequestUtils.acceptsXml(request())){
			return ok(views.xml.advisedusers.render(advisedUsers));
		}
		
		return badRequest("unsupported format");
	}
	
	public Result getConsultantFromAdvisedUser(Integer aId){
		
		AdvisedUser advisedUser = AdvisedUser.findAdvisedUserWithId(aId);
		
		if(advisedUser == null || advisedUser.getConsultant() == null){
			return notFound();
		}
		
		Consultant consultant = advisedUser.getConsultant();
		
		if(RequestUtils.acceptsJson(request())){
			return ok(Json.toJson(consultant));
		} else if (RequestUtils.acceptsXml(request())){
			return ok(views.xml.consultant.render(consultant));
		}
		
		return badRequest("unsupported format");
	}
	
	public Result updateConsultant(Integer aId){
	
		Integer idConsultant; 
		try {
			idConsultant = new Integer(request().getQueryString("value"));
		} catch(Exception ex){
			return badRequest("You need to add parameter value with the id of the new consultant"); 
		}
		
		AdvisedUser advisedUser = AdvisedUser.findAdvisedUserWithId(aId);
		
		if(advisedUser == null){
			return notFound("adviseduser not found");
		}
		
		Consultant consultant = Consultant.findConsultantWithId(idConsultant);
		
		if(consultant == null){
			return notFound("new consultant id not found");
		}
		
		advisedUser.setConsultant(consultant);

		advisedUser.update();
		
		return ok();
	}
}
