<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Reset Password</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }
        
        h1 {
            text-align: center;
            color: #333;
        }
        
        form {
            max-width: 400px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        
        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }
        
        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 16px;
        }
        
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            border: none;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 16px;
            color: #fff;
            cursor: pointer;
        }
        
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h1>Reset Password</h1>
    <%-- Retrieve email from request attribute --%>
    <% String email = (String) request.getAttribute("email"); %>
    <%-- Display email in the form or use it as needed --%>
    <form action="resetPasswordServlet" method="post">
        <label for="email">Email:</label>
        <input type="text" name="email" value="<%= email %>" readonly>
        <label for="newPassword">New Password:</label>
        <input type="password" name="newPassword">
        <label for="confirmPassword">Confirm Password:</label>
        <input type="password" name="confirmPassword">
        <input type="submit" value="Reset Password">
    </form>
</body>
</html>
