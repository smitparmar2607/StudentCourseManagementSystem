<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>Logout</title>
    <style>
    :root {
  --background-color: #484848;
  --text-color: #ffffff;
  --gradient: radial-gradient(circle, rgba(0, 136, 204, 1) 0%, rgba(96, 125, 139, 1) 100%);
}

body {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: var(--gradient);
  color: var(--text-color);
  margin: 0;
  padding: 0;
  font-family: 'Roboto', Arial, Helvetica, sans-serif;
}

h2 {
  font-size: 2rem;
  font-weight: 400;
  margin-bottom: 1rem;
}

p {
  font-size: 1.1rem;
  margin-top: 1rem;
}

a {
  color: var(--text-color);
  background: var(--background-color);
  padding: 0.7rem 2.5rem;
  border-radius: 20px;
  text-decoration: none;
  transition: 0.3s;
}

a:hover {
  box-shadow: 1px 3px 14px 0px rgba(0, 0, 0, 0.2);
}

    </style>
</head>
<body>
<%
       
HttpSession currentSession = request.getSession(false);
if (currentSession != null) {
    currentSession.invalidate();
}
    %>
    <h2>You have been successfully logged out.</h2>
    <p><a href="login.jsp">Click here to login again</a></p>
</body>
</html>