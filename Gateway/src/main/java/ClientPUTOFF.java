
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


public class ClientPUTOFF 
{
	public static void main(String args[]) throws IOException, SQLException 
	{
		
			
				CoapClient client; 
				
					try {
						client = new CoapClient(new URI("coap://[aaaa::212:4b00:89ab:cdef]:5683/actuators/relay"));
					
				 
				String str2="?mode=off";
				CoapResponse response ;//= client.put(str2,0);
				response=client.put(str2,0);
				System.out.println("YOU REQUESTED FOR PUT METHOD");
				if (response!=null) {
					
					System.out.println(response.getResponseText());
				}
				else {
					System.out.println(str2);
					System.out.println("No response received.off");
				}
				
				
					} catch (URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
	}
}
				
				
	
