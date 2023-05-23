/**
 * 
 */
package com.flipkart.exception;

import com.flipkart.constant.ConsoleColors;

/**
 * @author vanshika.tibrewal
 *
 */
public class CourseLimitExceededException extends Exception {
	
	private int num;

	/**
	 * Constructor
	 * @param num number of courses
 	 */
	public CourseLimitExceededException(int num )
	{	
		this.num = num;
	}


	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() 
	{
		return ConsoleColors.RED + "You have already registered for " + num + " courses" + ConsoleColors.RESET;
	}
}
