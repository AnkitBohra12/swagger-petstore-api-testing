package petstore.api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import petstore.api.payloads.User;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class UserEndPoints {
	
	public static Response createUser(User payload) {
		Response res = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		
		.when()
			.post(Routes.post_url);
		
		return res;
	}
	
	public static Response readUser(String username) {
		Response res = given()
						.pathParam("username", username)
						
					.when()
						.get(Routes.get_url);
		return res;				
	}
	
	public static Response updateUser(String username, User payload) {
		Response res = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
			.pathParam("username", username)
		
		.when()
			.put(Routes.update_url);
		
		return res;
	}
	
	public static Response deleteUser(String username) {
		
		Response response = given()
				.pathParam("username", username)
			.when()
				.delete(Routes.delete_url);
		
		return response;
	}
	
	
	
	
	

}
