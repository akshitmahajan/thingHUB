import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class Broadcast 
{
       final  static String INET_ADDR = "192.168.0.255";
       static String Ip;
       static String firstThree;
	final static int PORT = 5000;

    	public static void main(String[] args) throws UnknownHostException, InterruptedException,SocketException 
	{



 NetworkInterface ni = NetworkInterface.getByName("wlan0");
        Enumeration<InetAddress> inetAddresses =  ni.getInetAddresses();


        while(inetAddresses.hasMoreElements()) {
            InetAddress ia = inetAddresses.nextElement();
            if(!ia.isLinkLocalAddress()) {
                System.out.println("IP: " + ia.getHostAddress());
                Ip  = ia.getHostAddress();
                System.out.println(Ip);
                firstThree = Ip.replaceFirst("\\d+$", "255");
                System.out.println(firstThree);
            }
        }


		InetAddress broadcastAddress = InetAddress.getByName(firstThree);

		try (DatagramSocket serverSocket = new DatagramSocket()) 
		{
			String msg = "ThingTronics|thingHUB|v0.1|Zones|appliancetype"+Ip;


			DatagramPacket msgPacket = new DatagramPacket(msg.getBytes(),msg.getBytes().length,broadcastAddress,PORT);

			System.out.println("Started Broadcasting Thingtronics to everyone");

        		while(true)
		  	{
				serverSocket.send(msgPacket);
				Thread.sleep(15000);
         		}
        	}catch (IOException ex) 
		{
			ex.printStackTrace();
        	}
    	}
}

