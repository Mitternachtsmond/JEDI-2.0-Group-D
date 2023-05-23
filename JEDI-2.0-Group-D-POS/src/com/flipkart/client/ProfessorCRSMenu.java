/**
 * 
 */
package com.flipkart.client;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.constant.ConsoleColors;
import com.flipkart.exception.GradeNotAllotedException;
import com.flipkart.service.ProfessorInterface;
import com.flipkart.service.ProfessorOperation;
import com.flipkart.validator.ProfessorValidator;


/**
 * @author vanshika.tibrewal
 *
 */
public class ProfessorCRSMenu {
	
	ProfessorInterface professorInterface = ProfessorOperation.getInstance();

	/**
	 * @param profID
	 */
	public void createMenu(String profID) {
		Scanner in = new Scanner(System.in);
		
		int input;
		while (CRSApplication.loggedin) {
			System.out.println("\n");
			System.out.println("-------Professor Menu-----------");
			System.out.println("\n");
			System.out.println("1. view Courses");
			System.out.println("2. view Enrolled Students");
			System.out.println("3. add Grades");
			System.out.println("4. logout");
			System.out.println("\n--------------------------------\n");
			System.out.printf("Choose From Menu: ");
			
			input = in.nextInt();
			switch (input) {
			case 1:
				getCourses(profID);
				break;
			case 2:
				viewEnrolledStudents(profID);
				break;
			case 3:
				addGrade(profID);
				break;
			case 4:
				CRSApplication.loggedin = false;
				return;
			default:
				System.out.println(ConsoleColors.RED + "\nPlease select appropriate option...\n" + ConsoleColors.RESET);
			}
		}
		in.close();
	}
	
	public void viewEnrolledStudents(String profID) {
		System.out.println(String.format("%20s %20s %20s","COURSE CODE","COURSE NAME","Student" ));
		try {
			List<EnrolledStudent> enrolledStudents = new ArrayList<EnrolledStudent>();
			enrolledStudents = professorInterface.viewEnrolledStudents(profID);
			for (EnrolledStudent obj: enrolledStudents) {
				System.out.println(String.format("%20s %20s %20s",obj.getCourseCode(), obj.getCourseName(),obj.getStudentId()));
			}
			
		} catch(Exception ex) {
			System.out.println(ConsoleColors.RED + ex.getMessage()+"Something went wrong, please try again later!" + ConsoleColors.RESET);
		}
	}
	
	public void getCourses(String profId) {
		try {
			List<Course> coursesEnrolled = professorInterface.viewCourses(profId);
			System.out.println(String.format("%20s %20s %20s","COURSE CODE","COURSE NAME","No. of Students" ));
			for(Course obj: coursesEnrolled) {
				System.out.println(String.format("%20s %20s %20s",obj.getCourseCode(), obj.getCourseName(),10- obj.getSeats()));
			}		
		} catch(Exception ex) {
			System.out.println(ConsoleColors.RED + "Something went wrong!"+ex.getMessage() + ConsoleColors.RESET);
		}
	}
	
	public void addGrade(String profId) {	
		Scanner in = new Scanner(System.in);
		
		String courseCode, grade, studentId;
		try {
			List<EnrolledStudent> enrolledStudents = new ArrayList<EnrolledStudent>();
			enrolledStudents = professorInterface.viewEnrolledStudents(profId);
			System.out.println(String.format("%20s %20s %20s","COURSE CODE","COURSE NAME","Student ID" ));
			for (EnrolledStudent obj: enrolledStudents) {
				System.out.println(String.format("%20s %20s %20s",obj.getCourseCode(), obj.getCourseName(),obj.getStudentId()));
			}
			List<Course> coursesEnrolled = new ArrayList<Course>();
			coursesEnrolled	= professorInterface.viewCourses(profId);
			System.out.println("\n----------------Add Grade--------------\n");
			System.out.printf("Enter student id: ");
			studentId = in.nextLine();
			System.out.printf("Enter course code: ");
			courseCode = in.nextLine();
			System.out.println("Enter grade: ");
			grade = in.nextLine();
			if ((ProfessorValidator.isValidStudent(enrolledStudents, studentId))
			&& (ProfessorValidator.isValidCourse(coursesEnrolled, courseCode))) {
				professorInterface.addGrade(studentId, courseCode, grade);
				System.out.println(ConsoleColors.GREEN + "\nGrade added successfully for "+studentId + ConsoleColors.RESET);
			} else {
				System.out.println(ConsoleColors.RED + "\nInvalid data entered, try again!" + ConsoleColors.RESET);
			}
		} catch(GradeNotAllotedException ex) {
			System.out.println(ConsoleColors.RED + "\nGrade cannot be added for"+ex.getStudentId() + ConsoleColors.RESET);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
