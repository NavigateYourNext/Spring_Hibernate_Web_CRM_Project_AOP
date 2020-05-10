package com.code.testdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class TestDbServlet
 */
@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String user = "postgres";
		String pass = "admin";
		
		String jdbcurl = "jdbc:postgresql://localhost:5432/web_customer_tracker";
		String driver = "org.postgresql.Driver";
		
		try
		{
			PrintWriter out = response.getWriter();
			out.println("Connection to database: "+jdbcurl);
			
			Class.forName(driver);
			Connection myConn = DriverManager.getConnection(jdbcurl,user,pass);
			
			if(myConn != null)
			{
			 out.println("Database Connected!!!");
			}
			else
			{
				out.println("Database Not Connected!!!");
			}
			
			myConn.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
