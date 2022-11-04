package JDBCDemo;

import java.sql.*;

public class insertData {
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
            String sql = "insert into employees (lastname, firstname, email)" +
                    "values ('John', 'Smith', 'johnsmith@gmail.com')";

            myStmt.executeUpdate(sql);

            System.out.println("Insert complete.\n");

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
  