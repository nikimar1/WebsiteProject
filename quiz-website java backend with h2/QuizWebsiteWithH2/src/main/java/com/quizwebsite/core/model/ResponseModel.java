package com.quizwebsite.core.model;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResponseModel {
	
	public Date timestamp;
	private String formattedDate;
	private boolean success;
	private User user;
	
	public ResponseModel()
	{
		this.timestamp = null;
		this.setFormattedDate(null);
		this.success=false;
		this.setUser(null);
	}
	
	public void setSuccess(boolean success)
	{
		this.success=success;
	}
	
	public boolean getSuccess()
	{
		return this.success;
	}
	
	public void setTimestamp()
	{
		timestamp = new Date();
		String strDateFormat = "hh:mm:ss a";
		DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		setFormattedDate(dateFormat.format(timestamp));
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFormattedDate() {
		return formattedDate;
	}

	public void setFormattedDate(String formattedDate) {
		this.formattedDate = formattedDate;
	}

}
