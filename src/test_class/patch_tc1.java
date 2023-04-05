package test_class;

import java.io.IOException;

import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import common_method.common_method_patch_api;

import common_method.common_method_utilitize;

import io.restassured.path.json.JsonPath;

import request_repository.Patch_Request_Repository;

public class patch_tc1{
	@Test
	
	public static void orchestrator() throws IOException
	{
		String response_body ="";
		int response_statuscode = 0;
		String baseURI = Patch_Request_Repository.baseURI();
   	    String resource = Patch_Request_Repository.resource();
   	    String req_body = Patch_Request_Repository.patch_request_tc1();
   	 
         for(int i=0; i<5; i++)
         {
        	 response_statuscode = common_method_patch_api.responsestatuscode_extractor(baseURI, resource, req_body);
        	 if(response_statuscode == 200)
        	 {
        		 response_body = common_method_patch_api.responsebody_extractor(baseURI, resource, req_body);
        		 responsebodyvalidator(response_body);
        		 break;
         	 }
        	 else
        	 {
        		 System.out.println("correct status code is not found in the iteration" +i);
        	 }
          }
 		common_method_utilitize.evidencefilecreator("patch_tc1", req_body, response_body);
         Assert.assertEquals(response_statuscode, 200);
	}
    public static void responsebodyvalidator(String response_body)
    {
    	//create jsonpath object to extract responsebody parameter
    	JsonPath jsp = new JsonPath(response_body);
    	// extract responsebody parameters
    			String res_name = jsp.getString("name");
    			String res_job = jsp.getString("job");
    			String res_updatedAt = jsp.getString("updatedAt");

    			//System.out.println("name : " + res_name + "\njob : " + res_job +  "\nupdatedAt : " + res_updatedAt);

    			// validate responsebody parameter
    			Assert.assertEquals(res_name, "morpheus");
    			Assert.assertEquals(res_job, "zion resident");

    			// extract date from createdAt parameter
    			//String actual_date = res_updatedAt.substring(0, 10);
    			//String current_date = LocalDate.now().toString(); // Create a date object
    			//Assert.assertEquals(actual_date, current_date);
    			//System.out.println("Actual DATE : " +actual_date+ "\n CURRENT DATE : " +current_date);

    		}

    	}


