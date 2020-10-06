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

// Show images to gallery page
@WebServlet("/showfiles")
public class showfiles extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletOutputStream out = response.getOutputStream();
        HttpSession session = request.getSession();
        Connection con;
        PreparedStatement pstmt;
        ResultSet rs;
        Blob photo;
        int count;
        count = (int) session.getAttribute("count");

        Blob[] blobs = new Blob[15];
        System.out.println("showfiles connected");
        try {

            // Connect to Oracle
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "jay", "1024");
            con.setAutoCommit(false);

            // Query for searching images
            String sql = "SELECT image FROM photos WHERE userid=?";
            String userid = (String) session.getAttribute("userID");
            int counter = 0;
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userid);
            rs = pstmt.executeQuery();

            // If there are any images, save them at blobs array
            while (rs.next()) {
                blobs[counter] = rs.getBlob(1);
                counter++;
            }

            response.setContentType("image/gif");
            InputStream in = blobs[count].getBinaryStream();
            int length = (int) blobs[count].length();

            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];

            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }
}
