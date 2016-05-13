/*******************************************************************************
 * Copyright (c) 2014 Institute for Pervasive Computing, ETH Zurich and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 * 
 * The Eclipse Public License is available at
 *    http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *    http://www.eclipse.org/org/documents/edl-v10.html.
 * 
 * Contributors:
 *    Matthias Kovatsch - creator and main architect
 ******************************************************************************/
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


public class GET {

	/*
	 * Application entry point.
	 * 
	 */	
	public static void main(String args[]) throws IOException, SQLException {
		
		
		URI uri = null; // URI parameter of the request
		
		if (args.length > 0) {
			
			System.out.println("Select 1 For GET Method \nSelect 2 For POST Method \nSelect 3 For PUT Method");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String opt=in.readLine();
			//CoapClient client = new CoapClient(new URI("coap://coap.me:5683/.well-known/core"));
			//System.out.println(client);
			
			Connection c = null;
		    Statement stmt = null;
			
			switch(opt)		
				{
				case "1": 
					try {
				        while (true) {
				     
				        	String str=null;
				        	//CoapResponse response = client.get();
				        	CoapClient client = new CoapClient(new URI("coap://192.168.0.102:5683/.well-known/core"));
				        	CoapResponse response=client.get();
				        	
				        	
				        	if (response!=null) {
				        		System.out.println("YOU REQUESTED FOR GET METHOD");
				        		String payload =response.getResponseText();
				        	    //System.out.println(payload);
				        		//System.out.println(response.getCode());
				        		//System.out.println(response.getOptions());
				        		//System.out.println(response.getResponseText());
				        		//System.out.println(response.isSuccess());
				        		//System.out.println(response.getClass());
				        		//System.out.println("\nADVANCED\n");
				        		 //access advanced API with access to more details through .advanced()
				        		//System.out.println(Utils.prettyPrint(response));
				        		/*FileWriter fw=new FileWriter("C:\\receivedresponse.txt",true);
				        		BufferedWriter bw=new BufferedWriter(fw);
				        		bw.write(response.getResponseText()); 
				        		bw.newLine();
				        		bw.close();*/
				        		
				        	    
				        	    
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
				        					//System.out.println(resource_list);
				        					j=j+1;
				        		}
				        		System.out.println("Total "+j+" services are present");
				        		i=j;
				        		for(j=0;j<i;j++)
				        		{
				        			String resource=resources_list[j];
				        			System.out.println(resource);
				        			CoapClient clientstatus = new CoapClient(new URI("coap://192.168.0.101:5683"+resource));
						        	CoapResponse responsemotes=clientstatus.get();
						        	if(responsemotes!=null)
						        	{
						        	String payloadmotesstatues =responsemotes.getResponseText();
						        	System.out.println(payloadmotesstatues);
						        	System.out.println("\n");
						        	}
				        		}
				        	    
				        		Thread.sleep(300000);
				        	    
				        	
				        }
				        	else {
				        		System.out.println("No response received.");
				        	}
				        }
					}
				     catch(Exception e)
					{
				    	 
					}
					
				    
				break;
				case "2":
				CoapClient client;
				try {
					client = new CoapClient(new URI("coap://coap.me:5683/.well-known/core"));
				
					System.out.println("What Payload you want to send");
					Scanner s = new Scanner(System.in);
					String str1=s.nextLine();
					System.out.println("YOU REQUESTED FOR POST METHOD");
					//CoapResponse response1 = client.post(str1,0);
					CoapResponse response;response = client.post(str1,0);
					if (response!=null) {
				
						System.out.println(response.getCode());
						System.out.println(response.getOptions());
						System.out.println(response.getResponseText());
						System.out.println(response.isSuccess());
						System.out.println(response.getClass());
						System.out.println("\nADVANCED\n");
						FileWriter fw=new FileWriter("C:\\receivedresponse.txt",true);
						BufferedWriter bw=new BufferedWriter(fw);
						bw.write(response.getResponseText()); 
						bw.newLine();
						bw.close();
						// 	access advanced API with access to more details through .advanced()
						System.out.println(Utils.prettyPrint(response));
					}
					else {
						System.out.println("No response received.");
					}
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			break;
			/*
			case "3":
				CoapClient client1 = new CoapClient(new URI("coap://coap.me:5683/.well-known/core"));
					System.out.println("What Payload you want to send");
					Scanner s1 = new Scanner(System.in);
					String str2=s1.nextLine();
					//CoapResponse response2 = client.put(str2,0);
					response=client1.put(str2,0);
					System.out.println("YOU REQUESTED FOR PUT METHOD");
			if (response!=null) {
				
				System.out.println(response.getCode());
				System.out.println(response.getOptions());
				System.out.println(response.getResponseText());
				System.out.println(response.isSuccess());
				System.out.println(response.getClass());
				System.out.println("\nADVANCED\n");
				// access advanced API with access to more details through .advanced()
				System.out.println(Utils.prettyPrint(response));
				FileWriter fw=new FileWriter("receivedresponse.txt",true);
				BufferedWriter bw=new BufferedWriter(fw);
				bw.write(response.getResponseText()); 
				bw.newLine();
				bw.close();
			}
			else {
				System.out.println("No response received.");
			}
			break; 
			*/
					case "4":
						
					    try {
					    	
					      Class.forName("org.sqlite.JDBC");
					      c = DriverManager.getConnection("jdbc:sqlite:IOT.db");
					      System.out.println("Opened database successfully");

					      stmt = c.createStatement();
					      String sql = "CREATE TABLE STATUS " +
					                   "(ADDRESS CHAR(4500))"; 
					      stmt.executeUpdate(sql);
					      stmt.close();
					      c.close();
					    } catch ( Exception e ) {
					      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
					      System.exit(0);
					    }
					    System.out.println("Table created successfully");
				    break;
				case "5":
					try {
						
						CoapClient client1 = new CoapClient(new URI("coap://coap.me:5683/.well-known/core"));
			        	CoapResponse response=client1.get();
			        	String Address=response.getResponseText();
						
					Class.forName("org.sqlite.JDBC");
				      c = DriverManager.getConnection("jdbc:sqlite:IOT.db");
				      c.setAutoCommit(false);
				      System.out.println("Opened database successfully");

				      stmt = c.createStatement();
				      String sql = "INSERT INTO STATUS (ADDRESS)" +
				                   "VALUES ('"+Address+"');"; 
				      stmt.executeUpdate(sql);
				      stmt.close();
				      c.commit();
				      c.close();
				    } catch ( Exception e ) {
				      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				      System.exit(0);
				    }
				    System.out.println("Records created successfully");
					
				    break;
				case "6":
					
					try {
					      Class.forName("org.sqlite.JDBC");
					      c = DriverManager.getConnection("jdbc:sqlite:IOT.db");
					      c.setAutoCommit(false);
					      System.out.println("Opened database successfully");

					      stmt = c.createStatement();
					      ResultSet rs = stmt.executeQuery( "SELECT * FROM STATUS;" );
					      while ( rs.next() ) {
					         String  address = rs.getString("address");
					         System.out.println( "ADDRESS = " + address );
					         
					         System.out.println();
					      }
					      rs.close();
					      stmt.close();
					      c.close();
					    } catch ( Exception e ) {
					      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
					      System.exit(0);
					    }
					
				    System.out.println("Operation done successfully");
				    break;
				   
				default:System.out.println("Unknown requested unable to process \nRetry ");
				break;
				 
				}

			/*
			if (response!=null) {
				
				System.out.println(response.getCode());
				System.out.println(response.getOptions());
				System.out.println(response.getResponseText());
				System.out.println(response.isSuccess());
				System.out.println(response.getClass());
				System.out.println("\nADVANCED\n");
				// access advanced API with access to more details through .advanced()
				System.out.println(Utils.prettyPrint(response));
				
			} 
			
			else {
				System.out.println("No response received.");
			}
			*/
		} 
		
		else {
			// display help
			System.out.println("Californium (Cf) GET Client");
			System.out.println("(c) 2014, Institute for Pervasive Computing, ETH Zurich");
			System.out.println();
			System.out.println("Usage: " + GET.class.getSimpleName() + " URI");
			System.out.println("  URI: The CoAP URI of the remote resource to GET");
		}
	}

}

