package com.flipkart.exception;

import com.flipkart.constant.ConsoleColors;

/**
 * @author vanshika.tibrewal
 *
 */
public class StudentIdAlreadyInUseException extends Exception{
	private String StudentId;
	
	
	/***
	 * Setter function for ProfessorId
	 * @param userId
	 */
	
	public StudentIdAlreadyInUseException(String id) {
		StudentId = id;
	}
	
	/***
	 * Getter function for ProfessorId
	 * @param userId
	 */
	
	public String getUserId() {
		return StudentId;
	}
	
	
	@Override
	public String getMessage() {
		return ConsoleColors.RED + "userId: " + StudentId + " is already in use." + ConsoleColors.RESET;
	}

}