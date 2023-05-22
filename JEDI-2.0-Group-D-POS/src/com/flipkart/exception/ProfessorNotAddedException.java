package com.flipkart.exception;

import com.flipkart.constant.ConsoleColors;

/**
 * Exception to check if the professor is not added successfully by admin
 * @author siddartha.c 
 *
 */
public class ProfessorNotAddedException extends Exception{
	private String professorId;
	
	public ProfessorNotAddedException(String id) {
		professorId = id;
	}
	
	/**
	 * Getter function for professorId
	 * @return
	 */
	public String getUserId() {
		return this.professorId;
	}
	

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return ConsoleColors.RED + "ProfessorId: " + professorId + " not added!" + ConsoleColors.RESET;
	}
}