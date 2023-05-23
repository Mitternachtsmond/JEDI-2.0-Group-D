/**
 * 
 */
package com.flipkart.exception;

import com.flipkart.constant.ConsoleColors;

/**
 * @author vanshika.tibrewal
 *
 */
public class CourseNotDeletedException extends Exception {
	
	private String courseCode;
	
	public CourseNotDeletedException(String courseCode)
	{	
		this.courseCode = courseCode;
	}

	/**
	 * Getter function for course code
	 * @return
	 */
	public String getCourseCode()
	{
		return courseCode;
	}
	
	/**
	 * Message thrown by exception
	 */
	@Override
	public String getMessage() 
	{
		return ConsoleColors.RED + "Course with courseCode: " + courseCode + " can't be deleted." + ConsoleColors.RESET;
	}
}
