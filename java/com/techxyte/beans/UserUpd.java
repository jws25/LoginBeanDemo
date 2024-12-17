package com.techxyte.beans;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class Users
 */
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;


public class UserUpd implements Serializable {
	
	// Variables
	static LocalDate dateToday = java.time.LocalDate.now();
	java.util.Date dateTodayConverted = Date.from(dateToday.atStartOfDay(ZoneId.systemDefault()).toInstant());
	private String name;
	private String username;
	private String password;
	private String newpassword;
	private String cnfpassword;
	private String dobStr;
	private Date dateDobConverted;
	private long duration;
	private long diffInDays;
	private String gender;
	private String location;
	private String receiveupdates;
	

	// No-arg constructor
	public UserUpd() {
	}
	
	// Getter and Setter for name
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	// Getter and Setter for username
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	// Getter and Setter for password
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	// Getter and Setter for new password
	public String getNewPassword() {
		return newpassword;
	}

	public void setNewPassword(String newpassword) {
		this.newpassword = newpassword;
	}
	
	// Getter and Setter for dobStr
	public String getDobStr() {
		return dobStr;
	}
	public void setDobStr(String dobStr) {
		this.dobStr = dobStr;
	}
	
	// Getter and Setter for dateDobConverted
	public Date getDateDobConverted() {
		return dateDobConverted;
	}
	public void setDateDobConverted(Date dateDobConverted) {
		this.dateDobConverted = dateDobConverted;
	}

	// Getter and Setter for duration
	public long getDuration() {
		return dateDobConverted.getTime() - dateTodayConverted.getTime();
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}

	// Getter and Setter for diffInDays
	public long getDiffInDays() {
		return diffInDays;
	}
	public void setDiffInDays(long diffInDays) {
		this.diffInDays = diffInDays;
	}
	
	// Getter and Setter for gender
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	// Getter and Setter for location
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	// Getter and Setter for receiveupdates
	public String getReceiveupdates() {
		return receiveupdates;
	}
	public void setReceiveupdates(String receiveupdates) {
		this.receiveupdates = receiveupdates;
	}
	
}
