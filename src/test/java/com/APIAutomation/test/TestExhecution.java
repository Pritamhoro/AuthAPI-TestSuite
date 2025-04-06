package com.APIAutomation.test;

import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.APIAutomation.base.AuthenticationService;
import com.APIAutomation.payload.RequestPayload;
import com.APIAutomation.utilities.Listner;
import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@Listeners(Listner.class)
public class TestExhecution
{
	static Faker faker;
	@Test(priority = 1,description = "Successful User Registration - A new user with valid details should successfully register")
	public static void Successful_User_Registration()
	{
		RestAssured.registerParser("text/plain", io.restassured.parsing.Parser.TEXT);
		faker=new Faker();
		RequestPayload payload=new RequestPayload();
		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setEmail(faker.internet().emailAddress());
		payload.setUsername(faker.name().username());
		String username=faker.name().username();
		System.out.println("username ==========>"+username);
		payload.setMobileNumber("9876543210");
		payload.setPassword(faker.internet().password());
		String password=faker.internet().password();
		System.out.println("password ============>"+password);
		AuthenticationService authenticationService=new AuthenticationService();
		authenticationService.SingUp(payload)
		.then()
		.assertThat()
		.header("content-type", "text/plain;charset=UTF-8")
		.log()
		.all()
		.extract()
		.asString();
	}
	
	@Test(priority = 2,description = "Registration with Existing Username - Registration should fail if username already exists.")
	public void Registration_with_Existing_Username()
	{
		RestAssured.registerParser("text/plain", io.restassured.parsing.Parser.TEXT);
		RequestPayload payload=new RequestPayload();
		payload.setFirstName("namefirst");
		payload.setLastName("nameLast");
		payload.setEmail("name@gmail.com");
		payload.setUsername("name");
		payload.setMobileNumber("9876543210");
		payload.setPassword("name2@123");
		AuthenticationService authenticationService=new AuthenticationService();
		Response response =authenticationService.SingUp(payload)
		.then()
		.log()
		.body()
		.statusCode(400)
		.and()
		.extract()
		.response();
		response.then().statusCode(400);
		response.then().body(equalTo("Error: Username is already taken!"));
		
	}
	@Test(description = "Successful Login - User logs in with correct username and password.")
	public static void Login_valid_Credentials()
	{
		HashMap<String, String> body=new HashMap<String, String>();
		body.put("username","Admin");
		body.put("password", "Admin@1234");
		
		AuthenticationService authenticationService=new AuthenticationService();
		authenticationService.Login(body)
		.then()
		.log()
		.all()
		.and()
		.extract()
		.asPrettyString();
		
	}
	@Test(description = "Login with Invalid Credentials")
	public static void Login_Invalid_Credentials()
	{
		RequestPayload payload=new RequestPayload();
		payload.setUsername("Admn");
		payload.setPassword("Admn@12345");
		AuthenticationService authenticationService=new AuthenticationService();
		Response response = authenticationService.Login(payload)
		.then()
		.log()
		.all()
		.and()
		.extract()
		.response();
		
		response.then().statusCode(401);
		response.then().body("error",equalTo("Invalid Credentials"));
		
	}
	
}
