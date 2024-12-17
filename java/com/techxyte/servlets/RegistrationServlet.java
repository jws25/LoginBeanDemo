package com.techxyte.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

import com.techxyte.beans.Users;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	// Variables
	static boolean reset = true;
	static LocalDate dateToday = java.time.LocalDate.now();
	java.util.Date dateTodayConverted = Date.from(dateToday.atStartOfDay(ZoneId.systemDefault()).toInstant());

	// JDBC URL, username and password of Oracle database
	private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521/XE";
	private static final String USERNAME = "system";
	private static final String PASSWORD = "admin";
	
	// JDBC variables for opening and managing connection
	private Connection conn;
	
	public void init() throws ServletException {
 
		try {
			// Load the Oracle JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Open a connection
			conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		// Create a new User bean
		Users user = new Users();
		
		// Set properties of the User bean using form data
		user.setName(request.getParameter("name"));
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));	
		user.setCnfpassword(request.getParameter("cnfpassword"));
		user.setDobStr(request.getParameter("dob"));
		user.setDateDobConverted(java.sql.Date.valueOf(user.getDobStr()));
		user.setDuration(user.getDateDobConverted().getTime() - dateTodayConverted.getTime());
		user.setDiffInDays(TimeUnit.MILLISECONDS.toDays(user.getDuration()));
		user.setGender(request.getParameter("gender"));
		user.setLocation(request.getParameter("location"));
		user.setReceiveupdates(request.getParameter("receiveupdates"));
	
		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)){
			
			// SQL query to insert data
			String sql = "INSERT INTO users (name, username, password, dateOfBirth, gender, location, receiveupdates) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getUsername()); 
			
			// Password
			if(user.getPassword().equals(user.getCnfpassword()))
				pstmt.setString(3, user.getPassword()); // Note: In a real application, always use hash passwords
			else {
				RequestDispatcher rd = request.getRequestDispatcher("passwordConfimationFailure.html");
				rd.forward(request, response);
			}
			
			// Date of Birth
			if(Math.abs(user.getDiffInDays()) > 6408) {
				pstmt.setDate(4, java.sql.Date.valueOf(user.getDobStr()));
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("minimumAgeViolation.html");
				rd.forward(request, response);
			}
			
			pstmt.setString(5, user.getGender());
			pstmt.setString(6, user.getLocation());
			pstmt.setString(7, user.getReceiveupdates());
			
			// Execute the query
			int rowsInserted = pstmt.executeUpdate();
			
			if (rowsInserted > 0) {			
				RequestDispatcher rd = request.getRequestDispatcher("registrationSuccess.html");
				rd.forward(request, response);			
			} 
			
		} catch (Exception e) {
			out.println("Error: " + e.getMessage());
		}
		
	} // doPost

} // RegistrationServlet
