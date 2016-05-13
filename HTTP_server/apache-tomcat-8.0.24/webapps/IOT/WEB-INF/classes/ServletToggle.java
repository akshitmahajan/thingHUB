import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/toggle")
public class ServletToggle extends HttpServlet  {
	Connection c = null;
    Statement stmt = null;

protected void doGet(HttpServletRequest request,
               HttpServletResponse response)
 throws ServletException, IOException {
String req=request.getParameter("toggle");
doPost(request,response);
}



protected void doPost(HttpServletRequest request,
               HttpServletResponse response)
 throws ServletException, IOException {
String toggle = request.getParameter("toggle");
String toggle1 = request.getParameter("toggle1");
PrintWriter out=response.getWriter();
response.addHeader("Connection", "Keep-Alive");
response.addHeader("Keep-Alive", "timeout=20000");
out.print("You wants to toggle " +toggle1+"'s "+ toggle);
}
}
