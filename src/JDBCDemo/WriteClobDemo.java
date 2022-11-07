package JDBCDemo;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class WriteClobDemo {

	public static void main(String[] args) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null; 
		FileReader input = null;
		
		try {
			// Get a connection to database
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "hjvdz", "Hj1374@@");
			
			// Prepare Statement
			String sql = "update employees set longResume=? where email='john.doe@foo.com'";
			myStmt = myConn.prepareStatement(sql);
			
			// Set parameter for resume filename
			File theFile = new File("sample_resume.txt");
			input = new FileReader(theFile);
			myStmt.setCharacterStream(1, input);
			
			System.out.println("Reading input file: " + theFile.getAbsolutePath());
			
			// Execute statement
			System.out.append("\nStirong resume in database: " + theFile);
			System.out.println(sql);
			
			myStmt.executeUpdate();
			
			System.out.println("\ncompleted successfully!");
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			if (input != null) {
				input.close();
			}
			close(myConn, myStmt);	
		}
	}
	
	private static void close(Connection myConn, Statement myStmt) throws SQLException {
		if (myConn != null) {
			myConn.close();
		}
		
		if (myStmt != null) {
			myStmt.close();
		}
	}
}
