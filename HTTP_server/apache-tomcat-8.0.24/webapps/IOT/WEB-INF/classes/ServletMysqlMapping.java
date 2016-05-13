import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/register")
public class ServletMysqlMapping extends HttpServlet  {
	Connection c = null;
    Statement stmt = null;

protected void doGet(HttpServletRequest request,
               HttpServletResponse response)
 throws ServletException, IOException {
String req=request.getParameter("mote");
String res=request.getParameter("zone");
String resType=request.getParameter("resType");
doPost(request,response);
}



protected void doPost(HttpServletRequest request,
               HttpServletResponse response)
 throws ServletException, IOException {
String mote = request.getParameter("mote");
    String zone = request.getParameter("zone");
    String restype = request.getParameter("restype");
    try {
    Class.forName("com.mysql.jdbc.Driver");
	c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mapping","root","raspberry");
	System.out.println("Opened database successfully");
	c.setAutoCommit(false);
	stmt = c.createStatement();
	//UPDATE IP SET Zone ='NULL',ResourceType='LIGHT' WHERE IpAddress='aaaa::212:4b00:89ab:cdef';
    String sql = "UPDATE IP SET Zone= '"+zone+"',ResourceType='"+restype+"' WHERE label='"+mote+"'" ;
    stmt.executeUpdate(sql);
    stmt.close();
    c.commit();
    c.close();
    } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
