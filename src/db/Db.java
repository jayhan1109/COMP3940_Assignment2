package db;

import java.io.InputStream;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Db class to connect Oracle
 */

public class Db {

    // Variables
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;

    // Constructor
    public Db() {

        // Connect to Oracle
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "jay", "1024");
            con.setAutoCommit(false);

            // Handling Error
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    // Login function
    public int login(String userID, String userPassword) {
        String SQL = "SELECT userPassword FROM users WHERE userID =?";
        String encryptedPassword = encrypt(userPassword);

        try {
            // Check the user id and password
            pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, userID);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                if (rs.getString(1).equals(encryptedPassword)) {
                    return 1; // login success
                } else {
                    return 0; // password not match
                }
            }
            return -1;

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                con.close();
                pstmt.close();
                rs.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return -2;
    }

    // Get the number of rows
    public int maxRow(String userID) {
        String sql = "SELECT fileName from photos WHERE userid = ?";
        int counter = 0;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userID);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                counter++;
            }
            System.out.println(counter);
            return counter;
        } catch (Exception e) {
            System.out.println(e);
        }
        return counter;
    }

    // Register and Save information to Oracle DB
    public int register(String userID, String userPassword) {
        String sql = "INSERT INTO users (userID, userPassword) VALUES(?,?)";
        String encryptedPassword = encrypt(userPassword);
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userID);
            pstmt.setString(2, encryptedPassword);
            int row = pstmt.executeUpdate();
            con.commit();
            return row;
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }

    // Encrypt the password
    public String encrypt(String password) {
        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            password = hexString.toString();
            return password;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    // Testing database
    public void test() {
        String sql = "INSERT INTO files(fileName, fileRealName, caption, userid) VALUES(?,?,?,?)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "test");
            pstmt.setString(2, "test");
            pstmt.setString(3, "test");
            pstmt.setString(4, "Test");
            pstmt.executeUpdate();
            con.commit();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Upload an image to Oracle DB
    public void upload(String userid, String fileName, String caption, InputStream is) {
        String sql = "INSERT INTO photos(userid, fileName, caption, image) VALUES(?,?,?,?)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userid);
            pstmt.setString(2, fileName);
            pstmt.setString(3, caption);
            pstmt.setBlob(4, is);
            pstmt.executeUpdate();
            con.commit();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
