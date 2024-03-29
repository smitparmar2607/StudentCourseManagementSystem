package ServletStudentAuthenticatiopacakage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import DBPackage.dbconnection;


@WebServlet("/resetPasswordServlet")
public class resetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve new password and confirmation password from the form
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        String email = request.getParameter("email");

        // You can add further validation here if necessary
        if (newPassword.equals(confirmPassword)) {
            // Update the password in your database or wherever it's stored
            boolean passwordUpdated = updatePasswordInDatabase(newPassword,email);

            if (passwordUpdated) {
                // Password updated successfully
                out.println("<h1>Password Reset Successful</h1>");
            } else {
                // Handle password update failure
                out.println("<h1>Password Reset Failed</h1>");
            }
        } else {
            // Password and confirmation password do not match
            out.println("<h1>Passwords do not match</h1>");
        }
    }

    // Placeholder method to update password in database
    private boolean updatePasswordInDatabase(String newPassword,String email) {
    
        // SQL query to update password
        String sql = "UPDATE Users SET password = ? WHERE email = ?";

        try (
        		Connection connection = dbconnection.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            // Set parameters
            statement.setString(1, newPassword);
            statement.setString(2, email); 

            // Execute update
            int rowsUpdated = statement.executeUpdate();

            // Check if password was successfully updated
            if (rowsUpdated > 0) {
                return true; // Password updated successfully
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Password update failed
    }
}