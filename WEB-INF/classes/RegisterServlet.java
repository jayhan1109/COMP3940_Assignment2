import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  

public class RegisterServlet extends HttpServlet {  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
                    throws ServletException, IOException {  
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  

        String name=request.getParameter("username");  
        String password=request.getParameter("password");  
          
        if(password.equals("hello")){  
        out.print("Welcome, "+name);  
        HttpSession session=request.getSession();  
        session.setAttribute("username",name);
        request.getRequestDispatcher("profile.html").forward(request,response); 
        }  
        else{  
            out.print("Sorry, username or password error!");  
            request.getRequestDispatcher("login.html").forward(request, response);  
        }  
        out.close();  
    }  
} 