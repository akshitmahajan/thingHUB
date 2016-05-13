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

                        String sql ="SELECT DISTINCT Zone FROM IP;";

                        rs = stmt.executeQuery(sql);

            while(rs.next()){

        %>
        <tr>
        <form action="homeinteriorlay.jsp" method="post">
        <input type="hidden" name ="toggle" value=<%=rs.getString("Zone") %> >

        <td><button type="submit"><%=rs.getString("Zone") %></button></td>
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

