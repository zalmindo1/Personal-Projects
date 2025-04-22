package UnitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import application.Reviewers;
import javafx.application.Platform;
import javafx.scene.control.ListView;
/**
 * <p> Title: Reviews JUnit Test <p>
 * 
 * <p> Description: This JUnit test tests the Reviewers.java class to ensure that reviews are being displaying onto the GUI correctly
   
   <p> Copyright: Zachary Almindo © 2025<p>
   
   @author Zachary Almindo
   @version 1.0
 */
public class StaffShowReviewsTest {
	
	@BeforeAll
	static void initJfxRuntime() {
	    Platform.startup(() -> {});
	}
	/**
	 * <p> Tests the Reviewers.java class to make sure the reviews from the reviews.csv file are being added to the ListView correctly using the ShowReviewsStaff method
	 * <p> Test passes
	 */
	@Test
	void ShowReviews() {
		Reviewers rev = new Reviewers();
		ListView<String> revs = new ListView<>();
		assertEquals(true, rev.ShowReviewsStaff(revs));
	}

}
