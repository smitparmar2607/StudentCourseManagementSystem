package ServletStudentAuthenticatiopacakage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DBPackage.dbconnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        // Perform signup logic
        try {
            // Create database connection
            Connection conn = dbconnection.getConnection();

            // Define SQL statement for insertion
            String sql = "INSERT INTO Users (username, password, email) VALUES (?, ?, ?)";

            // Create prepared statement
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, email);

            // Execute the statement
            int rowsInserted = pstmt.executeUpdate();

            // Close resources
            pstmt.close();
            conn.close();

            // Check if user was successfully inserted
            if (rowsInserted > 0) {
                // Redirect user to signup success page
                response.sendRedirect("login.jsp");
            } else {
                // If insertion failed, redirect to an error page or display an error message
                response.sendRedirect("signup_error.jsp");
            }
        } catch (SQLException e) {
            // Handle any database errors
            e.printStackTrace(); // Print error details to console (for debugging)
            response.sendRedirect("signup_error.jsp"); // Redirect to an error page
        }
    }
}