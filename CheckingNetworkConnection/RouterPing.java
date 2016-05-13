import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class RouterPing {
	static int wifitimeout;
	static String Ip;
    static String firstThree;
	final static int PORT = 50000;
	public static void main(String[] args) throws UnknownHostException, InterruptedException,SocketException 
	{
	
	 Thread.sleep(3000);
	
     while(true)
	 {
          NetworkInterface ni = NetworkInterface.getByName("wlan0");
          if(ni!=null)
          {
           Enumeration<InetAddress> inetAddresses =  ni.getInetAddresses();
           while(inetAddresses.hasMoreElements())
           {
            InetAddress ia = inetAddresses.nextElement();
            if(!ia.isLinkLocalAddress()) 
            {
              System.out.println("IP: " + ia.getHostAddress());
              Ip  = ia.getHostAddress();
              System.out.println(Ip);
              firstThree = Ip.replaceFirst("\\d+$", "1");
              System.out.println(firstThree);
            }
           }
	   if(firstThree!=null)
	   {
	      InetAddress broadcastAddress = InetAddress.getByName(firstThree);
           try
	   {
			  if(broadcastAddress.isReachable(1300))
			  {
				System.out.println("Connected");
                                //Connected to Router So again check the connection after 20 mins
				Thread.sleep(1200000);
			  }
			  else
			  {
				 wifitimeout++;
				 System.out.println("Not Connected \t "+wifitimeout);
                                 //Connection to Router is absent soo check for 5 times every min
				 Thread.sleep(60000);
			  }
			  if(wifitimeout==5)
			  {
				 //file write make-interfaces/interfaces to /etc/network/interfaces
				 String make_interfaces ="auto lo \n iface lo inet loopback \n auto eth0 \n allow-hotplug eth0 \n iface eth0 inet manual \n allow-hotplug wlan0 \n iface wlan0 inet static \n address 192.168.42.1 \n netmask 255.255.255.0 \n gateway 192.168.1.1 \n pre-up iptables-restore < /etc/iptables.ipv4.nat";
		                 File file = new File("/etc/network/interfaces");
		          if (!file.exists())
		          {
		          try
		          {
				  file.createNewFile();
		          }
		          catch (IOException e)
		          {
				 e.printStackTrace();
		          }
		      }
		             FileWriter fw = new FileWriter(file.getAbsoluteFile());
		             BufferedWriter bw = new BufferedWriter(fw);
		             bw.write(make_interfaces);
		             bw.close();
		             System.out.println("Done");
		             Thread.sleep(1000);
		             break;
                      }

		   }
                   catch (IOException e) 
                   {
			    e.printStackTrace();
	           }
		  }
		  else
		  {
		      System.out.println("Wifi Not Connected");	
		      Thread.sleep(60000);
		      wifitimeout++;
		      if(wifitimeout==5)
		      {
			   break; 
			   //file write make-interfaces/interfaces to /etc/network/interfaces
		      }
		 }
	   }
       else
       {
    	                 wifitimeout++;
			 System.out.println("Not Connected \t "+wifitimeout);
			 Thread.sleep(60000);
       }
           	         if(wifitimeout==5)
	                 {
			 //file write make-interfaces/interfaces to /etc/network/interfaces
			 String make_interfaces ="auto lo \n iface lo inet loopback \n auto eth0 \n allow-hotplug eth0 \n iface eth0 inet manual \n allow-hotplug wlan0 \n iface wlan0 inet static \n address 192.168.42.1 \n netmask 255.255.255.0 \n gateway 192.168.1.1 \n pre-up iptables-restore < /etc/iptables.ipv4.nat";
	                 File file = new File("/etc/network/interfaces");
	                 if (!file.exists())
	                 {
	                  try 
	                    {
				 file.createNewFile();
			    }
	                  catch (IOException e)
	                    {
				 e.printStackTrace();
			    }
	           }
	             FileWriter fw;
		     try
                     {
                     fw = new FileWriter(file.getAbsoluteFile());
	             BufferedWriter bw = new BufferedWriter(fw);
	             bw.write(make_interfaces);
	             bw.close();
	             System.out.println("Done");
                     }
		     catch (IOException e)
                     {
		         // TODO Auto-generated catch block
			 e.printStackTrace();
	             }
	              Thread.sleep(1000);
	             break;
       }
    }
                try 
                {
		               Process p1 = Runtime.getRuntime().exec("sudo reboot");
	        }
                catch (IOException e) 
                {
                	e.printStackTrace();
	        }
    }
}

