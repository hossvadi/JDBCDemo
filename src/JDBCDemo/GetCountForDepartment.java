package JDBCDemo;

import java.sql.*;

public class GetCountForDepartment {

	public static void main(String[] args) throws Exception {

		Connection myConn = null;
		CallableStatement myStmt = null;

		try {
			// Get a connection to database
			myConn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/demo", "hjvdz", "Hj1374@@");

			String theDepartment = "Engineering";

			// Prepare the stored procedure call
			myStmt = myConn.prepareCall("{call get_count_for_department(?, ?)}");
			
			// Set the parameters
			myStmt.setString(1,theDepartment);
			myStmt.registerOutParameter(2, Types.INTEGER);
			
			// Call stored parameters
			System.out.println("\n\nCalling stored procedure. get_count_for_department('" + theDepartment + "', ?)");
			myStmt.execute();
			System.out.println("Finished calling stored procedure");

			// get the value of the INPUT parameter
			int theCount = myStmt.getInt(2);
			
			// Show the result 
			System.out.println("\n\nThe count = " + theCount);
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