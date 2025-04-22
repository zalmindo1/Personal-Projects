package UnitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.Answers;
/**
 * <p> Title: Answers JUnit Test <p>
 * 
 * <p> Description: This JUnit test tests the Answers.java class to ensure that the correct answer is being displayed onto the GUI
   
   <p> Copyright: Zachary Almindo © 2025<p>
   
   @author Zachary Almindo
   @version 1.0
 */
public class AnswersTests {

	/**
	 * <p> Determines if the getAnsInfo method from answers class correctly formats the string to be displayed into the GUI
	 * <p> Test passes
	 */
	@Test
	void ShowAnswers() {
		Answers ans = new Answers();
		ans.StoreAnswer("This is a test answer", "Zalmindo1", 1);
		assertEquals("User Zalmindo1 answered: \nThis is a test answer\n\n\n", ans.getAnsInfo("This is a test answer"));
	}

}
