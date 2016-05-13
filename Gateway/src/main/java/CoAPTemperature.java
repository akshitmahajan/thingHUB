import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.Utils;

public class CoAPTemperature {
	public static void main(String args[]) throws IOException
	{
		
    	CoapClient client;
		try {
			client = new CoapClient(new URI("coap://[aaaa::212:4b00:89ab:cdef]:5683/actuators/temp"));
		
    	CoapResponse response=client.get();
    	
    	
    	if (response!=null) 
    	{
    		System.out.println("YOU REQUESTED FOR GET METHOD");
    		String payload =response.getResponseText();
    		System.out.println(payload);
      	}
    	else 
    	{
    		System.out.println("Unable to read Temperature");
    	}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}
}
