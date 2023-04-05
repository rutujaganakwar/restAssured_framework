package common_method;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class common_method_utilitize {

	public static void evidencefilecreator(String filename, String request, String response) throws IOException
	{
		File newTextFile = new File("D:\\restassuredevidence\\" + filename + ".txt");
		if(newTextFile.createNewFile())
		{
		FileWriter datawriter = new FileWriter(newTextFile);
		datawriter.write("request body is :\n" +request+ "\n\n");
		datawriter.write("response body is :\n" +response);
		datawriter.close();
		System.out.println("request and response body data save in :" +newTextFile.getName());
	}
	else
		{
			System.out.println(newTextFile.getName()+ "already exists take a backup of it");
		}
		
	}
}
