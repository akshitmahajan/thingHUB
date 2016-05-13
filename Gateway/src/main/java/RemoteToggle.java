import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/remotetoggle")
public class RemoteToggle extends HttpServlet  {
	public static void main(String[] args) throws Exception {
	Connection c = null;
	Statement stmt = null;
	ResultSet rs = null;
	ResultSet KArs = null;
/*
   protected void doGet(HttpServletRequest request,
               HttpServletResponse response)
 throws ServletException, IOException {
String Zone = request.getParameter("Zone");
    String ApplianceType = request.getParameter("ApplianceType");
    String ApplianceName = request.getParameter("ApplianceName");
    String Turn=request.getParameter("turn");
doPost(request,response);
}



protected void doPost(HttpServletRequest request,
               HttpServletResponse response)
 throws ServletException, IOException {

    response.setContentType("text/json");
    String Zone = request.getParameter("Zone");
    String ApplianceType = request.getParameter("ApplianceType");
    String ApplianceName = request.getParameter("ApplianceName");
    String Turn=request.getParameter("turn");
*/
	String Zone = "HALL";
    String ApplianceType ="switch";
    String ApplianceName = "FAN";
    String Turn="on";
    try
	{ 
			Class.forName("com.mysql.jdbc.Driver");
	
	c = DriverManager.getConnection("jdbc:mysql://192.168.0.111:3306/wifidevices","root","raspberry");  
		stmt=c.createStatement();

		String sql ="SELECT Zone,ApplianceType,ApplianceName,Mac,Ip,ResNo,Timestamp FROM notifystatustest1;";

		rs = stmt.executeQuery(sql);
        while(rs.next())
        {
                String NsDbZone=rs.getString("Zone");
                String NsDbAppTy=rs.getString("ApplianceType");
               String NsDbAppName=rs.getString("ApplianceName");
        	String NsDbMac=rs.getString("Mac");
                Timestamp NsDbTimestamp=rs.getTimestamp("Timestamp");
                Timestamp NsDbTimestamp1=rs.getTimestamp("Timestamp");
                long NsDbTimestampres;
                NsDbTimestampres=NsDbTimestamp1.getTime()-NsDbTimestamp.getTime();
                System.out.println(NsDbTimestampres);
                
                String NsDbResNo=rs.getString("ResNo");
                
                
        	if(Zone.equals(NsDbZone) && ApplianceType.equals(NsDbAppTy) && ApplianceName.equals(NsDbAppName))
        	{
        		String KAsql ="SELECT Mac,Ip FROM keepalive;";

        		KArs = stmt.executeQuery(KAsql);
        		while(KArs.next())
                {
        			String KADbMac=KArs.getString("Mac");
        			String KADbIp=KArs.getString("Ip");
        			
        			if(NsDbMac.equals(KADbMac))
        			{
        				System.out.println(NsDbTimestamp);
        				System.out.println(KADbMac+"\n"+KADbIp);
        	        	String url = "http://"+KADbIp+"/plug/"+Turn;
        	    		URL obj = new URL(url);
        	    		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        	                con.setRequestMethod("GET");
        	                obj.getAuthority();
        	                obj.toURI();
        	               
        	                int responseCode = con.getResponseCode();
        	                System.out.println("\nSending 'GET' request to URL : " + url);
        			System.out.println("Response Code : " + responseCode); 
        			}
                }
        	
            }
        }
	}
    catch(Exception e)
    {
    	
    }
    


}
}
