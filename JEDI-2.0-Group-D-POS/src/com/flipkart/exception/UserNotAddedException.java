package com.flipkart.exception;

import com.flipkart.constant.ConsoleColors;

/**
 * Exception to check if user cannot be added
 * @author vanshika.tibrewal
 *
 */
public class UserNotAddedException extends Exception{
	private String userId;
	
	public UserNotAddedException(String id) {
		userId = id;
	}
	
	/**
	 * Getter function for UserId
	 * @return
	 */
	public String getUserId() {
		return userId;
	}
	

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return ConsoleColors.RED + "UserId: " + userId + " not added!" + ConsoleColors.RESET;
	}
}