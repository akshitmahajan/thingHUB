import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.sql.*;

public class BroadcastClientNotifying 
{
        static String Ip;
        static String firstThree;

	final static int PORT = 5002;

    public static void main(String[] args) throws UnknownHostException, SocketException 
	{
         Connection c = null;
         Statement stmt = null;
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
                                byte[] buf = new byte[256];
			        DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
				clientSocket.receive(msgPacket);
                                String msg = new String(buf, 0, buf.length);
                                System.out.println(msg);
System.out.println(msg);


                              // String rat_values = "thingTronics|thingSocket-DE2ACB|HALL|TV|HALL_TV|0-7|10|CONFIGURED|@@@@";
	   // String[] value_split = rat_values.split("\\|");
            Class.forName("com.mysql.jdbc.Driver");
        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/wifidevices","root","raspberry");
        System.out.println("Opened database successfully");
        c.setAutoCommit(false);
        stmt = c.createStatement();
        //UPDATE IP SET Zone ='NULL',ResourceType='LIGHT' WHERE IpAddress='aaaa::212:4b00:89ab:cdef';
	         String[] value_split=msg.split("\\|");
                 Boolean configurechecking=msg.contains("CONFIGURED"); 
                 System.out.println(msg);
                 String Mac=value_split[1];
                 System.out.println(Mac);
                 String Zone=value_split[2];
                 System.out.println(Zone);

                 String ApplianceType=value_split[3];
                 System.out.println(ApplianceType);

                 String ApplianceName=value_split[4];
                 System.out.println(ApplianceName);
                 String ResNo=value_split[5];
                 System.out.println(ResNo);
                 String status=value_split[6];
                if(configurechecking==true)
                {
                 String sql="insert into notifystatustest1(Mac,Zone,ApplianceType,ApplianceName,status,ResNo,Timestamp) values('"+Mac+"','"+Zone+"','"+ApplianceType+"','"+ApplianceName+"','"+status+"','"+ResNo+"',now()+INTERVAL 5 HOUR + INTERVAL 30 MINUTE)"; 
                 stmt.executeUpdate(sql);
                 stmt.close();
                 c.commit();
                 c.close();
                }
                else 
                {
	       // String status=value_split[6];
	        System.out.println(status);
                String sql = "UPDATE notifystatustest1 SET status = '"+status+"',Timestamp=now()+INTERVAL 5 HOUR + INTERVAL 30 MINUTE where Zone='"+Zone+"' AND ApplianceType='"+ApplianceType+"' AND ApplianceName='"+ApplianceName+"';";
                stmt.executeUpdate(sql);
                stmt.close();
                c.commit();
                c.close();
           }

	            }
		}
      	catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       catch(SQLException e)
       {
        e.printStackTrace();
       }
      catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } 
      	catch (IOException e)
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }
}
