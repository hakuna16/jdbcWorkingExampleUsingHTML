package com.project.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class AddBooks
 */
@WebServlet("/AddBooks")
public class AddBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddBooks() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	String driver = "com.mysql.jdbc.Driver";
	String URL = "jdbc:mysql://localhost:3306/project";
	String userName = "root";
	String password = "password";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String bookName = request.getParameter("bookName");
		String authourName = request.getParameter("authourName");
		String type = request.getParameter("type");
		String stock = request.getParameter("Stock");

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(URL, userName, password);

			String insertQuery = "INSERT INTO book(bookName,authourName,type,stock) values(?,?,?,?)";

			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(insertQuery);
			preparedStatement.setString(1, bookName);
			preparedStatement.setString(2, authourName);
			preparedStatement.setString(3, type);
			preparedStatement.setString(4, stock);

			int rowInserted = preparedStatement.executeUpdate();

			if (rowInserted > 0) {
				System.out.println("Row Inserted Successfully");
			} else {
				System.out.println("Error in query");
			}
			
			preparedStatement.close();	        
			con.close();
			
			response.sendRedirect("/ProjectForTest/booksLended.html");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
