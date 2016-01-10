package utils;
import java.util.List;

import models.Consultant;
import play.libs.Json;
import play.mvc.Http.Request;
import play.mvc.Result;
import play.mvc.Results;
public class ConsultantResponseUtils {
	
	@SuppressWarnings("unchecked")
	public static Result getResultForRequest(Object objectToRender, Request request) {
		
		if(objectToRender instanceof Consultant) {
			return getResultForConsulsantRequest((Consultant) objectToRender, request);
			
		} else if (objectToRender instanceof List && ((List<?>) objectToRender).get(0) instanceof Consultant){
			
			return getResultForConsulsantsRequest((List<Consultant>) objectToRender, request);
		}
		
		return null;
	}
	
	private static Result getResultForConsulsantRequest(Consultant c, Request request){
		
		if(RequestUtils.acceptsJson(request)){
			return Results.ok(Json.toJson(c));
		} else if (RequestUtils.acceptsXml(request)){
			return Results.ok(views.xml.consultant.render(c));
		}
		
		return Results.badRequest("unsupported format");
		
	}
	
	private static Result getResultForConsulsantsRequest(List<Consultant> consultants, Request request){
		
		if(RequestUtils.acceptsJson(request)){
			return Results.ok(Json.toJson(consultants));
		} else if (RequestUtils.acceptsXml(request)){
			return Results.ok(views.xml.consultants.render(consultants));
		}
		
		return Results.badRequest("unsupported format");
		
	}

}
