import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;import java.net.NetworkInterface;
import java.net.SocketException;


public class ConnectionChecking {
static String Ip;
static int i=0;
    public static void main(String args[]) {



        NetworkInterface ni;
		try {

			ni = NetworkInterface.getByName("wlan0");
            Enumeration<InetAddress> inetAddresses =  ni.getInetAddresses();

        while(inetAddresses.hasMoreElements()) 
        {
        	
            InetAddress ia = inetAddresses.nextElement();
            if(!ia.isLinkLocalAddress()) 
            {
               i++;
                Ip  = ia.getHostAddress();
             }
        }
        if(i!=0)
        {
        	System.out.println(Ip);
        	//start the services 
        	
        }
        else
        {
        	System.out.println("Wifi Not Connected ");
        	//Go to Access Point Program 
        }
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (NullPointerException e) {
			if(i!=0)
	        {
	        	System.out.println(Ip);
	        	//start the services 
	        	
	        }
	        else
	        {
	        	System.out.println("Wifi Not Connected ");
	        	
	        	//Go to Access Point Program 
	        }
		}
}
}
