import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ShowIp {
   static String GlobalIp;
    public static void main(String[] args) throws IOException, InterruptedException {
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
    	NetworkInterface ni = NetworkInterface.getByName("wlan0");
        if (ni!=null)
        {
        Enumeration<InetAddress> inetAddresses =  ni.getInetAddresses();


        while(inetAddresses.hasMoreElements()) {
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
             Process p1 = Runtime.getRuntime().exec("sudo reboot");

        
         }
          else if( GlobalIp.equals("192.168.42.1"))
          {
        	int i;
        	try {
				Process p1=Runtime.getRuntime().exec("sudo sh apache-tomcat-8.0.24/bin/startup.sh");
                
                        String orig_interfaces="\n auto lo \n iface lo inet loopback \n auto eth0 \n allow-hotplug eth0 \n iface eth0 inet manual \n auto wlan0 \n allow-hotplug wlan0 \n iface wlan0 inet manual \n wpa-conf /etc/wpa_supplicant/wpa_supplicant.conf \n";
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


//String[] cmd = {"sudo sh orig_interfaces.sh"};
//Process p2=Runtime.getRuntime().exec(cmd);
                        Thread.sleep(1000);
                        
                  for(i=0;i<10;i++)
                  {
    	              Thread.sleep(3000);
	              }
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
        		        Process p1=Runtime.getRuntime().exec("sudo sh apache-tomcat-8.0.24/bin/startup.sh");
        		        p1=Runtime.getRuntime().exec("sudo java RouterPing.jar");
        		        p1=Runtime.getRuntime().exec("sudo java Broadcast");
        		        //Run Broadcasting program sleep(500) & RMIServer and Client  
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
        	System.out.println("Wifi not connected");
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
             System.out.println("Done");
             Thread.sleep(5000);
        	
        	Process p1=Runtime.getRuntime().exec("sudo reboot");
        }
    }

}
