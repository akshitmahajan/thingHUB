
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.Utils;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import java.sql.*;
import java.util.Date;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class MySQLForSendingIPToWebApp {

	/*
	 * Application entry point.
	 * 
	 */	
	public static void main(String args[]) throws IOException, SQLException {
		Connection c = null;
        Statement stmt = null;
		/*
			              
			              try {
					      Class.forName("com.mysql.jdbc.Driver");
					      c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mapping","root","raspberry");
					      //System.setProperty("java.io.pi", "F:\\Home Automation\\"); 
					      System.out.println("Opened database successfully");

					      stmt = c.createStatement();
					      String sql = "CREATE TABLE IP (IpAddress CHAR(50),Resources CHAR(10), Zone CHAR(50),ResourceType CHAR(50) )"; 
					      stmt.executeUpdate(sql);
					      stmt.close();
					      c.close();
					    } catch ( Exception e ) {
					      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
					      System.exit(0);
					    }
					      System.out.println("Table created successfully");
					      
			*/
		//String url = "http://[aaaa::212:7400:137e:6fa4]";
		/*String url = "http://www.google.com";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");

		
		//con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		*/
		//System.out.println(response.toString());
		
		final Pattern TAG_REGEX = Pattern.compile("<pre>(aaaa....................)");
		//String ServerIPAddress=response.toString();
		String ServerIPAddress= "<html><head><title>ContikiRPL</title></head><body>Neighbors<pre></pre>Routes<pre>aaaa::212:4b00:89ab:cdef</pre><pre>aaaa::212:4b00:89ab:efgh</pre><pre>aaaa::212:4b00:89ab:defg</pre><pre>fe80::212:4b00:89ab:cdef</pre></body></html>";
		final List<String> tagValues = new ArrayList<String>();
	    final Matcher matcher = TAG_REGEX.matcher(ServerIPAddress);
	     
	    while (matcher.find()) {
	        tagValues.add(matcher.group(1));
	    }
	    try {
			Class.forName("com.mysql.jdbc.Driver");
		
    	c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mapping","root","raspberry");  
    	System.out.println("Opened database successfully");
    	c.setAutoCommit(false);
	    } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		
	for(String s:tagValues)
	{ 
		System.out.println(s);
		stmt = c.createStatement();
   	 String sql = "INSERT INTO IP (IPAddress) VALUES('"+s+"');"; 
   	
     stmt.executeUpdate(sql); 
     stmt.close();
			
	}
	
    c.commit();
    c.close();
		
	}
}
			        	