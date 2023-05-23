/**
 * 
 */
package com.flipkart.exception;

import com.flipkart.constant.ConsoleColors;

/**
 * @author vanshika.tibrewal
 *
 */
public class StudentNotFoundForApprovalException extends Exception{
	
	private String StudentId;
	
	public StudentNotFoundForApprovalException(String id) {
		StudentId = id;
	}
	
	/**
	 * Getter function for professorId
	 * @return
	 */
	public String getUserId() {
		return StudentId;
	}
	

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return ConsoleColors.RED + "StudentId: " + StudentId + " not registered!" + ConsoleColors.RESET;
	}
}
