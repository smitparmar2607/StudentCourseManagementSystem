<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="CoursePacakage.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="ServletStudentAuthenticatiopacakage.LoginServlet" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Course Management System</title>
    <style>
        :root {
  --primary-color: #213256;
  --secondary-color: #ffd700;
  --light-color: #f5f5f5;
  --dark-color: #333;
}

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: 'Roboto', Arial, sans-serif;
  background-color: var(--light-color);
}

.sidebar {
  position: fixed;
  top: 0;
  left: 0;
  width: 150px;
  height: 100%;
  background-color: var(--primary-color);
  color: var(--light-color);
  padding: 1rem;
}

.username {
  font-size: 1.2rem;
  font-weight: bold;
  margin-bottom: 1rem;
}

.sidebar a {
  display: block;
  padding: 0.5rem 0;
  color: var(--light-color);
  text-decoration: none;
  border-left: 3px solid var(--secondary-color);
  transition: 0.3s;
}

.sidebar a:hover {
  background-color: var(--secondary-color);
  color: var(--primary-color);
}

.content {
  margin-left: 250px;
  padding: 2rem;
}

h2 {
  font-size: 2rem;
  font-weight: bold;
  color: var(--dark-color);
  margin-bottom: 1rem;
  text-align: center;
}

h3 {
  font-size: 1.5rem;
  font-weight: bold;
  color: var(--dark-color);
  margin-bottom: 1rem;
  text-align: center;
}

table {
  width: 80%; /* Adjust the width as needed */
  margin: 0 auto; /* Center alignment */
  border-collapse: collapse;
  margin-top: 2rem;
}

th, td {
  padding: 0.75rem;
  border: 1px solid var(--dark-color);
  text-align: left;
}

th {
  background-color: var(--secondary-color);
  color: var(--primary-color);
  font-weight: bold;
}
        
      
    </style>
</head>
<body>
<div class="sidebar">
<% 
            String user = (String) session.getAttribute("users");
    %>
    <div class="username">
        <%= user %>
    </div>
    <a href="#">Home</a>
    <a href="#">Profile</a>
    <a href="logout.jsp">Logout</a>
</div>

    <h2>Welcome to Course Management System</h2>
    <h3>Courses:</h3>
    <table border="1">
        <thead>
            <tr>
                <th>Course Code</th>
                <th>Course Name</th>
                <th>Classroom</th>
            </tr>
        </thead>
        <tbody>
            <!-- Iterate over the courses obtained from the database -->
            <%
                String username = (String) session.getAttribute("username");
                LoginServlet loginServlet = new LoginServlet();
                List<Course> courses = loginServlet.getCoursesForUser(request,username);
                if (courses != null && !courses.isEmpty()) {
                    for (Course course : courses) {
            %>
                <tr>
                    <td><%= course.getCode() %></td>
                    <td><%= course.getName() %></td>
                    <td><%= course.getClassroom() %></td>
                </tr>
            <% 
                    }
                } else { 
            %>
            <!-- If there are no courses to display -->
            <tr>
                <td colspan="3">No courses available</td>
            </tr>
            <% 
                } 
            %>
        </tbody>
    </table>
</body>
</html>