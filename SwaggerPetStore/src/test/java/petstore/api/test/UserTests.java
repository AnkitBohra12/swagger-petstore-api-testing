package petstore.api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;
import petstore.api.endpoints.UserEndPoints;
import petstore.api.payloads.User;

public class UserTests {
	
	Faker faker;
	User userPayload;
	
	@BeforeClass
	public void setupData() {
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(8,15));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
	}
	
	@Test(priority =1 )
	public void test_CreateUser() {
		Response response = UserEndPoints.createUser(userPayload);
		response.then()
		.statusCode(200)
		.log().all();	
		
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
	@Test(priority =2 )
	public void test_GetUser() {
		Response response = UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);	
		System.out.println("********************************************************************");
	}
	
	@Test(priority =3)
	public void test_UpdateUser() {
		
		// update the data using payload
		
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		userPayload.setFirstname(faker.name().firstName());
		
		Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		
		// check data after update
		Response responseAfterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());
		responseAfterUpdate.then().log().all();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
		
	}
	
	public void test_DeleteUser() {
		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
	
}
