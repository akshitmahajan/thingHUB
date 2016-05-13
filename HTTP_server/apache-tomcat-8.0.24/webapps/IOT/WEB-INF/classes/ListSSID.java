import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.annotation.WebServlet;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet("/listssid")
public class ListSSID extends HttpServlet  {
	Connection c = null;
    Statement stmt = null;

protected void doGet(HttpServletRequest request,
               HttpServletResponse response)
 throws ServletException, IOException {

PrintWriter out=response.getWriter();
FileInputStream fstream;
        try {
                fstream = new FileInputStream("/home/pi/abc.txt");

        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;

        //Read File Line By Line
        while ((strLine = br.readLine()) != null)   {
          // Print the content on the console
          String trimstrLine=strLine.trim();
         out.println (trimstrLine.substring(7,trimstrLine.length() - 1));
        }

        //Close the input stream
        br.close();
} catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }

}
}
