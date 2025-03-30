package com.APIAutomation.test;

import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;
import com.APIAutomation.base.AuthenticationService;
import com.APIAutomation.payload.RequestPayload;
import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestExhecution
{
	@Test(description = "Successful User Registration - A new user with valid details should successfully register")
	public static void Successful_User_Registration()
	{
		Faker faker=new Faker();
		RequestPayload payload=new RequestPayload();
		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setEmail(faker.internet().emailAddress());
		payload.setUsername(faker.name().username());
		payload.setMobileNumber("9876543210");
		payload.setPassword(faker.internet().password());
		AuthenticationService authenticationService=new AuthenticationService();
		authenticationService.SingUp(payload)
		.then()
		.assertThat()
		.header("content-type", "text/plain;charset=UTF-8")
		.log()
		.all()
		.extract()
		.asPrettyString();
	}
	
	@Test(description = "Registration with Existing Username - Registration should fail if username already exists.")
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
		.extract()
		.response();
		
		response.then().statusCode(400);
		response.then().body(equalTo("Error: Username is already taken!"));
		
	}
}
