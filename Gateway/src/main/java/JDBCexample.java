import java.sql.*; 
// need to import this as the STEP 1. Has the classes that you mentioned  
public class JDBCexample {
static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
static final String DB_URL = "jdbc:mysql:///F:/Home%20Automation/MySQL//temp.db"; 
// DON'T PUT ANY SPACES IN BETWEEN and give the name of the database (case insensitive) 

// database credentials
static final String USER = "system";
// usually when you install MySQL, it logs in as root 
static final String PASS = "password";
// and the default password is blank

public static void main(String[] args) {
    Connection conn = null;
    Statement stmt = null;

    try {
// registering the driver__STEP 2
        Class.forName("com.mysql.jdbc.Driver"); 
// returns a Class object of com.mysql.jdbc.Driver
// (forName(""); initializes the class passed to it as String) i.e initializing the
// "suitable" driver
        System.out.println("connecting to the database");
// opening a connection__STEP 3
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
// executing a query__STEP 4 
        System.out.println("creating a statement..");
        stmt = conn.createStatement();
// creating an object to create statements in SQL
        String sql;
        sql = "SELECT column1, cloumn2, column3, column4 from jdbcTest;";
// this is what you would have typed in CLI for MySQL
        ResultSet rs = stmt.executeQuery(sql);
// executing the query__STEP 5 (and retrieving the results in an object of ResultSet)
// extracting data from result set
        while(rs.next()){
// retrieve by column name
            int value1 = rs.getInt("column1");
            int value2 = rs.getInt("column2");
            String value3 = rs.getString("column3");
            String value4 = rs.getString("columnm4");
// displaying values:
            System.out.println("column1 "+ value1);
            System.out.println("column2 "+ value2);
            System.out.println("column3 "+ value3);
            System.out.println("column4 "+ value4);

        }
// cleaning up__STEP 6
        rs.close();
        stmt.close();
        conn.close();
    } catch (SQLException e) {
//  handle sql exception
        e.printStackTrace();
    }catch (Exception e) {
// TODO: handle exception for class.forName
        e.printStackTrace();
    }finally{  
//closing the resources..STEP 7
        try {
            if (stmt != null)
                stmt.close();
        } catch (SQLException e2) {
            e2.printStackTrace();
        }try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
    }
    System.out.println("good bye");
}
}