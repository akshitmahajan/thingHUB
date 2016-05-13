<%@page import="java.sql.*"%>


<%

	Connection c = null;
	Statement stmt = null;
	ResultSet rs = null;
%>
	<h1 align="center">Available Resources in your home</h1>

	<table cellpadding="5" cellspacing="5">
	<%
		try
		{ 
				Class.forName("com.mysql.jdbc.Driver");
		
    	c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mapping","root","raspberry");  
			stmt=c.createStatement();

			String sql ="SELECT label FROM IP;";

			rs = stmt.executeQuery(sql);
                        
            while(rs.next()){
		 

	%>
	
	<tr>

	<td><%=rs.getString("label") %></td>

	</tr>

	<% 
			}

	} catch (Exception e) 
	{
		e.printStackTrace();
	}

	
%>
</table>

<table>
<tr>
<td>Which Device </td>
<td>
<input type="text" name="mote"/>
<input type="submit" value="check">
</td>
</tr>
</br>
</br>

</table>


<form action="register" method"post">
<table>
</br>
</br>
<tr> 
<td>
 Which Device <input type="text" name="mote"/>
</td>
</tr>
<tr>
<td>
 Zone <input type="text" name="zone"/>
</td>
</tr>

<tr>
<td>
Resource Type <input type="text" name="restype"/>
</td>
</tr>
<tr><td><input type="submit" value="SetLocation"></td></tr>
</table>
</form>

</body>
</html>

