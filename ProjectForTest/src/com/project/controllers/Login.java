package com.project.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;


/**
 * Servlet implementation class LoginValidatorServlet
 * 
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Login() {
        super();
    }
   
    String driver = "com.mysql.jdbc.Driver";
    String URL = "jdbc:mysql://localhost:3306/project";
    String userName = "root";
    String password = "password";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("inputEmail");
		String pwd = request.getParameter("inputPassword");
		
		System.out.println("Emailid: " + id + "password " + pwd);
		
		HttpSession httpSession = request.getSession();
		
		try {
			Class.forName(driver);  
			Connection con = DriverManager.getConnection(URL, userName, password);
			Statement stmt=con.createStatement();
			
			ResultSet rs = stmt.executeQuery("select * from login where userName='"+id+"' and password='"+pwd+"'");
			
			if(rs != null && rs.next())  {
				System.out.println("Valid user");
				String userName = rs.getString(1);
				String password = rs.getString(2);
				
				//System.out.println(userName+ " ----------" + password);
				
				if(userName.equals("admin")) {
					System.out.println(userName+ " ----------" + password);
					response.sendRedirect("/ProjectForTest/userAdminPage.html");
				}else {
					response.sendRedirect("/ProjectForTest/userPage.html");
				}
			}else {
				System.out.println("Invalid User");
				response.sendRedirect("/ProjectForTest/login.html");
			}
			rs.close();
	        stmt.close();
	        con.close();
	        
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
	}

}
