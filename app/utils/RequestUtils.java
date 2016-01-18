package utils;

import play.mvc.Http.Request;

import com.fasterxml.jackson.databind.JsonNode;

public class RequestUtils {
	
	public static Integer getIntegerFromBody(Request request, String fieldName){
		
		JsonNode body = request.body().asJson();

		JsonNode field = body.get(fieldName);
		
		return field == null ? null: field.asInt();
	}

	public static boolean acceptsJson(Request request) {
		return request.accepts("application/json");
	}
	
	public static boolean acceptsXml(Request request) {
		return (request.accepts("application/xml") || request.accepts("text/xml"));
	}
}
