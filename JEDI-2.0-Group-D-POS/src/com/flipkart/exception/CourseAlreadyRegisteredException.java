/**
 * 
 */
package com.flipkart.exception;

import com.flipkart.constant.ConsoleColors;

/**
 * @author vanshika.tibrewal
 *
 */
public class CourseAlreadyRegisteredException extends Exception{
	
	private String courseCode;
	
	/**
	 * Constructor
	 * @param courseCode
	 */
	public CourseAlreadyRegisteredException(String courseCode) {
		this.courseCode = courseCode;
	}
	
	/**
	 * Getter method
	 * @return course code
	 */
	public String getCourseCode() {
		return courseCode;
	}
	
	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return ConsoleColors.RED + "You have already registered for " + courseCode + ConsoleColors.RESET;
	}
}
