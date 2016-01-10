package utils;

import java.util.List;

import models.AdvisedUser;
import models.Consultant;
import play.libs.Json;
import play.mvc.Http.Request;
import play.mvc.Result;
import play.mvc.Results;

public class AdvisedUserResponseUtils {
	
	@SuppressWarnings("unchecked")
	public static Result getResultForRequest(Object objectToRender, Request request) {
		
		if(objectToRender instanceof AdvisedUser) {
			return getResultForAdvisedUserRequest((AdvisedUser) objectToRender, request);
			
		} else if (objectToRender instanceof List && ((List<?>) objectToRender).get(0) instanceof AdvisedUser){
			
			return getResultForAdvisedUserRequest((List<AdvisedUser>) objectToRender, request);
		}
		
		return null;
	}
	
	private static Result getResultForAdvisedUserRequest(AdvisedUser a, Request request){
		
		if(RequestUtils.acceptsJson(request)){
			return Results.ok(Json.toJson(a));
		} else if (RequestUtils.acceptsXml(request)){
			return Results.ok(views.xml.adviseduser.render(a));
		}
		
		return Results.badRequest("unsupported format");
		
	}
	
	private static Result getResultForAdvisedUserRequest(List<AdvisedUser> advisedusers, Request request){
		
		if(RequestUtils.acceptsJson(request)){
			return Results.ok(Json.toJson(advisedusers));
		} else if (RequestUtils.acceptsXml(request)){
			return Results.ok(views.xml.advisedusers.render(advisedusers));
		}
		
		return Results.badRequest("unsupported format");
		
	}


}
