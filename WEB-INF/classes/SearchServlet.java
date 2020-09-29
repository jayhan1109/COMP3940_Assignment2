import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SearchServlet extends HttpServlet {
  public void doPost(HttpServletRequest request, HttpServletResponse response) 
  throws ServletException, IOException {
      Connection con = null;
		try {
                    Class.forName("oracle.jdbc.OracleDriver");
                    } 
                    catch (Exception ex) { }
		try {
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "oracle1");
con.setAutoCommit(false);
			//using Transactions
                 PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO staff (name, address) VALUES (?,?)");

            preparedStatement.setString(1, "AAA");
            preparedStatement.setString(2, "AAA");
            int row = preparedStatement.executeUpdate();
preparedStatement.setString(1, "BBB");
            preparedStatement.setString(2, "BBB");
            row = preparedStatement.executeUpdate();
preparedStatement.setString(1, "CCC");
            preparedStatement.setString(2, "CCC");
            row = preparedStatement.executeUpdate();


			
			
			
			con.commit();
			con.setAutoCommit(true);
			

			

                        Statement stmt2 = con.createStatement();
                        ResultSet rs = stmt2.executeQuery("SELECT * FROM staff");

			//using result set meta data print the names of the columns of your query result

			ResultSetMetaData rsmd = rs.getMetaData();
     			int numberOfColumns = rsmd.getColumnCount();
			for (int i = 1; i <= numberOfColumns; i++) 
				System.out.println("   " + rsmd.getColumnName(i));

			System.out.println("\n\n");

 
                        while (rs.next()) {
	                  String sname = rs.getString("name");
	                  String saddress = rs.getString("address");
                          System.out.println("   " + sname + "  " + saddress); 
	                }
                        stmt2.close();
			System.out.println("\n\n");

			//using Database MetaData print the names of all the tables

			DatabaseMetaData md = con.getMetaData(); 
/*			rs = md.getTables(null, null, null, null);         
			while (rs.next()) {  
				String tname = rs.getString("TABLE_NAME"); 
				System.out.println(" " + tname);         
			} 
			System.out.println("\n\n");
*/
			//using Database MetaData print the names of all the columns of student table
	
			rs = md.getColumns(null, null, "staff", null);         
			while (rs.next()) {  
				String name = rs.getString("COLUMN_NAME"); 
				String type = rs.getString("TYPE_NAME");      
				int size = rs.getInt("COLUMN_SIZE");
				System.out.println("Column name: [" + name + "]; type: [" + type  + "]; size: [" + size + "]");         
			} 

			con.close();
		} 
		catch(SQLException ex) {
			try {
				con.rollback();
                                con.close();
			} catch (SQLException e) {
				System.out.println("\nError Rolling back\n");	
			} 
			System.out.println("\n--- SQLException caught ---\n"); 
			while (ex != null) { 
				System.out.println("Message: " + ex.getMessage ()); 
				System.out.println("SQLState: " + ex.getSQLState ()); 
				System.out.println("ErrorCode: " + ex.getErrorCode ()); 
				ex = ex.getNextException(); 
				System.out.println("");
			} 
		} 
  }
}