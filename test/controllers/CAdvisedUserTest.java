  package controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static play.test.Helpers.GET;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.route;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;

import org.junit.Test;

import play.mvc.Http.RequestBuilder;
import play.mvc.Result;

public class CAdvisedUserTest {

	@Test
	public void responseAdvisedUserJson() {
		running(fakeApplication(inMemoryDatabase()), () -> {
    		RequestBuilder request = fakeRequest(GET, "/v1/advisedusers").header("Accept", "application/json");
    		
    		Result result = route(request);
    		assertNotNull(result);
    		assertEquals("application/json", result.contentType());
		});
	}
	
	@Test
	public void responseAdvisedUserXml() {
		running(fakeApplication(inMemoryDatabase()), () -> {
    		RequestBuilder request = fakeRequest(GET, "/v1/advisedusers").header("Accept", "application/xml");
    		
    		Result result = route(request);
    		assertNotNull(result);
    		assertEquals("application/xml", result.contentType());
		});
	}
	
}
