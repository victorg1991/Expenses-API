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

public class ConsultantTest {

	@Test
	public void saveConsultantTest() {
		running(fakeApplication(inMemoryDatabase()), () -> {

			Consultant consultant = new Consultant();

			consultant.setId(1L);
			consultant.setIdConsultant(12);
			consultant.setName("prueba");
			consultant.setCity("Avila");
			consultant.setEmail("email@email.com");
			consultant.setDni("1234");

			consultant.save();

			Consultant consultantTest = Consultant.find.byId(1L);

			assertNotNull(consultantTest);
			assertEquals("prueba", consultantTest.getName());
			assertNotNull(consultantTest.getDni());
		});
	}

	@Test
	public void findWithIdTest() {
		running(fakeApplication(inMemoryDatabase()), () -> {

			createConsultantWithId(1);

			Consultant consultantTest = Consultant.findConsultantWithId(1);

			assertNotNull(consultantTest);
			assertEquals("prueba", consultantTest.getName());
			assertNotNull(consultantTest.getDni());
		});
	}

	@Test
	public void findAllConsultantTest() {
		running(fakeApplication(inMemoryDatabase()), () -> {

			createConsultantWithId(1);
			createConsultantWithId(2);
			createConsultantWithId(3);

			List<Consultant> consultantsTest = Consultant.findAllConsultants();

			assertNotNull(consultantsTest);
			assertEquals(3, consultantsTest.size());
		});
	}

	@Test
	public void existsConsultant() {
		running(fakeApplication(inMemoryDatabase()), () -> {

			createConsultantWithId(1);

			boolean exists = Consultant.existsConsultantWithId(1);

			assertTrue(exists);
		});
	}

	@Test
	public void constructFromJson() {
		running(fakeApplication(inMemoryDatabase()), () -> {
			
			
			ObjectNode node = Json.newObject();
			node.put("idConsultant", 1);
			node.put("name", "prueba");
			node.put("city", "Avila");
			node.put("dni", "1232432B");
			node.put("email", "email@email.com");

			Form<Consultant> form = Form.form(Consultant.class).bind(node);

			Consultant consultant = form.get();
			
			consultant.save();

			Consultant consultantFetched = Consultant.findConsultantWithId(1);

			assertFalse(form.hasErrors());
			assertNotNull(consultant);
			assertNotNull(consultantFetched);
		});
	}

	private void createConsultantWithId(Integer id) {

		Consultant consultant = new Consultant();

		consultant.setIdConsultant(id);
		consultant.setName("prueba");
		consultant.setCity("Avila");
		consultant.setEmail("email@email.com");
		consultant.setDni("1234");

		consultant.save();
	}

}
