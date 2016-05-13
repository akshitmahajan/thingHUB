import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.sql.*;
import java.util.Enumeration;


public class QueryFromCloudToGW 
{
	static Connection c = null;
	static Statement stmt = null;
    static ResultSet rs;
    static String IP;
	public static void main(String[] args) throws Exception {
	       			
	NetworkInterface ni = NetworkInterface.getByName("wlan0");
        Enumeration<InetAddress> inetAddresses =  ni.getInetAddresses();


    while(inetAddresses.hasMoreElements()) {
        InetAddress ia = inetAddresses.nextElement();
        if(!ia.isLinkLocalAddress()) {
            System.out.println("IP: " + ia.getHostAddress());
            IP  = ia.getHostAddress();
}
}
  System.out.println(IP);


InetAddress address = InetAddress.getByName(IP);

NetworkInterface niMac = NetworkInterface.getByInetAddress(address);
byte[] mac = niMac.getHardwareAddress();
StringBuilder sb = new StringBuilder();
for (int i = 0; i < mac.length; i++) 
{
  sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "" : "")); 
}
String macAdd = new String(sb);
macAdd="thingHUB-"+macAdd;
System.out.println("\n"+macAdd);
		
		while(true)
	     {
			String url = "http://192.168.0.114:8080/CloudIOT/ClientInformation?hostname="+macAdd;
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			con.setRequestMethod("GET");
			con.getClass();
	        
			int responseCode = con.getResponseCode();
			
			System.out.println("\nSending 'GET' request to URL : " + url);
			
			System.out.println("Response Code : " + responseCode);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) 
			{
				response.append(inputLine);
			}
			in.close();
			byte[] buf = new byte[256];
			String msg = new String(buf, 0, buf.length);
			msg=response.toString();
	        System.out.println(msg + " \t Nothing");
	        if(msg.isEmpty()==false)
	        {
	        try {
	        
	        	String[] value_split=msg.split("\\$");
        	
        	for(int i=0; i<value_split.length; i++)
        	{
        		
        		System.out.println(value_split[i]);
        		String[] query_split=value_split[i].split("\\|");
        		
        		String Zone=query_split[0];
                System.out.println(Zone);

                String ApplianceType=query_split[1];
                System.out.println(ApplianceType);

                String ApplianceName=query_split[2];
                System.out.println(ApplianceName);
                
                String status=query_split[3];
                System.out.println(status);
                
                Class.forName("com.mysql.jdbc.Driver");
            	c = DriverManager.getConnection("jdbc:mysql://192.168.0.111:3306/wifidevices","root","raspberry");
            	System.out.println("Opened database successfully");
            	c.setAutoCommit(false);
            	stmt = c.createStatement();
            	
            	String sql="SELECT Ip FROM notifystatus  where Zone='"+Zone+"' AND ApplianceType='"+ApplianceType+"' AND ApplianceName='"+ApplianceName+"';"; 
                rs = stmt.executeQuery(sql);
                while(rs.next())
                {
                	String IP = rs.getString("Ip");
                	System.out.println(" IP : " + IP);
                	String httpurl = "http://"+IP+"/plug/"+status;
                	System.out.println("HTTP-GET-REQUEST"+httpurl);
            		URL httpobj = new URL(httpurl);
            		HttpURLConnection httpcon = (HttpURLConnection) httpobj.openConnection();

            		httpcon.setRequestMethod("GET");
            		httpcon.getClass();
                    
            		int httpresponseCode = httpcon.getResponseCode();
            		
            		System.out.println("\nSending 'GET' request to URL : " + httpurl);
            		
            		System.out.println("Response Code : " + httpresponseCode);
            		
            		BufferedReader httpin = new BufferedReader(new InputStreamReader(httpcon.getInputStream()));
            		String httpinputLine;
            		StringBuffer httpresponse = new StringBuffer();

            		while ((httpinputLine = httpin.readLine()) != null) 
            		{
            			httpresponse.append(httpinputLine);
            		}
            		httpin.close();
                    System.out.println(httpresponse.toString());
            	
                }
                rs.close();
                stmt.close();
                c.commit();
                c.close();

        	}
        	System.out.println(IP);
        }
        catch(SQLException e)
        {
         e.printStackTrace();
        }
       catch (ClassNotFoundException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
         } 
       	catch (Exception e)
         {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
	   }
	        else
	        {
	        	System.out.println("No Query");
	        }
	        Thread.sleep(10000);
	}
	
}
}
