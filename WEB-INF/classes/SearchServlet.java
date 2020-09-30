import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SearchServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String caption = request.getParameter("title");
        String id = (String) session.getAttribute("id");

        Connection con = null;

        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (Exception ex) {
        }
        try {
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "jay", "1024");
            con.setAutoCommit(false);
            //using Transactions
            PreparedStatement preparedStatement = con.prepareStatement("select picture from photos where userid=? and caption=?");

            preparedStatement.setString(1, id);
            preparedStatement.setString(2, caption);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                response.setContentType("image/jpg");
                Blob blob = rs.getBlob(1);
                System.out.println(blob);
                InputStream is = blob.getBinaryStream();

                int length;

                while ((length = is.read()) != -1)

                    out.write(length);

                is.close();

            } else {
                out.println("<html><h2>Couldn't find the image.<h2>"
                        + "<a href=" + "profile.html" + ">Go Back</a></html>");
            }

            con.close();
        } catch (SQLException ex) {
            try {
                con.rollback();
                con.close();
            } catch (SQLException e) {
                System.out.println("\nError Rolling back\n");
            }
            System.out.println("\n--- SQLException caught ---\n");
            while (ex != null) {
                System.out.println("Message: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("ErrorCode: " + ex.getErrorCode());
                ex = ex.getNextException();
                System.out.println("");
            }
        }

        out.close();
    }
}