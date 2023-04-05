package request_repository;

import java.io.IOException;
import java.util.ArrayList;

import common_method.getData;

public class Patch_Request_Repository {
	
	public static String baseURI()
	{
		String baseURI = "https://reqres.in/";
		return baseURI ;
	}
	public static String resource()
	{
		String resource = "api/users/2";
		return resource ;
	}
	
	public static String patch_request_tc1() throws IOException
	{
		ArrayList<String> data = getData.getDataExcel("patch_data","Tc1" );
		String Name = data.get(2);
		String Job = data.get(3);
		String requestbody="{\r\n"
				+ "    \"name\": \""+Name+"\",\r\n"
				+ "    \"job\": \""+Job+"\"\r\n"
				+ "}";
		return requestbody;
	}
}

