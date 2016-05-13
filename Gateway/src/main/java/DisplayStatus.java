import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DisplayStatus
{
	static Connection c = null;
    static Statement stmt = null;
    static ResultSet rs= null;
    
	public static void main (String args[])throws Exception
	{
		
		Calendar cal = Calendar.getInstance();
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.US);
		
		Date d1 = cal.getTime();
		cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, -60);
		Date d2 = cal.getTime();
		ArrayList<String> Online = new ArrayList<String>();	
		ArrayList<String> Offline = new ArrayList<String>();
		int i=0;
		
			Class.forName("com.mysql.jdbc.Driver");
	        c = DriverManager.getConnection("jdbc:mysql://192.168.0.111:3306/wifidevices","root","raspberry");
	        stmt=c.createStatement();
	        String sql ="select Timestamp,Mac from keepalivetest;";
	        rs = stmt.executeQuery(sql);
	        c.setAutoCommit(true);
	       while(rs.next())
            {
	        	String dbdate = rs.getString("Timestamp");
	        	String Mac = rs.getString("Mac");
                Date date = formatter.parse(dbdate);
	        		        	
	        	if (date.before(d1)&&date.after(d2))
	        	{
	        		Online.add(i,Mac);
	        	}
	        	else
	        	{
	        		Offline.add(i,Mac);
	        	}
	        	i++;
            }
	       System.out.println("Online\t:\t"+Online);
	       System.out.println("Offline\t:\t"+Offline);
	}
}