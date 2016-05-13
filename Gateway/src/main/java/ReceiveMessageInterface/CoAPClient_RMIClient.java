package ReceiveMessageInterface;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 


public class CoAPClient_RMIClient extends HttpServlet  {

	  protected void doGet(HttpServletRequest request,
              HttpServletResponse response)
 throws ServletException, IOException {

doPost(request, response);
}

protected void doPost(HttpServletRequest request,
               HttpServletResponse response)
 throws ServletException, IOException {
//
String url = request.getRequestURL().toString();

String queryString = request.getQueryString();

String uri = request.getRequestURI();
String query = request.getQueryString();
PrintWriter pw = response.getWriter();
pw.print("Query: " + query);
SendingQuery(query);
}
public void SendingQuery(String query)
{
	ReceiveMessageInterface rmiServer;
    Registry registry;
    String serverAddress="localhost";
    String serverPort="3000";
    
    System.out.println("sending "+query+" to "+serverAddress+":"+serverPort);
    try{
       
        registry=LocateRegistry.getRegistry(serverAddress,
            (new Integer(serverPort)).intValue()
        );
        // look up the remote object
        rmiServer=
           (ReceiveMessageInterface)(registry.lookup("rmiServer"));
        // call the remote method
        rmiServer.receiveMessage(query);
    }
    catch(RemoteException e){
        e.printStackTrace();
    }
    catch(NotBoundException e){
        e.printStackTrace();
    }
}

	static public void main(String args[])
    {
       ReceiveMessageInterface rmiServer;
       Registry registry;
       String serverAddress="localhost";
       String serverPort="3000";
       String text="hello";
       System.out.println("sending "+text+" to "+serverAddress+":"+serverPort);
       try{
          
           registry=LocateRegistry.getRegistry(
               serverAddress,
               (new Integer(serverPort)).intValue()
           );
           // look up the remote object
           rmiServer=
              (ReceiveMessageInterface)(registry.lookup("rmiServer"));
           // call the remote method
           rmiServer.receiveMessage(text);
       }
       catch(RemoteException e){
           e.printStackTrace();
       }
       catch(NotBoundException e){
           e.printStackTrace();
       }
    }
	
}
