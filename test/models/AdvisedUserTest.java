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

public class AdvisedUserTest {

	@Test
	public void saveAdvisedUserTest() {
		running(fakeApplication(inMemoryDatabase()), () -> {

			AdvisedUser advisedUser = new AdvisedUser();

			advisedUser.setId(1L);
			advisedUser.setIdAdvisedUser(12);
			advisedUser.setName("prueba");
			advisedUser.setCity("Avila");
			advisedUser.setEmail("email@email.com");
			advisedUser.setDni("1234");

			advisedUser.save();

			AdvisedUser advisedUserTest = AdvisedUser.find.byId(1L);

			assertNotNull(advisedUserTest);
			assertEquals("prueba", advisedUserTest.getName());
			assertNotNull(advisedUserTest.getDni());
		});
	}

	@Test
	public void findWithIdTest() {
		running(fakeApplication(inMemoryDatabase()), () -> {

			createAdvisedUserWithId(1);

			AdvisedUser advisedUserTest = AdvisedUser.findAdvisedUserWithId(1);

			assertNotNull(advisedUserTest);
			assertEquals("prueba", advisedUserTest.getName());
			assertNotNull(advisedUserTest.getDni());
		});
	}

	@Test
	public void findAllAdvisedUserTest() {
		running(fakeApplication(inMemoryDatabase()), () -> {

			createAdvisedUserWithId(1);
			createAdvisedUserWithId(2);
			createAdvisedUserWithId(3);

			List<AdvisedUser> advisedUsersTest = AdvisedUser.findAllAdvisedUsers();

			assertNotNull(advisedUsersTest);
			assertEquals(3, advisedUsersTest.size());
		});
	}

	@Test
	public void existsAdvisedUser() {
		running(fakeApplication(inMemoryDatabase()), () -> {

			createAdvisedUserWithId(1);

			boolean exists = AdvisedUser.existsAdvisedUserWithId(1);

			assertTrue(exists);
		});
	}

	@Test
	public void constructFromJson() {
		running(fakeApplication(inMemoryDatabase()), () -> {

			ObjectNode node = Json.newObject();
			node.put("idAdvisedUser", 1);
			node.put("name", "prueba");
			node.put("city", "Avila");
			node.put("dni", "1232432B");
			node.put("email", "email@email.com");

			Form<AdvisedUser> form = Form.form(AdvisedUser.class).bind(node);

			AdvisedUser advisedUser = form.get();

			advisedUser.save();

			AdvisedUser advisedUserFetched = AdvisedUser.findAdvisedUserWithId(1);

			assertFalse(form.hasErrors());
			assertNotNull(advisedUser);
			assertNotNull(advisedUserFetched);
		});
	}

	private void createAdvisedUserWithId(Integer id) {

		AdvisedUser advisedUser = new AdvisedUser();

		advisedUser.setIdAdvisedUser(id);
		advisedUser.setName("prueba");
		advisedUser.setCity("Avila");
		advisedUser.setEmail("email@email.com");
		advisedUser.setDni("1234");

		advisedUser.save();
	}

}
