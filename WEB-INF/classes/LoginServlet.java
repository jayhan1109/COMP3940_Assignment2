import java.io.IOException;  
import java.io.PrintWriter;  
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  

public class LoginServlet extends HttpServlet {  
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
                                 PreparedStatement preparedStatement = con.prepareStatement("select * from users where userid=? and password=?");
                
                            preparedStatement.setString(1, name);
                            preparedStatement.setString(2, password);
                            
                            ResultSet rs=preparedStatement.executeQuery();  
                             
                        if(rs.next()){
                            HttpSession session=request.getSession();  
                            session.setAttribute("username",name);
                            session.setAttribute("id",rs.getString("ID"));
                            request.getRequestDispatcher("profile.html").forward(request,response); 
                        }else{
                            request.getRequestDispatcher("login.html").forward(request,response); 
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
                    
                        out.close();    
    }  
} 