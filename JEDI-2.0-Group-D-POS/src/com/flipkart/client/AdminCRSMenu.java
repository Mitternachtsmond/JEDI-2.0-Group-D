/**
 * 
 */
package com.flipkart.client;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.constant.ConsoleColors;
import com.flipkart.constant.GenderConstant;
import com.flipkart.constant.NotificationTypeConstant;
import com.flipkart.constant.RoleConstant;
import com.flipkart.exception.CourseExistsAlreadyException;
import com.flipkart.exception.CourseNotDeletedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.ProfessorNotAddedException;
import com.flipkart.exception.StudentNotFoundForApprovalException;
import com.flipkart.exception.UserIdAlreadyInUseException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.service.AdminInterface;
import com.flipkart.service.AdminOperation;
import com.flipkart.service.NotificationInterface;
import com.flipkart.service.NotificationOperation;
import com.flipkart.service.RegistrationInterface;
import com.flipkart.service.RegistrationOperation;

/**
 * @author nishit.singh
 *
 */
public class AdminCRSMenu {

	AdminInterface adminOperation = AdminOperation.getInstance();
	Scanner sc = new Scanner(System.in);
	NotificationInterface notificationInterface=NotificationOperation.getInstance();
	RegistrationInterface registrationInterface = RegistrationOperation.getInstance();
	
	/**
	 * Method to Create Admin Menu
	 */
	public void createMenu(){
		
		while(CRSApplication.loggedin) {
			System.out.println("\n");
			System.out.println("**********Admin Menu***********");
			System.out.println("\n");
			System.out.println("1. View course in catalog");
			System.out.println("2. Add Course to catalog");
			System.out.println("3. Delete Course from catalog");
			System.out.println("4. Approve Students by ID");
			System.out.println("5. View Pending Approvals");
			System.out.println("6. Approve all students");
			System.out.println("7. Add Professor");
			System.out.println("8. Assign Professor To Courses");
			System.out.println("9. Generate Report Card");
			System.out.println("10. Logout");
			System.out.println("\n***********");
			
			int choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				viewCoursesInCatalogue();
				break;
				
			case 2:
				addCourseToCatalogue();
				break;
				
			case 3:
				removeCourse();
				break;
				
			case 4:
				approveStudent();
				break;

			case 5:
				viewPendingAdmissions();
				break;

			case 6:
				approveAllStudent();
				break;

			case 7:
				addProfessor();
				break;
			
			case 8:
				assignCourseToProfessor();
				break;
				
			case 9:
				generateReportCard();
				break;
			
			case 10:
				CRSApplication.loggedin = false;
				return;

			default:
				System.out.println(ConsoleColors.RED + "** Wrong Choice **" + ConsoleColors.RESET);
			}
		}
	}

	private void approveAllStudent(){
		
		System.out.println("List of unapproved students: ");
		
		List<Student> studentList= adminOperation.viewPendingAdmissions();
		
		try {
			
			if(studentList.size() == 0) {

				System.out.println( ConsoleColors.RED + "No students to verify" + ConsoleColors.RESET);
				return;
			}
			
			
			for( Student student: studentList){
				
				adminOperation.approveStudent(student.getStudentId(), studentList);
					
				//notificationInterface.sendNotification(NotificationTypeConstant.REGISTRATION, student.getStudentId(), null,0);
			}
			System.out.println(ConsoleColors.GREEN + "All students are verified" + ConsoleColors.RESET);
		}
		catch (StudentNotFoundForApprovalException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void generateReportCard() 
	{
		
		List<Grade> grade_card=null;
		boolean isReportGenerated = true;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nEnter the StudentId for report card generation : ");
		String studentId = sc.next();
		
		try 
		{
			adminOperation.checkStudentId(studentId);
			adminOperation.setGeneratedReportCardTrue(studentId);
			if(isReportGenerated) {
				grade_card = registrationInterface.viewGradeCard(studentId);
				System.out.println(String.format("%-20s %-20s %-20s","COURSE CODE", "COURSE NAME", "GRADE"));
				
				if(grade_card.isEmpty())
				{
					System.out.println(ConsoleColors.RED + "You haven't registered for any course" + ConsoleColors.RESET);
					return;
				}
				
				for(Grade obj : grade_card)
				{
					System.out.println(String.format("%-20s %-20s %-20s",obj.getCrsCode(), obj.getCrsName(),obj.getGrade()));
				}
			}
			else
				System.out.println(ConsoleColors.RED + "Report card not yet generated" + ConsoleColors.RESET);
		} 
		catch (SQLException e) 
		{

			System.out.println(e.getMessage());
		}
		catch (StudentNotFoundForApprovalException e){
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Method to assign Course to a Professor
	 */
	private void assignCourseToProfessor() {
		List<Professor> professorList= adminOperation.viewProfessors();
		System.out.println("\n********** Professor ********** ");
		System.out.println(String.format("%20s | %20s | %20s ", "ProfessorId", "Name", "Designation"));
		for(Professor professor : professorList) {
			System.out.println(String.format("%20s | %20s | %20s ", professor.getUserId(), professor.getName(), professor.getDesignation()));
		}
		
		
		System.out.println("\n\n");
		List<Course> courseList= adminOperation.viewCourses();
		System.out.println("****** Course ******");
		System.out.println(String.format("%20s | %20s | %20s", "CourseCode", "CourseName", "ProfessorId"));
		for(Course course : courseList) {
			System.out.println(String.format("%20s | %20s | %20s", course.getCourseCode(), course.getCourseName(), course.getInstructorId()));
		}
		
		System.out.println("Enter Course Code:");
		String courseCode = sc.next();
		
		System.out.println("Enter Professor's User Id:");
		String userId = sc.next();
		
		try {
			
			adminOperation.assignCourse(courseCode, userId);
			System.out.println(ConsoleColors.GREEN + "Professor Assigned" + ConsoleColors.RESET);
		
		}
		catch(CourseNotFoundException | UserNotFoundException  e) {
			
			System.out.println(e.getMessage());
		}
		
	}

	/**
	 * Method to add Professor to DB
	 */
	private void addProfessor() {
		
		System.out.println("Enter User Id:");
		String userId = sc.next();
		Professor professor = new Professor(userId);
		
		System.out.println("Enter Professor Name:");
		String professorName = sc.next();
		professor.setName(professorName);
		
		System.out.println("Enter Department:");
		String department = sc.next();
		professor.setDepartment(department);
		
		System.out.println("Enter Designation:");
		String designation = sc.next();
		professor.setDesignation(designation);
		
		System.out.println("Enter Password:");
		String password = sc.next();
		professor.setPassword(password);
		
		System.out.println("Enter GenderConstant: \t 1: Male \t 2.Female \t 3.Other ");
		int gender = sc.nextInt();
		
		if(gender==1)
			professor.setGender(GenderConstant.MALE);
		else if(gender==2)
			professor.setGender(GenderConstant.FEMALE);
		else if(gender==3)
			professor.setGender(GenderConstant.OTHER);
		
		System.out.println("Enter Address:");
		String address = sc.next();
		professor.setAddress(address);
		
		professor.setRole(RoleConstant.PROFESSOR);
		
		try {
			adminOperation.addProfessor(professor);
		} catch (ProfessorNotAddedException | UserIdAlreadyInUseException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Method to view Students who are yet to be approved
	 * @return List of Students whose admissions are pending
	 */
	private List<Student> viewPendingAdmissions() {
		
		List<Student> pendingStudentsList= adminOperation.viewPendingAdmissions();
		if(pendingStudentsList.size() == 0) {
			System.out.println(ConsoleColors.RED + "No students pending approvals" + ConsoleColors.RESET);
			return pendingStudentsList;
		}
		System.out.println(String.format("%20s | %20s | %20s", "StudentId", "Name", "GenderConstant"));
		for(Student student : pendingStudentsList) {
			System.out.println(String.format("%20s | %20s | %20s", student.getStudentId(), student.getName(), student.getGender().toString()));
		}
		return pendingStudentsList;
	}

	/**
	 * Method to approve a Student using Student's ID
	 */
	private void approveStudent() {
		
		System.out.println("List of unapproved students: ");
		
		List<Student> studentList = viewPendingAdmissions();
		if(studentList.size() == 0) {
			
			
			return;
		}
		
		try {
			
			System.out.println("Enter Student's ID:");
			String studentUserIdApproval = sc.next();
			
			adminOperation.approveStudent(studentUserIdApproval, studentList);
			System.out.println( ConsoleColors.GREEN + "\nStudent Id : " +studentUserIdApproval+ " has been approved\n" + ConsoleColors.RESET);
			//send notification from system
			//notificationInterface.sendNotification(NotificationTypeConstant.REGISTRATION, studentUserIdApproval, null,0);
	
		} catch (StudentNotFoundForApprovalException e) {
			System.out.println(e.getMessage());
		}
	
		
	}

	/**
	 * Method to delete Course from catalogue
	 * @throws CourseNotFoundException 
	 */
	private void removeCourse() {
		List<Course> courseList = viewCoursesInCatalogue();
		System.out.println("Enter Course Code:");
		String courseCode = sc.next();
		
		try {
			adminOperation.removeCourse(courseCode, courseList);
			System.out.println(ConsoleColors.GREEN + "\nCourse Deleted : "+courseCode+"\n" + ConsoleColors.RESET);
		} catch (CourseNotFoundException e) {
			
			System.out.println(e.getMessage());
		}
		catch (CourseNotDeletedException e) {
			
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Method to add Course to catalogue
	 * @throws CourseExistsAlreadyException 
	 */
	private void addCourseToCatalogue() {
		List<Course> courseList = viewCoursesInCatalogue();

		sc.nextLine();
		System.out.println("Enter Course Code:");
		String courseCode = sc.nextLine();
		
		System.out.println("Enter Course Name:");
		String courseName = sc.next();
		
		System.out.println("Enter Course Fees:");
		int courseFee = sc.nextInt();
		
		Course course = new Course(courseCode, courseName,"", 10 , courseFee);
		course.setCourseCode(courseCode);
		course.setCourseName(courseName);
		course.setSeats(10);
		course.setCourseFees(courseFee);
		
		try {
		adminOperation.addCourse(course, courseList);
		System.out.println( ConsoleColors.GREEN + "Course Added Successfully" +  ConsoleColors.RESET);
		}
		catch (CourseExistsAlreadyException e) {
			System.out.println(ConsoleColors.RED + "Course already existed "+e.getMessage() + ConsoleColors.RESET);
		}

	}
	
	/**
	 * Method to display courses in catalogue
	 * @return List of courses in catalogue
	 */
	private List<Course> viewCoursesInCatalogue() {
		List<Course> courseList = adminOperation.viewCourses();
		if(courseList.size() == 0) {
			System.out.println(ConsoleColors.RED + "Nothing present in the catalogue!" + ConsoleColors.RESET);
			return courseList;
		}
		System.out.println(String.format("%20s | %20s | %20s","COURSE CODE", "COURSE NAME", "INSTRUCTOR"));
		for(Course course : courseList) {
			System.out.println(String.format("%20s | %20s | %20s", course.getCourseCode(), course.getCourseName(), course.getInstructorId()));
		}
		return courseList;
	}
}