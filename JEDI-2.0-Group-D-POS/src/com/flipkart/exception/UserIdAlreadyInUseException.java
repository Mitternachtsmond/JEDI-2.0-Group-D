package com.flipkart.exception;

import com.flipkart.constant.ConsoleColors;

/**
 * @author vanshika.tibrewal
 *
 */
public class UserIdAlreadyInUseException extends Exception{
	private String userId;
	
	
	/***
	 * Setter function for UserId
	 * @param userId
	 */
	
	public UserIdAlreadyInUseException(String id) {
		userId = id;
	}
	
	/***
	 * Getter function for UserId
	 * @param userId
	 */
	
	public String getUserId() {
		return userId;
	}
	
	
	@Override
	public String getMessage() {
		return ConsoleColors.RED + "UserId: " + userId + " is already in use." + ConsoleColors.RESET;
	}

}