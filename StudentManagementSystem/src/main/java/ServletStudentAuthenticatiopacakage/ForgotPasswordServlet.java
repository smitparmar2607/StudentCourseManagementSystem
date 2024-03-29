package ServletStudentAuthenticatiopacakage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DBPackage.dbconnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/ForgotPasswordServlet")
public class ForgotPasswordServlet extends HttpServlet {
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameter
        String email = request.getParameter("email");
        
        // Perform forgot password logic
        if (emailExists(email)) {
        	request.setAttribute("email", email);
            RequestDispatcher dispatcher = request.getRequestDispatcher("reset_password.jsp");
            dispatcher.forward(request, response);
        } else {
            // If email does not exist, redirect user to forgot password page with error message
            response.sendRedirect("forgot_password.jsp?error=email_not_found");
        }
    }

    private boolean emailExists(String email) {
        // Query to check if the email exists in the database
        String query = "SELECT * FROM Users WHERE email=?";

        try (Connection conn = dbconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // If email exists, return true
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Error occurred, return false
        }
    }
}