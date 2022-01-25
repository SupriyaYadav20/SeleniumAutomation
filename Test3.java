
package restassuredtestcases;

import org.testng.Assert;
import org.testng.annotations.Test;


import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;


public class Test3 {
	
	@Test
	
	public void createUserList()
	{
		
		baseURI = "https://gorest.co.in/";
		
		given()
		     .get("/public/v1/users2")
		.then()
		     .log().status()
		     .log().body();
	}
	
	
	
@Test
	
	public void getCreatedUserList()
	{
		
		baseURI = "https://gorest.co.in/";
		
		given()
		     .get("/public/v1/users/100")
		.then()
		     .log().status()
		     .log().everything()
		     .log().ifStatusCodeIsEqualTo(203)
		     .log().ifValidationFails(LogDetail.STATUS)
		     .log().body();
		
	}
@Test

public void updateCreateUser() {
	baseURI = "https://gorest.co.in/";
	
	JSONObject reqData = new JSONObject();
	
	reqData.put("name","Supriya");
	reqData.put("email","Supriya@gmail.com");
	
	System.out.println(reqData.toJSONString());
	
	given()
	.header("content-type" , "application/json")
	.header("Connection", "Keep-alive")
	.contentType(ContentType.JSON)
	.accept(ContentType.JSON)
	      .body(reqData.toJSONString())
	.when()
	      .put("/public/v1/users/100")
	.then()
	    .log().status()
	    .log().body();
	
}
@Test

void findCreateUserInList()
{
	
	baseURI = "https://gorest.co.in/";
	
	JSONObject reqData = new JSONObject();
	
	reqData.put("name","John");
	reqData.put("email","Supriya@gmail.com");
	
	System.out.println(reqData.toJSONString());
	
	given()
//	.header("content-type" , "application/json")
	.header("Connection", "Keep-alive")
	.contentType(ContentType.JSON)
	.accept(ContentType.JSON)
	      .body(reqData.toJSONString())
	.when()
	      .post("/public/v1/users/100/posts")
	.then()
	.log().body();
}


@Test

public void testPostCreateUser() {
	baseURI = "https://gorest.co.in/";
	
	JSONObject reqData = new JSONObject();
	
	reqData.put("name","Supriya");
	reqData.put("email","Supriya@gmail.com");
	
	System.out.println(reqData.toJSONString());
	
	given()
//	.header("content-type" , "application/json")
	.header("Connection", "Keep-alive")
	.contentType(ContentType.JSON)
	.accept(ContentType.JSON)
	      .body(reqData.toJSONString())
	.when()
	      .post("/public/v1/users/100/posts")
	.then()
	    .log().status()
	    .log().body();
	
}

@Test

public void createPostComment() {
	baseURI = "https://gorest.co.in/";
	
	JSONObject reqData = new JSONObject();
	
	reqData.put("name","Supriya");
	reqData.put("email","Supriya@gmail.com");
	
	System.out.println(reqData.toJSONString());
	
	given()
//	.header("content-type" , "application/json")
	.header("Connection", "Keep-alive")
	.contentType(ContentType.JSON)
	.accept(ContentType.JSON)
	      .body(reqData.toJSONString())
	.when()
	      .post("/public/v1/posts/100/comments")
	.then()
	    .log().status()
	    .log().body();
	
}

@Test

public void createUserTODO() {
	baseURI = "https://gorest.co.in/";
	
	JSONObject reqData = new JSONObject();
	
	reqData.put("name","Twisha");
	reqData.put("email","Supriya@gmail.com");
	
	System.out.println(reqData.toJSONString());
	
	given()
//	.header("content-type" , "application/json")
	.header("Connection", "Keep-alive")
	.contentType(ContentType.JSON)
	.accept(ContentType.JSON)
	      .body(reqData.toJSONString())
	.when()
	      .post("/public/v1/users/100/todos")
	.then()
	    .log().status()
	    .log().body();
	
}

@Test

public void testDeleteUser() {
	baseURI = "https://gorest.co.in/";
	
	when()
	  .delete("/public/v1/users/100")
	 .then()
	   .log().status()
	   .log().body();
	 	
}

}
