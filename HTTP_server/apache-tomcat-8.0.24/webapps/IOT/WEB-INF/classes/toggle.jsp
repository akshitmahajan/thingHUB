<%@page import="java.sql.*"%>


<%

        Connection c = null;
        Statement stmt = null;
        ResultSet rs = null;
%>

        <table cellpadding="5" cellspacing="5">
        
        <%
                try
                {
                                Class.forName("com.mysql.jdbc.Driver");

        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mapping","root","raspberry");
                        stmt=c.createStatement();

                        String sql ="SELECT ResourceType FROM IP;";

                        rs = stmt.executeQuery(sql);

            while(rs.next()){

        %>
        <tr>
        <form action="toggle" method="post">
        <td><button type="submit" name="toggle"><%=rs.getString("ResourceType") %></button></td>
         </form>
        </tr>

        <%
                        }

        } catch (Exception e)
        {
                e.printStackTrace();
        }


%>
</table>

