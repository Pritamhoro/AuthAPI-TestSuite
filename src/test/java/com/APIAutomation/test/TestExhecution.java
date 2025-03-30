package com.APIAutomation.test;

import org.testng.annotations.Test;

import com.APIAutomation.base.AuthenticationService;
import com.APIAutomation.payload.RequestPayload;

public class TestExhecution
{
	@Test(description = "Successful User Registration - A new user with valid details should successfully register")
	public static void Dummy()
	{
		RequestPayload payload=new RequestPayload();
		payload.setFirstName("John");
		payload.setLastName("Doe");
		payload.setEmail("johndoe@example.com");
		payload.setUsername("john_doe123");
		payload.setMobileNumber("9876543210");
		payload.setPassword("SecureP@ssw0rd!");
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
}
