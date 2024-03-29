package ServletStudentAuthenticatiopacakage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DBPackage.dbconnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import CoursePacakage.Course;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Perform login logic
        boolean isAuthenticated = authenticateUser(username, password);

        if (isAuthenticated) {
            // Fetch courses related to the logged-in user
            List<Course> courses = getCoursesForUser(request,username);

            // Set courses as an attribute to be passed to home.jsp
            request.setAttribute("courses", courses);
            request.setAttribute("users", username);

            // Redirect user to home.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
            dispatcher.forward(request, response);
        } else {
            // If authentication fails, redirect user to login page with error message
            response.sendRedirect("login.jsp?error=invalid_credentials");
        }
    }

    // Dummy method to authenticate user (replace with actual authentication logic)
    private boolean authenticateUser(String username, String password) {
    	String query = "SELECT * FROM Users WHERE username=? AND password=?";
        try (Connection conn = dbconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // If there's a matching user, authentication succeeds
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Authentication fails due to database error
        }
    }
    public List<Course> getCoursesForUser(HttpServletRequest request,String username) {
        // Fetch courses related to the user from the database
        List<Course> courses = new ArrayList<>();
        String query = "SELECT course_code,course_name,classroom FROM UserCourses WHERE username=?";
        try (Connection conn = dbconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String code = rs.getString("course_code");
                String name = rs.getString("course_name");
                String classroom = rs.getString("classroom");
                Course course = new Course(code,name, classroom);
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        Object attribute = request.getAttribute("courses");
        if (attribute instanceof List<?>) {
            List<?> attributeList = (List<?>) attribute;
            for (Object obj : attributeList) {
                if (obj instanceof Course) {
                    courses.add((Course) obj);
                } else {
                    // Handle unexpected type (for example, log a warning)
                    System.out.println("Unexpected type found: " + obj.getClass().getName());
                }
            }
        } else if (attribute != null) {
            // Handle unexpected type (for example, log a warning)
            System.out.println("Unexpected type found: " + attribute.getClass().getName());
        }
        return courses;
    }
}