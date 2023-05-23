package com.flipkart.exception;

import com.flipkart.constant.ConsoleColors;

/**
 * Exception to check if seats are available for course registration
 * @author vanshika.tibrewal
 *
 */
public class SeatNotAvailableException extends Exception{
	
	private String courseCode;

	/**
	 * Constructor
	 * @param courseCode
	 */
	public SeatNotAvailableException(String courseCode)
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