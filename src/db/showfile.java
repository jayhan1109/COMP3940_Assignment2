package db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// Show an image from search page
@WebServlet("/showfile")
public class showfile extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletOutputStream out = response.getOutputStream();
        HttpSession session = request.getSession();
        Connection con;
        PreparedStatement pstmt;
        ResultSet rs;
        Blob photo;
        System.out.println("showfile connected");
        try {

            // Connect to Oracle
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "jay", "1024");
            con.setAutoCommit(false);

            // Query for searching image with parameters from session attributes
            String sql = "SELECT image FROM photos WHERE fileName =? and userid=?";
            String userid = (String) session.getAttribute("userID");
            String fileName = (String) session.getAttribute("key");

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, fileName);
            pstmt.setString(2, userid);
            rs = pstmt.executeQuery();

            // If there is an image, show it
            if (rs.next()) {
                photo = rs.getBlob(1);
                response.setContentType("image/gif");
                InputStream in = photo.getBinaryStream();
                int length = (int) photo.length();

                int bufferSize = 1024;
                byte[] buffer = new byte[bufferSize];

                while ((length = in.read(buffer)) != -1) {
                    out.write(buffer, 0, length);
                }

            } else {
                System.out.println("image not found");
            }

        } catch (Exception e) {
            System.out.print(e);
        }
    }
}
