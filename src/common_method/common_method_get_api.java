package common_method;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

public class common_method_get_api {
	public static int responsestatuscode_extractor(String baseURI, String resource)
	{
		RestAssured.baseURI =baseURI;
		int response_statuscode = given().when().get(resource).then().extract().statusCode();
		return response_statuscode ;
		
		
	}
	public static String responsebody_extractor(String baseURI, String resource)
	{
		RestAssured.baseURI =baseURI;
		String response_body = given().when().get(resource).then().extract().response().asString();
		return response_body ;
		
		
	}

}

