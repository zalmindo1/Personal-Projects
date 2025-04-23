package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * <p> Title: Role Checker <p>
 * 
 * <p> Description: This java class checks what role you are
   
   <p> Copyright: Zachary Almindo © 2025<p>
   
   @author Zachary Almindo
   @version 1.0
 */
public class ValidRoleCheck {
	/**
	 * This method checks the reviewers.csv file to see if passed in username is a reviewer
	 * @param username
	 * @return true if you are a reviewer, false if otherwise
	 */
	public boolean CheckValidReviewer(String username) {
		boolean valid = false;
		File rfile = new File("reviewers.csv");
		try {
			Scanner scn = new Scanner(rfile);
			while (scn.hasNextLine()) {
				String[] revs = scn.nextLine().split(",");
				if (revs[0].equals(username)) {
					valid = true;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return valid;
	}
	/**
	 * This method checks the staff.csv file to see if passed in username is a staff member
	 * @param username
	 * @return true if you are a staff member, false if otherwise
	 */
	public boolean CheckValidStaff(String username) {
		boolean valid = false;
		File rfile = new File("staff.csv");
		try {
			Scanner scn = new Scanner(rfile);
			while (scn.hasNextLine()) {
				String[] revs = scn.nextLine().split(",");
				if (revs[0].equals(username)) {
					valid = true;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return valid;
	}
	/**
	 * This method checks the instructors.csv file to see if passed in username is an instructor
	 * @param username
	 * @return
	 */
	public boolean CheckValidInstructor (String username) {
		boolean valid = false;
		File rfile = new File("instructors.csv");
		try {
			Scanner scn = new Scanner(rfile);
			while (scn.hasNextLine()) {
				String[] revs = scn.nextLine().split(",");
				if (revs[0].equals(username)) {
					valid = true;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return valid;
	}
}
