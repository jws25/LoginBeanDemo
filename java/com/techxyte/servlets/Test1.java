package com.techxyte.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

import com.techxyte.beans.Users;

@WebServlet("/pratham1")
public class Test1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static LocalDate dateToday = java.time.LocalDate.now();
	java.util.Date dateTodayConverted = Date.from(dateToday.atStartOfDay(ZoneId.systemDefault()).toInstant());

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Create a new User bean
		Users user = new Users();

		// Set properties of the User bean using form data
		user.setDobStr(request.getParameter("dob"));

		PrintWriter out = response.getWriter();
		out.println("Message from Servlet");
		out.println(dateToday);
		out.println(user.getDobStr());
		//long duration = dateDobConverted.getTime() - dateTodayConverted.getTime();
		//long diffInDays = TimeUnit.MILLISECONDS.toDays(duration);
		
	}

}
