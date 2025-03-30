package com.APIAutomation.base;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Base 
{
	private static final String BASE_URI="http://64.227.160.186:8080";
	private static RequestSpecification requestSpecification;
	
	public Base()
	{
		requestSpecification=given().baseUri(BASE_URI)
				.header("Content-Type","application/json")
				.contentType(ContentType.JSON);
	}
	
	public static Response POST(Object payload,String endpoint)
	{
		return requestSpecification.body(payload).when().post(endpoint);
	}
}
