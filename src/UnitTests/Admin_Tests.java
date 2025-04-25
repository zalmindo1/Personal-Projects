package unitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import application.Admin;
import application.Requests;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
/**
 * <p> Title: Admin JUnit Test <p>
 * 
 * <p> Description: This JUnit test file tests all of the methods inside of the Admin.java file
   
   <p> Copyright: Zachary Almindo © 2025<p>
   
   @author Zachary Almindo
   @version 1.0
 */
public class Admin_Tests {

	@BeforeAll
	static void initJfxRuntime() {
	    Platform.startup(() -> {});
	}
	/**
	 * <p> Tests the Admin.java class to make sure the all users window appears when the button "View All Users" is clicked
	 * <p> Test passes
	 */
	@Test
	void showalluserswindow() {
		Admin an = new Admin();
		Platform.runLater(()-> {
			assertEquals(true, an.ShowAllUsersWindow());
		});
	}
	
	/**
	 * <p> Tests the Admin.java class to make sure the all users are populated into the passed in listview
	 * <p> Test passes
	 */
	@Test
	void PopUsers() {
		Admin an = new Admin();
		ListView<String> users = new ListView<>();
		assertEquals(true, an.PopulateAllUsers(users));
	}
	
	/**
	 * <p> Tests the Admin.java class to make sure when an admin's username is selected, the label updates accordingly
	 * <p> Test passes
	 */
	@Test
	void CheckUserRoleAdmin() {
		Admin an = new Admin();
		Label l = new Label();
		String username = "Mooey";
		assertEquals(true, an.CheckRoleOfUser(l, username));
	}
	
	/**
	 * <p> Tests the Admin.java class to make sure when a students's username is selected, the label updates accordingly
	 * <p> Test passes
	 */
	@Test
	void CheckUserRoleStudent() {
		Admin an = new Admin();
		Label l = new Label();
		String username = "Zalmindo1";
		assertEquals(true, an.CheckRoleOfUser(l, username));
	}
	
	/**
	 * <p> Tests the Admin.java class to make sure when a reviewers's username is selected, the label updates accordingly
	 * <p> Test passes
	 */
	@Test
	void CheckUserRoleReviewer() {
		Admin an = new Admin();
		Label l = new Label();
		String username = "Mooey001";
		assertEquals(true, an.CheckRoleOfUser(l, username));
	}
	
	/**
	 * <p> Tests the Admin.java class to make sure when a staff member's username is selected, the label updates accordingly
	 * <p> Test passes
	 */
	@Test
	void CheckUserRoleStaff() {
		Admin an = new Admin();
		Label l = new Label();
		String username = "Staff123";
		assertEquals(true, an.CheckRoleOfUser(l, username));
	}
	
	/**
	 * <p> Tests the Admin.java class to make sure when an instructor's username is selected, the label updates accordingly
	 * <p> Test passes
	 */
	@Test
	void CheckUserRoleInstructor() {
		Admin an = new Admin();
		Label l = new Label();
		String username = "Instructor";
		assertEquals(true, an.CheckRoleOfUser(l, username));
	}
	
}
