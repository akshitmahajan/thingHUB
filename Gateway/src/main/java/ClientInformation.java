import java.sql.*;

public class ClientInformation 
{
   Connection c=null;
   Statement stmt = null;
   ResultSet rs;

  public void main(String[] args) 
  {

	 String hostname="thingHUB";
     try
     {
     Class.forName("com.mysql.jdbc.Driver");
     c = DriverManager.getConnection("jdbc:mysql://localhost:3306/RFGW","root","raspberry");
     c.setAutoCommit(false);
     stmt = c.createStatement();
     String sql = "SELECT query FROM MobQuery WHERE hostname='"+hostname+"';";
     System.out.println(sql);
     rs=stmt.executeQuery(sql);
     while(rs.next())
     {
	 String query=rs.getString("query");
	 System.out.println("Query = "+query+"<br>");
     }
     System.out.println("</body></html>");
     rs.close();
     stmt.close();
     c.commit();
     c.close();
     } 
     catch (ClassNotFoundException e) 
     {
        e.printStackTrace();
     }    
     catch (SQLException e) 
     {
        e.printStackTrace();
     }
    }
   }