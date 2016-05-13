import java.sql.*;


// Notice, do not import com.mysql.jdbc.*
// or you will have problems!

public class MySqlTest {
    public static void main(String[] args) {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations
        	Connection c = null;
            Statement stmt = null;

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mapping","root","raspberry");  
        	System.out.println("Opened database successfully");
        	c.setAutoCommit(false);
            stmt = c.createStatement();
          	 String sql = "INSERT INTO IP (Zone) VALUES('TEST');"; 
          	
            stmt.executeUpdate(sql); 
            stmt.close();
       	    c.commit();
            c.close();
        } catch (Exception ex) {
            // handle the error
        }
    }
}