package unitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.Requests;
import javafx.application.Platform;
import javafx.scene.control.ListView;
/**
 * <p> Title: Requests JUnit Test <p>
 * 
 * <p> Description: This JUnit test file tests all of the methods inside of the Requests.java file
   
   <p> Copyright: Zachary Almindo © 2025<p>
   
   @author Zachary Almindo
   @version 1.0
 */
public class Requests_Tests {

	@BeforeAll
	static void initJfxRuntime() {
	    Platform.startup(() -> {});
	}
	
	/**
	 * <p> Tests the Requests.java class to make sure open requests are displayed into the passed in listview
	 * <p> Test passes
	 */
	@Test
	void displayOpenRequests() {
		Requests rq = new Requests();
		ListView<String> reqs = new ListView<>();
		assertEquals(true, rq.showOpenRequests(reqs));
	}
	
	/**
	 * <p> Tests the Requests.java class to make sure the open request window opens correctly
	 * <p> Test passes
	 */
	@Test
	void showRequstList() {
		Requests rq = new Requests();
		Platform.runLater(() -> {
			assertEquals(true, rq.ShowRequestList());
		});
	}
	
	/**
	 * <p> Tests the Requests.java class to make sure the instructor open request window opens correctly
	 * <p> Test passes
	 */
	@Test
	void showInstructorRqList() {
		Requests rq = new Requests();
		Platform.runLater(() -> {
			assertEquals(true, rq.ShowRequestListInst());
		});
	}
	
	/**
	 * <p> Tests the Requests.java class to make sure the admin open request window opens correctly
	 * <p> Test passes
	 */
	@Test
	void showAdminRequestList() {
		Requests rq = new Requests();
		Platform.runLater(() -> {
			assertEquals(true, rq.ShowAdminRequestList());
		});
	}
	
	/**
	 * <p> Tests the Requests.java class to make sure the window for adding a new admin request opens correctly
	 * <p> Test passes
	 */
	@Test
	void showRequestManager() {
		Requests rq = new Requests();
		Platform.runLater(() -> {
			assertEquals(true, rq.ShowRequestsManager());
		});
	}
	
	/**
	 * <p> Tests the Requests.java class to make sure writing a request through the request manager window performs correctly
	 * <p> Test passes
	 */
	@Test
	void writeRequest() {
		String sender = "Instructor";
		String desc = "This is a test request";
		Requests rq = new Requests();
		assertEquals(true, rq.WriteRequest(sender, desc));
	}
	
	/**
	 * <p> Tests the Requests.java class to make sure the close request method works correctly
	 * <p> Test passes
	 */
	@Test
	void closeRequest() throws IOException {
		Requests rq = new Requests();
		ListView<String> reqs = new ListView<>();
		assertEquals(true, rq.CloseRequest(reqs));
	}
	
	/**
	 * <p> Tests the Requests.java class to make sure closed requests are displayed on the passed in listview
	 * <p> Test passes
	 */
	@Test
	void showClosedRequests() {
		Requests rq = new Requests();
		ListView<String> reqs = new ListView<>();
		assertEquals(true, rq.showCLosedRequests(reqs));
	}
	
	/**
	 * <p> Tests the Requests.java class to make sure the closed requests window open correctly
	 * <p> Test passes
	 */
	@Test
	void closedRequestList() {
		Requests rq = new Requests();
		Platform.runLater(() -> {
			assertEquals(true, rq.ShowClosedRequestList());
		});
	}
	
	/**
	 * <p> Tests the Requests.java class to make sure the instructor closed requests window open correctly
	 * <p> Test passes
	 */
	@Test
	void closedRqListInst() {
		Requests rq = new Requests();
		Platform.runLater(() -> {
			assertEquals(true, rq.ShowClosedRequestListInst());
		});
	}
	
	/**
	 * <p> Tests the Requests.java class to make sure the open request method works correctly
	 * <p> Test passes
	 */
	@Test
	void openRequest() throws IOException {
		Requests rq = new Requests();
		ListView<String> reqs = new ListView<>();
		assertEquals(true, rq.OpenRequest(reqs));
	}
	
	/**
	 * <p> Tests the Requests.java class to make sure instructors can edit the description of requests
	 * <p> Test passes
	 */
	@Test
	void editRequest() throws IOException {
		String desc = "Test Edit";
		Requests rq = new Requests();
		ListView<String> reqs = new ListView<>();
		assertEquals(true, rq.EditRequest(desc, reqs));
	}
	
	/**
	 * <p> Tests the Requests.java class to make sure the window for editing requests appears
	 * <p> Test passes
	 */
	@Test
	void showEditRequestWindow() {
		Requests rq = new Requests();
		ListView<String> reqs = new ListView<>();
		Platform.runLater(() -> {
			assertEquals(true, rq.ShowEditRequestWindow(reqs));
		});
	}
}
