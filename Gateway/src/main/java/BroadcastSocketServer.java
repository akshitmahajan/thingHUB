import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class BroadcastSocketServer 
{   
	final static String INET_ADDR = "192.168.42.255";
    
	final static int PORT = 5001;

    	public static void main(String[] args) throws UnknownHostException, InterruptedException 
	{
        
		// Get the address that we are going to connect to.
        
    		InetAddress broadcastAddress = InetAddress.getByName(INET_ADDR);
    		//int port = 8888;
    	//	InetAddress ip=InetAddress.getLocalHost();
    	
    	//	System.out.println(ip);
     
        		// Open a new DatagramSocket, which will be used to send the data.
        
		try (DatagramSocket serverSocket = new DatagramSocket()) 
		{
			String msg = "Thingtronics|Gateway|v1.0|etc" ;
            
			// Create a packet that will contain the data (in the form of bytes) and send it.

DatagramPacket msgPacket = new DatagramPacket(msg.getBytes(),msg.getBytes().length, broadcastAddress, PORT);

System.out.println("Started Broadcasting Thingtronics to everyone");
			
          while(true)
		  {
           		
				serverSocket.send(msgPacket);
      			
				Thread.sleep(1000);
                
				//System.out.println(msg);
            			}
        		}catch (IOException ex) 
		{
            			ex.printStackTrace();
        		}
    	}
}