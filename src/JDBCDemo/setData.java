package JDBCDemo;

import java.sql.*;

public class setData {
    public static void main(String[] args) throws SQLException {
    	
    	Connection myConn = null;
    	PreparedStatement myStmt = null;
    	ResultSet myRs = null;
    	
        try {
        	String url = "jdbc:mysql://localhost:3306/demo";
        	String user = "hjvdz";
            String pass = "Hj1374@@";

            // 1. get the connection
            myConn = DriverManager.getConnection(url, user, pass);

            // 2. prepare statement
            myStmt = myConn.prepareStatement("select * from employees where salary > ? and department = ?");
            
            // 3. get the parameters
            myStmt.setDouble(1, 2500);
            myStmt.setString(2, "IT");
            
            // 4. Execute SQL query
			myRs = myStmt.executeQuery();
			
            // 5. Display the result set
			display(myRs);
			
			System.out.println("\n\nReuse the prepared statement: salary > 1700. department = PLC");
			
			// 6. set the parameters
			myStmt.setDouble(1, 1700);
			myStmt.setString(2, "PLC");
			
			// 7. Execute SQL query
			myRs = myStmt.executeQuery();
			
			// 8. Display te result set
			display(myRs);
			
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
		finally {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();
			}
		}
    }

	private static void display(ResultSet myRs) throws SQLException {
		while (myRs.next()) {
			String lastName = myRs.getString("lastname");
			String firstName = myRs.getString("firstname");
			double salary = myRs.getInt("salary");
			String department = myRs.getString("department");
			
			System.out.printf("%s, %s, %.2f, %s\n", lastName, firstName, salary, department);
		}
	}
}
  