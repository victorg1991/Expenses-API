package models;

import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;

import java.util.List;

import org.junit.Test;

import play.data.Form;
import play.libs.Json;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class ProfitTest {

	@Test
    public void saveProfitTest() {
		running(fakeApplication(inMemoryDatabase()), () -> {
				
			createProfitWithId(1);
			
			Profit profitTest = Profit.find.byId(1L);
			
			assertNotNull(profitTest);
			assertEquals("sueldo de noviembre", profitTest.getDescription());
			assertNotNull(profitTest.getUser());
		});
    }
	
	@Test
    public void findWithIdTest() {
		running(fakeApplication(inMemoryDatabase()), () -> {
				
			createProfitWithId(1);
			
			Profit profitTest = Profit.findProfitWithId(1);
			
			assertNotNull(profitTest);
			assertEquals("sueldo de noviembre", profitTest.getDescription());
			assertNotNull(profitTest.getUser());
		});
    }
	
	@Test
    public void findAllProfitTest() {
		running(fakeApplication(inMemoryDatabase()), () -> {
				
			createProfitWithId(1);
			createProfitWithId(2);
			createProfitWithId(3);
			
			List<Profit> profitsTest = Profit.findAllProfits();
			
			assertNotNull(profitsTest);
			assertEquals(3, profitsTest.size());
		});
    }
	
	@Test
    public void existsProfit() {
		running(fakeApplication(inMemoryDatabase()), () -> {
				
			createProfitWithId(1);
			
			boolean exists = Profit.existsProfitWithId(1);
			
			assertTrue(exists);
		});
    }
	
	@Test
    public void constructFromJson() {
		running(fakeApplication(inMemoryDatabase()), () -> {
			
			AdvisedUser user = createAdvisedUser();
			
			ObjectNode node = Json.newObject();
			node.put("idProfit", 1);
			node.put("quantity", 150.0f);
			node.put("description", "venta de television");
			node.put("idAdvisedUser", user.getIdAdvisedUser());
			
			Form<Profit> form = Form.form(Profit.class).bind(node);
			
			Profit profit = form.get();
			
			profit.setIdProfit(node.get("idAdvisedUser").asInt());
			
			profit.save();
			
			Profit profitFetched = Profit.findProfitWithId(profit.getIdProfit());
			
			assertFalse(form.hasErrors());
			assertNotNull(profit);
			assertNotNull(profitFetched);
		});
    }
	
	private void createProfitWithId(Integer id){
		
		AdvisedUser user = createAdvisedUser();
		
		Profit profit = new Profit();
		profit.setId(id);
		profit.setIdProfit(id);
		profit.setUser(user);
		profit.setQuantity(150.0f);
		profit.setDescription("sueldo de noviembre");
		
		profit.save();
	}
	
	private AdvisedUser createAdvisedUser(){
		
		AdvisedUser user = new AdvisedUser();
		user.setName("Juan");
		user.setIdAdvisedUser(1);
		user.save();
		
		return user;
	}
	
}
