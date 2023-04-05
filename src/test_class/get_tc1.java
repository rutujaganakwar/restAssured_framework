package test_class;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import common_method.common_method_get_api;

import common_method.common_method_utilitize;

import io.restassured.path.json.JsonPath;

import request_repository.Get_Request_Repository;

public class get_tc1{
	@Test
	
	public static void orchestrator() throws IOException
	{
		String response_body ="";
		int response_statuscode = 0;
		String baseURI = Get_Request_Repository.baseURI();
   	    String resource = Get_Request_Repository.resource();   	
         for(int i=0; i<5; i++)
         {
        	 response_statuscode = common_method_get_api.responsestatuscode_extractor(baseURI, resource);
        	 if(response_statuscode == 200)
        	 {
        		 response_body = common_method_get_api.responsebody_extractor(baseURI, resource);
        		 responsebodyvalidator(response_body);
        		 break;
         	 }
        	 else
        	 {
        		 System.out.println("correct status code is not found in the iteration" +i);
        	 }
          }
 		common_method_utilitize.evidencefilecreator("get_tc1", null, response_body);
         Assert.assertEquals(response_statuscode, 200);
	}
    public static void responsebodyvalidator(String response_body)
    {
    	//create jsonpath object to extract responsebody parameter
    	JsonPath jsp = new JsonPath(response_body);
                int array_length = jsp.getInt("data.size()");
                System.out.println("The data array length is" +array_length);
                int exp_id[] = {7,8,9,10,11,12};
    	        String exp_email[] = {"michael.lawson@reqres.in","lindsay.ferguson@reqres.in","tobias.funke@reqres.in","byron.fields@reqres.in","george.edwards@reqres.in","rachel.howell@reqres.in",};
    			String exp_fname[] = {"Michael","Lindsay", "Tobias","Byron", "George","Rachel",};
    			String exp_lname[] = {"Lawson","Ferguson","Funke","Fields","Edwards","Howell",};
    			String exp_avatar[] = {"https://reqres.in/img/faces/7-image.jpg","https://reqres.in/img/faces/8-image.jpg","https://reqres.in/img/faces/9-image.jpg","https://reqres.in/img/faces/10-image.jpg","https://reqres.in/img/faces/11-image.jpg", "https://reqres.in/img/faces/12-image.jpg"};
    			System.out.println("below responce is show for get request:");
    			
    			for(int i=0; i < array_length; i++)
    			{
    				
    				//extractc responsebody parameter
    				int res_id = jsp.getInt("data["+i+"].id");
    				String res_fname = jsp.getString("data["+i+"].first_name");
    				String res_lname = jsp.getString("data["+i+"].last_name");
    				String res_email = jsp.getString("data["+i+"].email");
    				String res_avatar = jsp.getString("data["+i+"].avatar");
    				System.out.println(res_id);
    				System.out.println(res_fname);
    				System.out.println(res_lname);
    				System.out.println(res_email);
    				System.out.println(res_avatar);
            
    	
				// validate responsebody parameter
    			Assert.assertEquals(res_id,exp_id[i]);
    			Assert.assertEquals(res_fname,exp_fname[i]);
    			Assert.assertEquals(res_lname,exp_lname[i]);
    			Assert.assertEquals(res_email,exp_email[i]);    
    		    Assert.assertEquals(res_id, exp_id[i]);
    		}

    	}
 }


