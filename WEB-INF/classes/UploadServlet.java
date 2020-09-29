import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.oreilly.servlet.MultipartRequest;
import javax.servlet.annotation.WebServlet;

public class UploadServlet extends HttpServlet {
public void doPost(HttpServletRequest request, HttpServletResponse response) 
throws ServletException, IOException {
    //  response.setContentType("text/html");
    //  PrintWriter out = response.getWriter();
    //  MultipartRequest m = new MultipartRequest(request,"c:/tomcat/webapps/upload/images");
    //  out.print("successfully uploaded");

      response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        HttpSession session=request.getSession();
        String username = (String)session.getAttribute("username");
        out.print("Welcome, "+username); 
   }
}