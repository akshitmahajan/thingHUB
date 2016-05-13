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
package org.eclipse.californium.examples;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.Utils;


public class GETClient {

	/*
	 * Application entry point.
	 * 
	 */	
	public static void main(String args[]) throws URISyntaxException, IOException {
		
		URI uri = null; // URI parameter of the request
		
		if (args.length > 0) {
			
			// input URI from command line arguments
			try {
				uri = new URI(args[0]);
			} catch (URISyntaxException e) {
				System.err.println("Invalid URI: " + e.getMessage());
				System.exit(-1);
			}
			
			
			
			System.out.println("Select 1 For GET Method \nSelect 2 For POST Method \n Select3 For PUT Method");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String opt=in.readLine();
			
				
			switch(opt)		
				{
				case "1":CoapClient client = new CoapClient(new URI("coap://192.168.0.102:5683/Shankar")); 
				String str=null;
				CoapResponse response = client.get();
				System.out.println("YOU REQUESTED FOR GET METHOD");
				if (response!=null) {
					
					System.out.println(response.getCode());
					System.out.println(response.getOptions());
					System.out.println(response.getResponseText());
					System.out.println(response.isSuccess());
					System.out.println(response.getClass());
					System.out.println("\nADVANCED\n");
					// access advanced API with access to more details through .advanced()
					System.out.println(Utils.prettyPrint(response));
					FileWriter fw=new FileWriter("C:\\receivedresponse.txt",true);
					BufferedWriter bw=new BufferedWriter(fw);
					bw.write(response.getResponseText()); 
					bw.newLine();
					bw.close();

					
				}
				else {
					System.out.println("No response received.");
				}
				break;
				case "2":
				
					CoapClient client1 = new CoapClient(new URI("coap://192.168.0.101:5683/Shankar")); 
					String str1=null;
					System.out.println("YOU REQUESTED FOR POST METHOD");
					CoapResponse response1 = client1.post(str1,0);
				
			if (response1!=null) {
				
				System.out.println(response1.getCode());
				System.out.println(response1.getOptions());
				System.out.println(response1.getResponseText());
				System.out.println(response1.isSuccess());
				System.out.println(response1.getClass());
				System.out.println("\nADVANCED\n");
				FileWriter fw=new FileWriter("C:\\receivedresponse.txt",true);
				BufferedWriter bw=new BufferedWriter(fw);
				bw.write(response1.getResponseText()); 
				bw.newLine();
				bw.close();
				// access advanced API with access to more details through .advanced()
				System.out.println(Utils.prettyPrint(response1));
			}
			else {
				System.out.println("No response received.");
			}
			break;
			
				case "3":
					CoapClient client2 = new CoapClient(new URI("coap://192.168.0.101:5683/Shankar")); 
					String str2=null;
					CoapResponse response2 = client2.put(str2,0);
					System.out.println("YOU REQUESTED FOR PUT METHOD");
			if (response2!=null) {
				
				System.out.println(response2.getCode());
				System.out.println(response2.getOptions());
				System.out.println(response2.getResponseText());
				System.out.println(response2.isSuccess());
				System.out.println(response2.getClass());
				System.out.println("\nADVANCED\n");
				// access advanced API with access to more details through .advanced()
				System.out.println(Utils.prettyPrint(response2));
				FileWriter fw=new FileWriter("C:\\receivedresponse.txt",true);
				BufferedWriter bw=new BufferedWriter(fw);
				bw.write(response2.getResponseText()); 
				bw.newLine();
				bw.close();
			}
			else {
				System.out.println("No response received.");
			}
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
		} else {
			// display help
			System.out.println("Californium (Cf) GET Client");
			System.out.println("(c) 2014, Institute for Pervasive Computing, ETH Zurich");
			System.out.println();
			System.out.println("Usage: " + GETClient.class.getSimpleName() + " URI");
			System.out.println("  URI: The CoAP URI of the remote resource to GET");
		}
	}

}
