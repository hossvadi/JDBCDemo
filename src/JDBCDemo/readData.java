package JDBCDemo;

import java.sql.*;

public class readData {
    public static void main(String[] args) {
        try {
            // 1. get the connection
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "hjvdz", "Hj1374@@");

            // 2. create the statement
            Statement myStmt = myConn.createStatement();
            
            // 3. Execute SQL query
            ResultSet myRs = myStmt.executeQuery("select * from employees");
            
            // 4. Proccess the result set
            while (myRs.next()) {
            	System.out.println(myRs.getString("lastname") + ", " + myRs.getString("firstname"));
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
  