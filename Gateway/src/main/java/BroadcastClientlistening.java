import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.sql.*;
public class BroadcastClientlistening
{
    static Connection c = null;
    static Statement stmt = null;
    static ResultSet rs= null;
    static boolean exists;
    static boolean Ipexists;

    @SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
    	MulticastSocket ds = new MulticastSocket(5000);
    byte[] buf = new byte[1024];
    DatagramPacket dp = new DatagramPacket(buf, 1024);
    ArrayList<String> keepaliveIpArray = new ArrayList<String>();
    int count;
    while(true)
    {
    ds.receive(dp);
    InetAddress ip=dp.getAddress();
    String keepaliveip=ip.toString();
    keepaliveip = keepaliveip.substring(1);
    String str = new String(dp.getData(), 0, dp.getLength());
    String[] value_split = str.split("\\|");
    keepaliveip=keepaliveip+"|"+value_split[1]+"|"+value_split[2];
    keepaliveIpArray.add(0, keepaliveip);
    System.out.println(keepaliveip); 
    count=keepaliveIpArray.size();
    System.out.println(count);
    if(count==10)
    {
    	HashSet<String> hs=new HashSet<>(keepaliveIpArray);
		System.out.println(hs);
		Iterator<String> iterator = hs.iterator();
		ArrayList<String> IpwithoutDuplicate = new ArrayList<String>();
		 ArrayList<String> MacwithoutDuplicate = new ArrayList<String>();
		 ArrayList<String> DatabaseMacAddress= new ArrayList<String>();
		 ArrayList<String> DatabaseIpAddress= new ArrayList<String>();
		   while (iterator.hasNext())
		   {
			  String Mac=iterator.next().toString();
			  String[] value_split1 = Mac.split("\\|");
			  IpwithoutDuplicate.add(0,value_split1[0]);
			  MacwithoutDuplicate.add(0,value_split1[1]);
			  System.out.println("Converting I2S ="+Mac);
		   }
		    for(int i=0;i<MacwithoutDuplicate.size();i++)
			 {
			  System.out.println(MacwithoutDuplicate.get(i));
			 }
		         Class.forName("com.mysql.jdbc.Driver");
                         c = DriverManager.getConnection("jdbc:mysql://192.168.0.111:3306/wifidevices","root","raspberry");
                         stmt=c.createStatement();
                         String sql ="select Ip,Mac from keepalivetest;";
                         rs = stmt.executeQuery(sql);
                         c.setAutoCommit(true);
                         if(!rs.next())
                         {
                           System.out.println("No data");
                         }
                         else
                         {
            	          rs.beforeFirst();
                         while(rs.next())
                         {
                          String DbIp=rs.getString("Ip");
                          String DbMac=rs.getString("Mac");
                          System.out.println(DbMac);
                          DatabaseIpAddress.add(DbIp);
                          DatabaseMacAddress.add(DbMac);
                         }
                       }
            int MacwithoutDuplicateCount=MacwithoutDuplicate.size();
            int DatabaseMacAddressCount=DatabaseMacAddress.size();
            Object[]IpwithoutDuplicateForComparision=IpwithoutDuplicate.toArray();
            Object[] MacwithoutDuplicateForComparision= MacwithoutDuplicate.toArray();
            Object[] DatabaseMacAddressComparision= DatabaseMacAddress.toArray();
            Object[] DatabaseIpAddressComparision= DatabaseIpAddress.toArray();
            Class.forName("com.mysql.jdbc.Driver");
	        stmt = c.createStatement();
	        for(int i=0;i<MacwithoutDuplicateCount;i++)
                {
            	 for(int j=0;j<DatabaseMacAddressCount;j++)
            	 {
            		exists=MacwithoutDuplicateForComparision[i].equals(DatabaseMacAddressComparision[j]);
            		if(exists==true)
            		{
            			Ipexists=IpwithoutDuplicateForComparision[i].equals(DatabaseIpAddressComparision[j]);
            		        break;
            		}
            	}
            	if(exists==true)
        	{
            		if(Ipexists==true)
            		{
            			System.out.println("Already Ip exists w.r.t hostname So Ip Not UPDATED but Timestamp UPDATED in DB ");
            			String sql1 = "UPDATE keepalivetest SET Timestamp=now()+INTERVAL 5 HOUR + INTERVAL 30 MINUTE WHERE Mac='"+MacwithoutDuplicateForComparision[i]+"';" ;
        			    stmt.executeUpdate(sql1);
            		}
            		else
            		{
        		    String sql1 = "UPDATE keepalivetest SET Ip= '"+IpwithoutDuplicateForComparision[i]+"',Timestamp=now()+INTERVAL 5 HOUR + INTERVAL 30 MINUTE WHERE Mac='"+MacwithoutDuplicateForComparision[i]+"';" ;
        			    stmt.executeUpdate(sql1);
            		}
                }
            	else
            	{
            		String sql1 = "insert into keepalivetest(Ip,Mac,Timestamp) values('"+IpwithoutDuplicateForComparision[i]+"','"+MacwithoutDuplicateForComparision[i]+"',now()+INTERVAL 5 HOUR + INTERVAL 30 MINUTE);" ;
        		    stmt.executeUpdate(sql1);
            	}
            }
		for(int j = count-1; j >= 0; j--)
    	{

    		keepaliveIpArray.remove(j);

    	}
    	Thread.sleep(5000);
    }

  }
    //ds.close();
 }
}


/*import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class BroadcastClientlistening 
{
	


	static String Ip;
    static String firstThree;
	final static int PORT = 5000;

    public static void main(String[] args) throws UnknownHostException, SocketException 
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
        
		InetAddress address = InetAddress.getByName(firstThree);
		
      	try (MulticastSocket clientSocket = new MulticastSocket(PORT))
		{
        
      		
				clientSocket.getBroadcast();
			
	        while (true) 
            { 
		    	byte[] buf = new byte[512];
			   DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
               
				clientSocket.receive(msgPacket);
				InetAddress ip=msgPacket.getAddress();
			    String keepaliveip=ip.toString();
			    System.out.println(keepaliveip);
			 
               String msg = new String(buf, 0, buf.length);
               System.out.println(msg);
               
			}
		}
      	catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
		   //Logic Should Start from here
		   
  }
}*/