package com.in28minutes.rest.webservices.restfullwebservices.Exception;

import java.util.Date;

public class ExceptionResponse {
	private Date timeStamp;
	private String message;
	private String details;
	public Date getTimeStamp() {
		return timeStamp;
	}
	public String getMessage() {
		return message;
	}
	public String getDetails() {
		return details;
	}
	public ExceptionResponse() {
		
	}
	public ExceptionResponse(Date date, String message, String details) {
		super();
		this.timeStamp = date;
		this.message = message;
		this.details = details;
	}
	
	

}
