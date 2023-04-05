package test_class;

import java.io.IOException;

import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import common_method.common_method_api;

import common_method.common_method_utilitize;

import io.restassured.path.json.JsonPath;

import request_repository.Post_Request_Repository;

public class post_tc1{
	@Test
	public static void orchestrator() throws IOException
	{
		String response_body ="" ;
		int response_statuscode = 0;
		String baseURI = Post_Request_Repository.baseURI();
   	    String resource = Post_Request_Repository.resource();
   	    String req_body = Post_Request_Repository.post_request_tc1();
   	 
         for(int i=0; i<5; i++)
         {
        	 response_statuscode = common_method_api.responsestatuscode_extractor(baseURI, resource, req_body);
        	 if(response_statuscode == 201)
        	 {
        		 response_body = common_method_api.responsebody_extractor(baseURI, resource, req_body);
        		 responsebodyvalidator(response_body);
        		 break;
         	 }
        	 else
        	 {
        		 System.out.println("correct status code is not found in the iteration" +i);
        	 }
          }
 		common_method_utilitize.evidencefilecreator("post_tc1", req_body, response_body);
         Assert.assertEquals(response_statuscode,201);
	}
    public static void responsebodyvalidator(String response_body)
    {
    	//create jsonpath object to extract responsebody parameter
    	JsonPath jsp = new JsonPath(response_body);
    	// extract responsebody parameters
    			String res_name = jsp.getString("name");
    			String res_job = jsp.getString("job");
    			String res_id = jsp.getString("id");
    			String res_createdAt = jsp.getString("createdAt");

    			//System.out.println("name : " + res_name + "\njob : " + res_job + "\nid : " + res_id + "\ncreatedAt : " + res_createdAt);

    			// validate responsebody parameter
    			Assert.assertEquals(res_name, "morpheus");
    			Assert.assertEquals(res_job, "leader");
    			Assert.assertNotNull(res_id, "assertion error , id parameter is null");

    			// extract date from createdAt parameter
    			String actual_date = res_createdAt.substring(0, 10);
    			String current_date = LocalDate.now().toString(); // Create a date object
    			Assert.assertEquals(actual_date, current_date);
    			//System.out.println("Actual DATE : " +actual_date+ "\n CURRENT DATE : " +current_date);

    		}

    	}
