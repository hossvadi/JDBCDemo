package JDBCDemo;

import java.sql.*;

public class updateData {
    public static void main(String[] args) {
    	String url = "jdbc:mysql://localhost:3306/demo";
    	String user = "hjvdz";
        String pass = "Hj1374@@";

        try {
            // 1. get the connection
            Connection myConn = DriverManager.getConnection(url, user, pass);

            // 2. create the statement
            Statement myStmt = myConn.createStatement();
            
            // 3. Execute SQL query
            String sql = "update employees set email='hjvdz74@gmail.com'"
            		+ "where empId=1";

            myStmt.executeUpdate(sql);

            System.out.println("Update complete.");
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
  