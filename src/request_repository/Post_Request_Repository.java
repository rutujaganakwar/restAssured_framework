package request_repository;

import java.io.IOException;
import java.util.ArrayList;

import common_method.getData;

public class Post_Request_Repository {
	
	public static String baseURI()
	{
		String baseURI = "https://reqres.in/";
		return baseURI ;
	}
	public static String resource()
	{
		String resource = "api/users";
		return resource ;
	}
	public static String post_request_tc1() throws IOException
	{
		ArrayList<String> data = getData.getDataExcel("post_data","Tc1" );
		String Name = data.get(2);
		String Job = data.get(3);
		String requestbody="{\r\n"
				+ "    \"name\": \""+Name+"\",\r\n"
				+ "    \"job\": \""+Job+"\"\r\n"
				+ "}";
		return requestbody;
	}
}
	
   /* public static String post_request_tc2()
    {
	String requestbody="{\r\n"
			+ "    \"name\": \"morpheus_change\",\r\n"
			+ "    \"job\": \"leader\"\r\n"
			+ "}";
	return requestbody;
    }
}*/