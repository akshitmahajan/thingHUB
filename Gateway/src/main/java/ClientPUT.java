
import java.io.*;
import java.net.DatagramSocket;
import java.net.DatagramSocketImpl;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.Utils;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.CoAP.Code;
import org.eclipse.californium.core.coap.CoAP.Type;
import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.core.coap.Response;

import java.sql.*;


public class ClientPUT 
{
	public static void main(String args[]) throws IOException, SQLException, InterruptedException 
	{
		
		 String ipAddress = "aaaa::212:4b00:89ab:cdef";
		 String test="aaaa::212:7400:137e:6fa4";
		 InetAddress inet = Inet6Address.getByName(ipAddress);
	     InetAddress testinet = Inet6Address.getByName(test);
	     
	     while(true)
			{
	    	 System.out.println("Sending Ping Request to " + ipAddress+" "+testinet);
	    	 System.out.println(testinet.isReachable(5000) ? "Border-Router is reachable" : "Border-Router is NOT reachable");
	         System.out.println(inet.isReachable(5000) ? "Server is reachable" : "Server is NOT reachable");
	         
	 		if(inet.isReachable(5000) && testinet.isReachable(5000))
	 		{
				 Request request=new Request(Code.PUT);
 		         request.setURI("coap://[aaaa::212:4b00:89ab:cdef]:5683/actuators/relay?mode=on");
 		     	 request.send().setConfirmable(false);
 		         Response response;
		         response = request.waitForResponse();
	             //response.setType(Type.NON);
	             System.out.println("YOU REQUESTED FOR PUT Request-1 METHOD");
	         if(response != null)
 		     {
	              System.out.println(response.getPayloadString());
			      Thread.sleep(5000);
			           
            }
			 else
			 {
				  System.out.println("Some Error Encountered 1");
				  Thread.sleep(5000);
			 }
	         
	         Request request1=new Request(Code.PUT); 
	             request1.setURI("coap://[aaaa::212:4b00:89ab:cdef]:5683/actuators/relay?mode=off");
		     	 request1.send().setConfirmable(false);
		         response = request1.waitForResponse();
		        if(response != null)
	 		     {
		              System.out.println(response.getPayloadString());
				      Thread.sleep(5000);
	 		     }
		       else
				 {
					  System.out.println("Some Error Encountered 1");
					  Thread.sleep(5000);
				 }
	 
	         
	 		}
	 		else
	 		{
	 			System.out.println("Network Error");
	 			Thread.sleep(5000);
	 		}
				
			}
			
		}
}
		
		
		
	

		
		
		
		
		
			
			
		
	
	
	
			
	
	
		
		



	
			
		
	

	
	

