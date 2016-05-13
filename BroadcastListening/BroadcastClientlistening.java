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
                         c = DriverManager.getConnection("jdbc:mysql://localhost:3306/wifidevices","root","raspberry");
                         stmt=c.createStatement();
                         String sql ="select Ip,Mac from keepalive;";
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
            			System.out.println("Already Ip exists w.r.t hostname Soo Ip Not UPDATED in DB ");
            		}
            		else
            		{
        		    String sql1 = "UPDATE keepalive SET Ip= '"+IpwithoutDuplicateForComparision[i]+"'WHERE Mac='"+MacwithoutDuplicateForComparision[i]+"';" ;
        			    stmt.executeUpdate(sql1);
            		}
                }
            	else
            	{
            		String sql1 = "insert into keepalive(Ip,Mac) values('"+IpwithoutDuplicateForComparision[i]+"','"+MacwithoutDuplicateForComparision[i]+"');" ;
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
