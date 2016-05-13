import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.Utils;
import java.sql.*;
import java.util.Date;


public class ResourceDiscovery {

	
	public static void main(String args[]) throws IOException, SQLException {
		URI uri = null; 
		
							try {
				        				     
				        	String str=null;
				        	
				        	CoapClient client = new CoapClient(new URI("coap://coap.me:5683/.well-known/core"));
				        	CoapResponse response=client.get();
				        	
				        	
				        	if (response!=null) {
				        		System.out.println("YOU REQUESTED FOR GET METHOD");
				        		String payload =response.getResponseText();
				        System.out.println(payload);	    
				        	}
				        	else {
				        		System.out.println("No response received.");
				        	}
				        
					}	
				     catch(Exception e)
					{
				    	 
					}
				
					
}
}