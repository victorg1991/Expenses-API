package utils;

import play.mvc.Http.Request;

import com.fasterxml.jackson.databind.JsonNode;

public class RequestUtils {
	
	public static Integer getIntegerFromBody(Request request){
		
		JsonNode body = request.body().asJson();

		return body.get("idConsultant").asInt();
	}

	public static boolean acceptsJson(Request request) {
		return request.accepts("application/json");
	}
	
	public static boolean acceptsXml(Request request) {
		return (request.accepts("application/xml") || request.accepts("text/xml"));
	}
}
