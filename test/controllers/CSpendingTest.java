  package controllers;

import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;

import java.util.List;

import models.AdvisedUser;
import models.Spending;

import org.junit.Test;

import play.data.Form;
import play.libs.Json;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class CSpendingTest {

	@Test
    public void saveSpendintTest() {
		running(fakeApplication(inMemoryDatabase()), () -> {
				
			createSpendingWithId(1);
			
			Spending spendingTest = Spending.find.byId(1L);
			
			assertNotNull(spendingTest);
			assertEquals("pago de lentillas", spendingTest.getDescription());
			assertNotNull(spendingTest.getUser());
		});
    }
	
	@Test
    public void findWithIdTest() {
		running(fakeApplication(inMemoryDatabase()), () -> {
				
			createSpendingWithId(1);
			
			Spending spendingTest = Spending.findSpendingWithId(1);
			
			assertNotNull(spendingTest);
			assertEquals("pago de lentillas", spendingTest.getDescription());
			assertNotNull(spendingTest.getUser());
		});
    }
	
	@Test
    public void findAllSpendingTest() {
		running(fakeApplication(inMemoryDatabase()), () -> {
				
			createSpendingWithId(1);
			createSpendingWithId(2);
			createSpendingWithId(3);
			
			List<Spending> spendingsTest = Spending.findAllSpendings();
			
			assertNotNull(spendingsTest);
			assertEquals(3, spendingsTest.size());
		});
    }
	
	@Test
    public void existsSpending() {
		running(fakeApplication(inMemoryDatabase()), () -> {
				
			createSpendingWithId(1);
			
			boolean exists = Spending.existsSpendingWithId(1);
			
			assertTrue(exists);
		});
    }
	
	@Test
    public void constructFromJson() {
		running(fakeApplication(inMemoryDatabase()), () -> {
			
			AdvisedUser user = createAdvisedUser();
			
			ObjectNode node = Json.newObject();
			node.put("idSpending", 1);
			node.put("quantity", 150.0f);
			node.put("description", "compra en el mercadona");
			node.put("idAdvisedUser", user.getIdAdvisedUser());
			
			Form<Spending> form = Form.form(Spending.class).bind(node);
			
			Spending spending = form.get();
			
			spending.setIdSpending(node.get("idAdvisedUser").asInt());
			
			spending.save();
			
			Spending spendingFetched = Spending.findSpendingWithId(spending.getIdSpending());
			
			assertFalse(form.hasErrors());
			assertNotNull(spending);
			assertNotNull(spendingFetched);
		});
    }
	

	private void createSpendingWithId(Integer id){
		
		AdvisedUser user = createAdvisedUser();
		
		Spending spending = new Spending();
		spending.setId(id);
		spending.setIdSpending(id);
		spending.setUser(user);
		spending.setQuantity(150.0f);
		spending.setDescription("pago de lentillas");
		
		spending.save();
	}
	
	private AdvisedUser createAdvisedUser(){
		
		AdvisedUser user = new AdvisedUser();
		user.setName("Juan");
		user.setIdAdvisedUser(1);
		user.save();
		
		return user;
	}
	
}
