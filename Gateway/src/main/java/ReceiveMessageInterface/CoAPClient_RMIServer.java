package ReceiveMessageInterface;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import java.net.InetAddress;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.core.coap.Response;
import org.eclipse.californium.core.coap.CoAP.Code;
	
	@SuppressWarnings("serial")
	public class CoAPClient_RMIServer extends java.rmi.server.UnicastRemoteObject implements ReceiveMessageInterface
	{
	int thisPort=3000;
    String thisAddress;
    Registry registry;//rmi registry is the lookup for remote object
	public CoAPClient_RMIServer() throws RemoteException
	{

        try{

            // get the address of this host.

            thisAddress= (InetAddress.getLocalHost()).toString();

        }

        catch(Exception e){

            throw new RemoteException("can't get inet address.");

        }

        thisPort=3000;  

        System.out.println("this address="+thisAddress+",port="+thisPort);

        try{

        // create the registry and bind the name and object.

            registry = LocateRegistry.createRegistry( thisPort );
            registry.rebind("rmiServer", this);
            System.out.println(registry);

        }

        catch(RemoteException e){

        throw e;

        }

    }
	@Override
	public void receiveMessage(String query) throws RemoteException 
	{
		System.out.println(query);
		 Request request=new Request(Code.PUT);
		         request.setURI("coap://[aaaa::212:4b00:89ab:cdef]:5683/"+query);
		         System.out.println(query);
		         request.send().setConfirmable(false);
		         System.out.println(query);
		         Response response;
	         try {
				response = request.waitForResponse();
				System.out.println(query);
            
            //System.out.println("YOU REQUESTED FOR PUT Request-1 METHOD");
            if(response != null)
		    {
              System.out.println(response.getPayloadString());
              System.out.println(query); 
            }
		     else
		     {
			  System.out.println("Some Error Encountered 1");
			  
		     }
	 
		
		} 
	    catch (InterruptedException e) 
	    {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(NullPointerException npe)
        {
  	      System.err.println("Network Disconnected");
  	      
        }
   
	}
	
	
	public static void main(String[] args) 
	{
		try {
			new CoAPClient_RMIServer();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
}
	}

