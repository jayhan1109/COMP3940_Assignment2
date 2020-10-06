package db;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


// Servlet for upload image
@WebServlet("/uploadAction")
@MultipartConfig
public class Fileupload extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get session object
        HttpSession session = request.getSession();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String userid = (String) session.getAttribute("userID");

        // Get parameter from request
        String caption = request.getParameter("caption");
        String fileName = request.getParameter("title");
        Part filePart = request.getPart("file");
        InputStream is = filePart.getInputStream();


        Db db = new Db();
        db.upload(userid, fileName, caption, is);
        response.sendRedirect("main.jsp");


    }

}
