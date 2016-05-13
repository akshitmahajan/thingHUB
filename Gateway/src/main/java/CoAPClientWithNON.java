
import java.io.*;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
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
import java.lang.RuntimeException;

public class CoAPClientWithNON {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws InterruptedException,IllegalArgumentException, IOException
	{
		while(true) 
		{		
		     String ipAddress = "134.102.218.18";
			 //String ipAddress = "[aaaa::212:4b00:89ab:cdef]";
			 //String border_router="[aaaa::212:7400:137e:6fa4]";
		     String test="192.168.0.1";
			 InetAddress inet = InetAddress.getByName(ipAddress);
		     InetAddress testinet = InetAddress.getByName(test);
		     //InetAddress inet_bouder_router = InetAddress.getByName(border_router);
	         System.out.println("Sending Ping Request to " + ipAddress+" "+testinet);
	         //System.out.println(inet.isSiteLocalAddress());
	         System.out.println(testinet.isReachable(0) ? "Gateway is reachable" : "Gateway is NOT reachable");
	         System.out.println(inet.isReachable(3000) ? "Server is reachable" : "Server is NOT reachable");
	       if(inet.isReachable(3000) && testinet.isReachable(3000))
            {		
				 Request request=new Request(Code.PUT);
  		         request.setURI("coap://coap.me:5683/large-update?mode=on");
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
							
		   }
	    
	     else 
		 {
			System.out.println("NETWORK ERROR , Will try in 5 seconds again");
			Thread.sleep(5000);
		 }
	    /*
	        String ipAddress1 = "134.102.218.18";
	        InetAddress inet1 = InetAddress.getByName(ipAddress1);
		    
		    System.out.println("Sending Ping Request to " + ipAddress1+"\t"+inet1.isReachable(5000));
		    //System.out.println(inet1.isSiteLocalAddress());
		    System.out.println(testinet.isReachable(5000) ? "Gateway is reachable" : "Gateway is NOT reachable");
		    System.out.println(inet1.isReachable(5000) ? "Server is reachable" : "Server is NOT reachable");
		    		
		if(inet1.isReachable(5000) && testinet.isReachable(5000))
		{
		    	
			 System.out.println("YOU REQUESTED FOR PUT Request-2 METHOD");
			 Request request1=new Request(Code.PUT);
			 request1.setURI("coap://coap.me:5683/sink?mode=on");
			 request1.send().setConfirmable(false);
			
			 Response response1;
			 
			 response1 = request1.waitForResponse();
			 // response.setType(Type.NON);
			 if(response1!=null)
			 {
			 System.out.println(response1.getPayloadString());
			 Thread.sleep(5000);
			 }
			 else 
			 {
				System.out.println("Some Error Occured 2");
				Thread.sleep(5000);
			 }
			
		}
		else
		{
		    	System.out.println("NETWORK ERROR , Will try in 5 seconds again-2");
				Thread.sleep(5000);
		}
		*/
	 }
  }
}