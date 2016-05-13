import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.annotation.WebServlet;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;

@WebServlet("/remoteToggle")
public class RemoteToggle extends HttpServlet  {
	Connection c = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSet KArs = null;
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
    try
	{ 
			Class.forName("com.mysql.jdbc.Driver");
	                c = DriverManager.getConnection("jdbc:mysql://localhost:3306/wifidevices","root","raspberry");  
		        stmt=c.createStatement();
         		String sql ="SELECT Zone,ApplianceType,ApplianceName,Mac,Ip,ResNo FROM notifystatustest1;";
         		rs = stmt.executeQuery(sql);
                        while(rs.next())
                        {
                         String NsDbZone=rs.getString("Zone");
                         String NsDbAppTy=rs.getString("ApplianceType");
                         String NsDbAppName=rs.getString("ApplianceName");
        	         String NsDbMac=rs.getString("Mac");
                         String NsDbIp=rs.getString("Ip");
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
        				System.out.println(KADbMac+"\n"+KADbIp);
        	        	String url = "http://"+KADbIp+"/plug/"+Turn;
        	    		URL obj = new URL(url);
        	    		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        	                con.setRequestMethod("GET");
        	                obj.getAuthority();
        	                obj.toURI();
                                PrintWriter out=response.getWriter();
                                out.print(url);
                                int responseCode = con.getResponseCode();
                                System.out.println("\nSending 'GET' request to URL : " + url);
                                out.print("Response Code : " + responseCode); 
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
