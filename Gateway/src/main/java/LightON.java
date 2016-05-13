import java.io.*;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
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
import java.lang.reflect.InvocationTargetException;
import java.lang.ProcessBuilder;

public class LightON 
{
	public static void main(String[] args) throws InterruptedException,IllegalArgumentException, IOException, InvocationTargetException
	{
		try
		{
		String server = "aaaa::212:4b00:89ab:cdef";
		 String border_router="aaaa::212:7400:137e:6fa4";
		 InetAddress serverinet = Inet6Address.getByName(server);
	     InetAddress border_router_inet = Inet6Address.getByName(border_router);
	     System.out.println("Sending Ping Request to " + serverinet+" "+border_router_inet);
        if(border_router_inet.isReachable(800))
        {
         //Thread.sleep(1000);
        if(serverinet.isReachable(800))
        {
       	 
       	 //Thread.sleep(1000);
			 Request request=new Request(Code.PUT);
		         request.setURI("coap://[aaaa::212:4b00:89ab:cdef]:5683/actuators/relay?mode=on");
		     	 request.send().setConfirmable(false);
		         Response response;
	         response = request.waitForResponse();
            
            //System.out.println("YOU REQUESTED FOR PUT Request-1 METHOD");
            if(response != null)
		         {
             System.out.println(response.getPayloadString());
		      
            }
		     else
		     {
			  System.out.println("Some Error Encountered 1");
			  
		     }
	      }
   
         else 
	      {
		      System.out.println("NETWORK ERROR , Will try in 5 seconds again");
		      
	      }
         }
         else 
	      {
			System.err.println("Border-Router Issue");
			
	      }
		}
		catch (UnknownHostException e) 
		  {
          System.err.println("Network Disconnected");
         
        }
        catch(NullPointerException npe)
        {
  	      System.err.println("Network Disconnected");
  	      
        }
   

}
}