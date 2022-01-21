package restassuredtestcases;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
public class Assignment8 {

	//GET- single user not found
	@Test
	public void testGetSingleUserNotFound() {

		System.out.println("GET-Single User Not Found");

		baseURI = "https://reqres.in/api";

		given().get("/users/23").then().statusCode(404).log().all();
	}

	//GET-List of resources
	@Test
	public void testGetlistResources() {

		System.out.println("GET-List of resources");

		baseURI = "https://reqres.in/api";

		given().get("/unknown").then().statusCode(200).log().all();
	}

	//GET- SINGLE <RESOURCE>
	@Test
	public void testGetSingleResources() {

		System.out.println("GET- SINGLE <RESOURCE>");

		baseURI = "https://reqres.in/api";

		given().get("/unknown/2").then().statusCode(200).log().all();
	}

	// GET-SINGLE <RESOURCE> NOT FOUND
	@Test
	public void testGetSingleResourcesNotFound() {

		System.out.println("GET-Single REsources Not Found");

		baseURI = "https://reqres.in/api";

		given().get("/unknown/23").then().statusCode(404).log().all();
	}

	// POST - REGISTER - SUCCESSFUL
	@Test
	public void validatePostRegisterSuccessful() {
	System.out.println("POST - REGISTER - SUCCESSFUL");
	baseURI = "https://reqres.in/api";

	JSONObject postData = new JSONObject();
	postData.put("email", "eve.holt@reqres.in");
	postData.put("password", "pistol");

	given()
		.header("Content-Type", "application/json")
		.accept(ContentType.JSON)
		.body(postData.toJSONString())
	.when()
		.post("/register")
	.then()
		.statusCode(200)
		.log().body();
	}
	
	// POST - REGISTER - UNSUCCESSFUL
		@Test
		public void validatePostRegisterUnSuccessful() {
		System.out.println("POST - REGISTER - UNSUCCESSFUL");
		baseURI = "https://reqres.in/api";

		JSONObject postData = new JSONObject();
		postData.put("email", "sydney@fife");
	
		given()
			.header("Content-Type", "application/json")
			.accept(ContentType.JSON)
			.body(postData.toJSONString())
		.when()
			.post("/register")
		.then()
			.statusCode(400)
			.body("error",equalTo("Missing password"))
			
			//if validation fails , status is displayed
			.log().ifStatusCodeIsEqualTo(200)
			
			//if validation fails,display status
			.log().ifValidationFails(LogDetail.STATUS)
			
			.log().body();
		}
		
		// LOGIN - UNSUCCESSFUL
		@Test
		public void validatePostLoginUnsuccessful() {
		System.out.println("POST - LOGIN - UNSUCCESSFUL");
		baseURI = "https://reqres.in/api";

		JSONObject postData = new JSONObject();
		postData.put("email", "peter@klaven");

		given()
			.header("Content-Type", "application/json")
			.accept(ContentType.JSON)
			.body(postData.toJSONString())
		.when()
			.post("/login")
		.then()
		.statusCode(400)
		.body("error",equalTo("Missing password"))
		
		//if validation fails , status is displayed
		.log().ifStatusCodeIsEqualTo(400)
		
		//if validation fails,display status
		.log().ifValidationFails(LogDetail.STATUS);
		}

		
		//Register a user >> extract id and token
        // Log in with the above created user >> extract token
        // run get single user to find the same user id >> validate name and job details
        // SINGLE <RESOURCE> use the same user if >> validate details
        // then update user details  >> add validations >> search user and validate again
        // patch same user >> validate response >> search user >> validate
        // delete same user >> validate code >> search user >> validate
		
		@Test
		public void validationOfCompleteFlow()
		{
			
			
			//Register a user >> extract id and token
			baseURI = "https://reqres.in/api";

			JSONObject json = new JSONObject();
			json.put("email", "eve.holt@reqres.in");
			json.put("password", "pistol");

			System.out.println(json.toJSONString());

			int id =given()
					.body(json.toJSONString())
					.header("charset", "utf-8")
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)

					.when()
						.post("/register")
			
					.then()
						.extract().path("id");
						
			System.out.println(id);
			
			String token =given()
					.body(json.toJSONString())
					.header("charset", "utf-8")
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)

					.when()
						.post("/register")
			
					.then()
						.extract().path("token");
						
			System.out.println(token);
			
			// Log in with the above created user >> extract token
			
			String Logintoken = given()
					.body(json.toJSONString())
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.header("charset","utf-8")
					.when()
					.post("/login")
					.then()
					.extract().path("token");
			
			//run get single user to find the same user id >> validate name and job details
			int userId =given()
					.body(json.toJSONString())
					.header("charset", "utf-8")
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)

					.when()
						.get("/users/"+id)
			
					.then()
						.body("data.id",equalTo(id))
						.body("data.email",equalTo("eve.holt@reqres.in"))
						.body("data.first_name", equalTo("Eve"))
						.body("data.last_name", equalTo("Holt"))
						.extract().path("data.id");
					
						System.out.println(userId);
			
			//then update user details  >> add validations >> search user and validate again
			
		 json.put("name", "Supriya");
		 json.put("job","Quality Engineer");
		 
			given()
			.header("Content-Type","application/json")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.header("Connection","keep-alive")
				.body(json.toJSONString())
			.when()
				.put("/users/"+id)
			.then()
				.statusCode(200)
				.body("name", equalTo("Supriya"))
				.body("job", equalTo("Quality Engineer"))
				.log().body();
			
			//Patch same user >> validate response >> search user >> validate
			json.put("job","Quality Control");
			 
			given()
			.header("Content-Type","application/json")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.header("Connection","keep-alive")
				.body(json.toJSONString())
			.when()
				.patch("/users/"+id)
			.then()
				.statusCode(200)
				.body("name", equalTo("Supriya"))
				.body("job", equalTo("Quality Control"))
				.log().body();
			
			 //Delete same user >> validate code >> search user >> validate
			given()
				.header("Content-Type","application/json")
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(json.toJSONString())
			
			.when()
				.delete("/users/"+id)
			.then()
				.statusCode(204)
				.log().ifValidationFails(LogDetail.STATUS)
				.log().body();
			
		}
}