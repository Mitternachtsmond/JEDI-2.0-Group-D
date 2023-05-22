package com.flipkart.exception;

import com.flipkart.constant.ConsoleColors;

/**
 * Exception to check if seats are available for course registration
 * @author siddartha.c
 *
 */
public class CourseNotAvailableException extends Exception{
	
	private String courseCode;

	/**
	 * Constructor
	 * @param courseCode
	 */
	public CourseNotAvailableException(String courseCode)
	{	
		this.courseCode = courseCode;
	}


	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return  ConsoleColors.RED + "Seats are not available in : " + courseCode + ConsoleColors.RESET;
	}


}