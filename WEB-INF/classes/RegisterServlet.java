import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;  

public class RegisterServlet extends HttpServlet {  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
                    throws ServletException, IOException { 
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  

        String name=request.getParameter("username");  
        String password=request.getParameter("password");  

        Connection con = null;

		try {
                    Class.forName("oracle.jdbc.OracleDriver");
                    } 
                    catch (Exception ex) { }
		try {
		    con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "jay", "1024");
            con.setAutoCommit(false);
			//using Transactions
                 PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO users (userid, password) VALUES (?,?)");

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            
            int row = preparedStatement.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
		
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
        
        HttpSession session=request.getSession();  
        session.setAttribute("username",name);
        request.getRequestDispatcher("profile.html").forward(request,response); 

        out.close();  
    }  
} 

