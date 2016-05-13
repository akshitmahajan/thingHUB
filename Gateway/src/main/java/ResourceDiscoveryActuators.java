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

public class ResourceDiscoveryActuators
{
	
	public static void main(String args[]) throws IOException, SQLException 
	{
		URI uri = null;
    	String str=null;
    	//CoapResponse response = client.get();
    	CoapClient client;
		try {
			client = new CoapClient(new URI("coap://[aaaa::212:4b00:89ab:cdef]:5683/.well-known/core"));
		
    	CoapResponse response=client.get();
    	   	if (response!=null)
    	   	{
    		System.out.println("YOU REQUESTED FOR GET METHOD");
    		String payload =response.getResponseText();
    	    String regex="\\<\\(.*?)\\>";
    		Pattern pattern = Pattern.compile("\\<.*?\\>");
    		Matcher matcher = pattern.matcher(payload);
    		String[] resources_list=new String[100];
    		int i=0;
    		int j=0;
    		for(i=0;matcher.find();i++)
    		{
    			String temp=matcher.group();
    			
    					String resource_list=temp.replaceAll("\\<|\\>", "");
    					resources_list[i]=resource_list;
    					System.out.println(resource_list);
    					System.out.println("coap://[aaaa::212:4b00:89ab:cdef]:5683"+resource_list);
    					j=j+1;
    		}
    		System.out.println("Total "+j+" services are present");
    		i=j;
    		/*
    		for(j=0;j<i;j++)
    		{
    			String resource=resources_list[j];
    			System.out.println(resource);
    			String actuators="/Shankar";
    			
    			CoapClient clientstatus;
				try {
					clientstatus = new CoapClient(new URI("coap://192.168.0.103:5683"+resource));
				
    			System.out.println("coap://coap.me:5683"+resource);
    			
    			CoapResponse responsemotes=clientstatus.get();
	        	
	        	String payloadmotesstatues =responsemotes.getResponseText();
	        	System.out.println(payloadmotesstatues);
	        	System.out.println("\n");
	        	
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	        	
    			
    			
    		}*/
    	    
    		    	    
    	
    }
    	else {
    		System.out.println("No response received.");
    	}
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
								
}
	
	


