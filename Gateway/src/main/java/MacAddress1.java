import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class MacAddress1 
{
       final  static String INET_ADDR = "192.168.0.255";
       static String Ip;
       static String firstThree;
       static String firstThree1="192.168.0.111";
	final static int PORT = 5555;

    	public static void main(String[] args) throws UnknownHostException, InterruptedException,SocketException 
	{



    		 NetworkInterface ni = NetworkInterface.getByName("wlan0");
             Enumeration<InetAddress> inetAddresses =  ni.getInetAddresses();


         while(inetAddresses.hasMoreElements()) {
             InetAddress ia = inetAddresses.nextElement();
             if(!ia.isLinkLocalAddress()) {
                 System.out.println("IP: " + ia.getHostAddress());
                 Ip  = ia.getHostAddress();
 }
 }
       System.out.println(Ip);


     InetAddress address = InetAddress.getByName(Ip);

     NetworkInterface niMac = NetworkInterface.getByInetAddress(address);
     byte[] mac = niMac.getHardwareAddress();
     StringBuilder sb = new StringBuilder();
     for (int i = 3; i < mac.length; i++) 
     {
       sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "" : "")); 
     }
     String macAdd = new String(sb);
    macAdd="thingHUB-"+macAdd;
     System.out.println("\n"+macAdd);       

		InetAddress broadcastAddress = InetAddress.getByName(firstThree1);

		try (DatagramSocket serverSocket = new DatagramSocket()) 
		{
			String msg = "ThingTronics|"+macAdd+"|v0.1|Zones|appliancetype|applianceName";


			DatagramPacket msgPacket = new DatagramPacket(msg.getBytes(),msg.getBytes().length,broadcastAddress,PORT);

			System.out.println("Started Broadcasting Thingtronics to everyone");

        		while(true)
		  	{
				serverSocket.send(msgPacket);
				Thread.sleep(3000);
         		}
        	}catch (IOException ex) 
		{
			ex.printStackTrace();
        	}
    	}
}
