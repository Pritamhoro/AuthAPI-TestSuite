package com.APIAutomation.base;

import io.restassured.response.Response;

public class AuthenticationService extends Base
{
	private static final String BASE_PATH="/api/auth/";
	
	public static Response SingUp(Object payload)
	{
		return POST(payload, BASE_PATH+"signup");
	}
}
