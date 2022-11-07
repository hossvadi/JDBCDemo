package JDBCDemo;

import java.sql.*;

public class GreetTheDepartment {

	public static void main(String[] args) throws Exception {

		Connection myConn = null;
		CallableStatement myStmt = null;

		try {
			// Get a connection to database
			myConn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/demo", "hjvdz", "Hj1374@@");

			String theDepartment = "Engineering";

			// Prepare the stored procedure call
			myStmt = myConn.prepareCall("{call greet_the_department(?)}");
			
			// Set the parameters
			myStmt.registerOutParameter(1, Types.VARCHAR);
			myStmt.setString(1,theDepartment);

			// Call stored procedure
			System.out.println("\n\nCalling stored procedure. greet_the_department('" + theDepartment + "')");
			myStmt.execute();
			System.out.println("Finished calling stored procedure");

			// get the value of the INPUT parameter
			String theResult = myStmt.getString(1);
			
			// Show the result 
			System.out.println("\n\nThe result = " + theResult);
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			close(myConn, myStmt);
		}
	}

	private static void close(Connection myConn, Statement myStmt) throws SQLException {
		if (myStmt != null) {
			myStmt.close();
		}

		if (myConn != null) {
			myConn.close();
		}
	}
}