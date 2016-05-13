<%@page import="java.sql.*"%>


<%

        Connection c = null;
        Statement stmt = null;
        ResultSet rs = null;
        String id=request.getParameter("toggle");
        
%>
<h1> <% String id1=request.getParameter("toggle"); %> 
        <table cellpadding="5" cellspacing="5">
        
        <%
                try
                {
                                Class.forName("com.mysql.jdbc.Driver");

        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mapping","root","raspberry");
                        stmt=c.createStatement();

                        String sql ="SELECT ResourceType FROM IP WHERE Zone='"+id+"';";

                        rs = stmt.executeQuery(sql);

            while(rs.next()){

        %>
        <tr>
        <form action="toggle" method="post">
        <input type="hidden" name ="toggle" value=<%=rs.getString("ResourceType") %> >

        <td><button type="submit"><%=rs.getString("ResourceType") %></button></td>
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

