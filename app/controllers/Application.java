package controllers;

import models.Consultant;
import play.data.Form;
import play.mvc.*;

public class Application extends Controller {

    public Result index() {
    	Form<Consultant> form = Form.form(Consultant.class).bindFromRequest();
    	
    	Consultant a = form.get();
    	
    	if(!Consultant.existsConsultantWithId(a.getIdConsultant())){
        	a.save();
        	
        	return ok("Creado");
    	}

//    	AdvisedUser u = new AdvisedUser();
//    	u.setName("fasdfasUser");
//    	u.setConsultant(a);
//    	u.save();
    	
        return Results.status(409, "dni ya registrado");
    }
    
    public Result test() {
    	
    	Consultant c = Consultant.find.byId((long) 33);
    	
    	StringBuilder users = new StringBuilder();
    	
//    	for(AdvisedUser ad : c.getAdvisedUsers()){
//    		users.append(ad.getName());
//    	}
    	
    	return ok(users.toString());
    }

}
