/*import java.math.BigInteger;

class AB {
        int i;
        void display() {
            System.out.println(i);
        }
    }    
    class B extends AB {
        int j;
        
        void display() {
            System.out.println(j);
        }
    }    
    class A {
        public static void main(String args[])
        {
            B obj = new B();
            obj.i=1;
            obj.j=2;
            		   
            obj.display();
            int value = new BigInteger("ff", 16).intValue();
            System.out.println(value);
            
            
        }
   }
*/


import java.sql.*;

public class A
{

	public static void main(String a[])throws Exception
	{
		
		Connection c = null;
	    Statement stmt = null;
	    ResultSet rs= null;

	    try
	    {
	    	Class.forName("com.mysql.jdbc.Driver");
	    	c = DriverManager.getConnection("jdbc:mysql://192.168.0.111:3306/wifidevices","root","raspberry");
	    	stmt=c.createStatement();
	    	String sql ="select * from keepalive";
	    	rs = stmt.executeQuery(sql);
	    	char[] buf = null;
			String msg = new String(buf, 0, buf.length);
			System.out.println(msg);
	    	System.out.println("IP  \t\t Mac");
	    	while (rs.next())
	    	{
	    		String IP = rs.getString("Ip");
	    		
	    		String Mac = rs.getString("Mac");
	    		System.out.println(IP+"   "+Mac);
	    	}
	    }
	    catch(SQLException se){
	    	
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	}
}