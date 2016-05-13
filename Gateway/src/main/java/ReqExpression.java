import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class ReqExpression {
	public static void main(String[] args) throws UnknownHostException, SocketException, IOException 
	{
		
		Connection c = null;
        Statement stmt = null;
        ResultSet rs;
        try{
        
        	byte[] buf = new byte[256];
        	String msg = new String(buf, 0, buf.length);
        	 msg="HALL|LIGHT|HALL_BULB|off$HALL|FAN|HALL_FAN|off$HALL|LIGHT|HALL_BULB|on$HALL|FAN|HALL_FAN|on";
        	System.out.println(msg);
        	
		
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
                	String url = "http://"+IP+"/plug/"+status;
                	System.out.println("HTTP-GET-REQUEST"+url);
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
                    System.out.println(response.toString());
            	
                }
                rs.close();
                stmt.close();
                c.commit();
                c.close();

        	}
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
}

	