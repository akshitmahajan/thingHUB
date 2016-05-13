import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Monitoring
{
static String GlobalIp;
public static void main(String[] args) throws IOException, InterruptedException
{
        Thread.sleep(1000);
        NetworkInterface ni = NetworkInterface.getByName("wlan0");
        if (ni!=null)
        {
           Enumeration<InetAddress> inetAddresses =  ni.getInetAddresses();
        while(inetAddresses.hasMoreElements())
        {
                 InetAddress ia = inetAddresses.nextElement();
                if(!ia.isLinkLocalAddress()) 
                {
                 GlobalIp=ia.getHostAddress();
            	 System.out.println(GlobalIp);
                }
        }
        if(GlobalIp==null)
        {
            System.out.println("Wifi not connected");
            String make_interfaces ="auto lo \niface lo inet loopback \nauto eth0 \nallow-hotplug eth0 \niface eth0 inet manual \nallow-hotplug wlan0 \niface wlan0 inet static \naddress 192.168.42.1 \nnetmask 255.255.255.0 \ngateway 192.168.1.1 \npre-up iptables-restore < /etc/iptables.ipv4.nat";
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
             Process p1 = Runtime.getRuntime().exec("sudo reboot");
         }
          else if( GlobalIp.equals("192.168.42.1"))
          {
        	int i;
        	try
                {
			Process p1=Runtime.getRuntime().exec("sudo sh thingHUB/apache-tomcat-8.0.24/bin/startup.sh");
                        String orig_interfaces="auto lo\niface lo inet loopback\nauto eth0\nallow-hotplug eth0\niface eth0 inet manual\nauto wlan0\nallow-hotplug wlan0\niface wlan0 inet manual\nwpa-conf /etc/wpa_supplicant/wpa_supplicant.conf";
                        File file = new File("/etc/network/interfaces");
                        if (!file.exists())
                        {
                           file.createNewFile();
                        }
                        FileWriter fw = new FileWriter(file.getAbsoluteFile());
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(orig_interfaces);
                        bw.close();
                        System.out.println("Done");
    	                   Thread.sleep(13000);
                           p1=Runtime.getRuntime().exec("sudo reboot");

               }
    	       catch (IOException e1)
               {
                        e1.printStackTrace();
	       }
               catch (InterruptedException e) 
               {
  	          	e.printStackTrace();
  	       }
            }
            else
            {
              try
              {
        		        Process p1=Runtime.getRuntime().exec("sudo sh thingHUB/HTTP_server/apache-tomcat-8.0.24/bin/startup.sh");
        		        p1=Runtime.getRuntime().exec("sudo java /home/pi/thingHUB/CheckingNetworkConnection/RouterPing &");
        		        p1=Runtime.getRuntime().exec("sudo java /home/pi/thingHUB/thingHUB_Broadcast/Broadcast");
        		        //Run Broadcasting program sleep(500) & RMIServer and Client
                                p1=Runtime.getRuntime().exec("sudo sh /home/pi/thingHUB/BroadcastNotification/BroadcastClientNotify.sh > BroadcastClientNotify.txt");
                                p1=Runtime.getRuntime().exec("sudo sh /home/pi/thingHUB/BroadcastListening/BroadcastClientlisten.sh > BroadcastClientlistening.txt");
                                p1=Runtime.getRuntime().exec("sudo sh /home/pi/thingHUB/StartupScripts/Start_CoAPClient_RMIServer.sh > abc.txt");
	      }
              catch (IOException e)
              {
	                        e.printStackTrace();
              }
                                System.out.println(GlobalIp);
           }
        }
        else
        {
                   System.out.println("Check your wifi dongle connection");
                   System.out.println("Done");
                   String make_interfaces ="auto lo\niface lo inet loopback\nauto eth0\nallow-hotplug eth0\niface eth0 inet manual\nallow-hotplug wlan0\niface wlan0 inet static\naddress192.168.42.1\nnetmask 255.255.255.0\ngateway 192.168.1.1\npre-up iptables-restore < /etc/iptables.ipv4.nat";
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
                   Thread.sleep(3000);
        	   Process p1=Runtime.getRuntime().exec("sudo reboot");
        }
    }
}

