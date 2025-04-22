package UnitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import application.Pms;
import javafx.scene.control.ListView;
import javafx.application.Platform;
/**
 * <p> Title: Private Messages JUnit Test <p>
 * 
 * <p> Description: This JUnit test tests the Pms.java class to test that private messages are both shown and sent correctly
   
   <p> Copyright: Zachary Almindo © 2025<p>
   
   @author Zachary Almindo
   @version 1.0
 */
public class Private_Message_Tests {

	@BeforeAll
	static void initJfxRuntime() {
	    Platform.startup(() -> {});
	}
	
	/**
	 * <p> Tests the Pms.java class to ensure the method showPmsStaff works properly
	 * <p> Test passes
	 */
	@Test
	void showPMS() {
		Pms pm = new Pms();
		ListView<String> PmList = new ListView<>();
		assertEquals(true, pm.showPmsStaff(PmList));
	}
	/**
	 * <p> Tests the Pms.java class to ensure the method WritePM works properly
	 * <p> Test passes
	 */
	@Test
	void WritePM() {
		Pms pm = new Pms();
		assertEquals(true, pm.WritePM("Mooey001", "Hello"));
	}
	
	
}
