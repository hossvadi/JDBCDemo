package JDBCDemo;

import java.io.File;
import java.io.FileWriter;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadClobDemo {
	public static void main(String[] args) throws Exception {
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		Reader input = null;
		FileWriter output = null;
		
		try {
			// Get a connection to Database
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "hjvdz", "Hj1374@@");
			
			// Execute statement
			myStmt = myConn.createStatement();
			String sql = "select longResume from employees where email='john.doe@foo.com'";
			myRs = myStmt.executeQuery(sql);
			
			// 3. Set parameter for resume file name
			File theFile = new File("CLOB_resume_DB.txt");
			output = new FileWriter(theFile);
			
			if (myRs.next()) {
				input = myRs.getCharacterStream("longResume");
				System.out.println("Reading resume from database ...");
				System.out.println(sql);
				
				int theChar;
				while ((theChar = input.read()) > 0) {
					output.write(theChar);
				}
			}
			
			System.out.println("\nSSaved to file: " + theFile.getAbsolutePath());
			System.out.println("\nCompleted successfully!");
			
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {			
			if (input != null) {
				input.close();
			}

			if (output != null) {
				output.close();
			}
			
			close(myConn, myStmt);			
		}
	}

	private static void close(Connection myConn, Statement myStmt)
			throws SQLException {

		if (myStmt != null) {
			myStmt.close();
		}
		
		if (myConn != null) {
			myConn.close();
		}
	}

}