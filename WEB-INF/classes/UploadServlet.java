import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.oreilly.servlet.MultipartRequest;

public class UploadServlet extends HttpServlet {
public void doPost(HttpServletRequest request, HttpServletResponse response) 
throws ServletException, IOException {
  HttpSession session=request.getSession();  

  String id = (String)session.getAttribute("id");
  Part photo=request.getPart("photo");
  String caption=request.getParameter("title");

  InputStream inputStream = photo.getInputStream();
  PrintWriter pw=response.getWriter();
  try
  {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      String str = "jdbc:oracle:thin:@localhost:1521:XE";
      Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "jay", "1024");
      PreparedStatement pstmt=con.prepareStatement("insert into photos(userid,filename,caption,picture) values(?,?,?,?)");
      pstmt.setString(1, id);
      pstmt.setString(2, photo.getName());
      pstmt.setString(3, caption);
      pstmt.setBlob(4, inputStream);
      int size=pstmt.executeUpdate();
      if(size>0)
      {
          pw.println("<html>Image Uploaded Successfully.</html>");
      }
      else
      {
          pw.println("<html>Image could not be uploaded.</html>");
      }
  }
  catch(SQLException e)
  {
      e.printStackTrace();
  }
  catch(ClassNotFoundException e)
  {
      e.printStackTrace();
  }
  }
}