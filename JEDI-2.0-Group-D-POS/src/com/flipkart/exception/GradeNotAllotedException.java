/**
 * 
 */
package com.flipkart.exception;

import com.flipkart.constant.ConsoleColors;

/**
 * @author vanshika.tibrewal
 *
 */
public class GradeNotAllotedException extends Exception{
	 
	private String studentId;
	 
	/**
	 * Constructor
	 * @param studentId2
	 */
	 public GradeNotAllotedException(String studentId2)
	 {
		 this.studentId=studentId2;
	 }
	 
	 /**
	  * Getter function for studentId
	  * @return
	  */
	 public String getStudentId()
	 {
		 return studentId;
	 }
	 
	 /**
		 * Message returned when exception is thrown
	 */
	 
	 public String getMessage() 
	 {
			return ConsoleColors.RED + "Student with id: " + studentId + "hasn't been alloted a grade yet" + ConsoleColors.RESET;
	 }
}
