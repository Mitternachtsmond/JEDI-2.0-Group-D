package com.flipkart.exception;

import com.flipkart.constant.ConsoleColors;

/**
 * @author vanshika.tibrewal
 *
 */
public class ProfessorIdAlreadyInUseException extends Exception{
	private String ProfessorId;
	
	
	/***
	 * Setter function for ProfessorId
	 * @param userId
	 */
	
	public ProfessorIdAlreadyInUseException(String id) {
		ProfessorId = id;
	}
	
	/***
	 * Getter function for ProfessorId
	 * @param userId
	 */
	
	public String getUserId() {
		return ProfessorId;
	}
	
	
	@Override
	public String getMessage() {
		return ConsoleColors.RED + "UserId: " + ProfessorId + " is already in use." + ConsoleColors.RESET;
	}

}