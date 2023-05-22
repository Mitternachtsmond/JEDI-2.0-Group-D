
package com.flipkart.exception;

import com.flipkart.constant.ConsoleColors;

/**
 * Exception to check if user exists 
 * @author siddartha.c 
 *
 */
public class UserNotFoundException extends Exception {

	private String userId;

	/***
	 * Setter function for UserId
	 * @param userId
	 */
	public UserNotFoundException(String id) {
		userId = id;
	}

	/**
	 * Message thrown by exception
	 */
	
	public String getMessage() {
		return ConsoleColors.RED + "User with userId: " + userId + " not found." + ConsoleColors.RESET;
	}
	

}