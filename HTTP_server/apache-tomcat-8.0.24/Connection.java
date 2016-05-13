import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Connection
{

	public static void main(String a[])throws Exception
	{
		
		Connection c = null;
	    Statement stmt = null;
	    try
	    {
	    	Class.forName("com.mysql.jdbc.Driver");
	    	c = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/wifidevices","root","raspberry");
	    	stmt=((java.sql.Connection) c).createStatement();
	    	String sql ="select * from keepalive";
	    	ResultSet rs = stmt.executeQuery(sql);
	    	System.out.println("IP  Mac");
	    	while (rs.next())
	    	{
	    		int IP = rs.getInt("IP");
	    		String Mac = rs.getString("Mac");
	    		System.out.println(IP+"   "+Mac);
	    	}
	    }
	    catch(SQLException se){}
	    catch(Exception e){}
	}
}
