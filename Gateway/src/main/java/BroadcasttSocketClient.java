import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class BroadcasttSocketClient 
{
	final static String INET_ADDR = "192.168.0.255";



	final static int PORT = 5000;

    public static void main(String[] args) throws UnknownHostException 
	{
        		// Get the address that we are going to connect to.
        
		InetAddress address = InetAddress.getByName(INET_ADDR);
		byte[] buf = new byte[256];
      	try (MulticastSocket clientSocket = new MulticastSocket(PORT))
		{
        
			clientSocket.getBroadcast();
	        String test="Thingtronics";
		    while (true) 
            { 
			   DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
               clientSocket.receive(msgPacket);
               String msg = new String(buf, 0, buf.length);
               String s=msg.trim();
    	       if(s.equals(test))
    	       {
	           System.out.println(msg);
	           break;
    	       }
			}
		   System.out.println("Came Out");
		   //Logic Should Start from here
		   Runtime rTime = Runtime.getRuntime();
			String url = "D:/hi.html";
			String browser ="C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe ";
			Process pc;
			try {
				pc = rTime.exec(browser + url);
			
			pc.waitFor();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
	      }catch (IOException ex) 
           {
			ex.printStackTrace();
	       }
  }
}