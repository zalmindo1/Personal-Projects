package unitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.Students;
/**
 * <p> Title: Questions JUnit Test <p>
 * 
 * <p> Description: This JUnit test tests the Students.java class to ensure that the correct question is being displaying and in the correct format onto the GUI
   
   <p> Copyright: Zachary Almindo © 2025<p>
   
   @author Zachary Almindo
   @version 1.0
 */
public class QuestionTests {
	/**
	 * <p> Tests the Students.java class to ensure that the displayQuestion method works correctly by returning the correct string
	 * <p> Test passes
	 */
	@Test
	void ShowQuestions() {
		Students stu = new Students();
		stu.StoreStudents("Test", "This is a test", "Lynn Robert Carter", 1);
		assertEquals("Lynn Robert Carter Wrote:\nTest\nThis is a test", stu.displayQuestion("Test"));
	}
}
