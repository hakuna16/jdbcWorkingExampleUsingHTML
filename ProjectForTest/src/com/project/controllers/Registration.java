package com.project.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;

@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Registration() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
	}
	
	String driver = "com.mysql.jdbc.Driver";
    String URL = "jdbc:mysql://localhost:3306/project";
    String userName = "root";
    String password = "password";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zipcode = request.getParameter("zipcode");
		
		
		System.out.println( email+ " " + password1 + " " + password2 + " " + city + " " + address1 + " " + address2 + " " + state + " " + zipcode );
		
		try {
			Class.forName(driver);
			
			Connection con = DriverManager.getConnection(URL, userName, password);
			
			String query =   "INSERT INTO registration(email,password,password2,address1,address2,city,state,postalcode) values(?,?,?,?,?,?,?,?)";
			
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password1);
			preparedStatement.setString(3, password2);
			preparedStatement.setString(4, address1);
			preparedStatement.setString(5, address2);
			preparedStatement.setString(6, city);
			preparedStatement.setString(7, state);
			preparedStatement.setString(8, zipcode);
			
			int rowInserted = preparedStatement.executeUpdate();
			
			if(rowInserted>0) {
				System.out.println("Row Inserted Successfully");
			}else {
				System.out.println("Error in query");
			}
			
			
	        preparedStatement.close();
	        con.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		response.sendRedirect("/ProjectForTest/userPage.html");
	}

}