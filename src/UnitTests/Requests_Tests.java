package UnitTests;

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
	
	@Test
	void displayOpenRequests() {
		Requests rq = new Requests();
		ListView<String> reqs = new ListView<>();
		assertEquals(true, rq.showOpenRequests(reqs));
	}
	
	@Test
	void showRequstList() {
		Requests rq = new Requests();
		Platform.runLater(() -> {
			assertEquals(true, rq.ShowRequestList());
		});
	}
	
	@Test
	void showInstructorRqList() {
		Requests rq = new Requests();
		Platform.runLater(() -> {
			assertEquals(true, rq.ShowRequestListInst());
		});
	}
	
	@Test
	void showAdminRequestList() {
		Requests rq = new Requests();
		Platform.runLater(() -> {
			assertEquals(true, rq.ShowAdminRequestList());
		});
	}
	
	@Test
	void showRequestManager() {
		Requests rq = new Requests();
		Platform.runLater(() -> {
			assertEquals(true, rq.ShowRequestsManager());
		});
	}
	
	@Test
	void writeRequest() {
		String sender = "Instructor";
		String desc = "This is a test request";
		Requests rq = new Requests();
		assertEquals(true, rq.WriteRequest(sender, desc));
	}
	
	@Test
	void closeRequest() throws IOException {
		Requests rq = new Requests();
		ListView<String> reqs = new ListView<>();
		assertEquals(true, rq.CloseRequest(reqs));
	}
	
	@Test
	void showClosedRequests() {
		Requests rq = new Requests();
		ListView<String> reqs = new ListView<>();
		assertEquals(true, rq.showCLosedRequests(reqs));
	}
	
	@Test
	void closedRequestList() {
		Requests rq = new Requests();
		Platform.runLater(() -> {
			assertEquals(true, rq.ShowClosedRequestList());
		});
	}
	
	@Test
	void closedRqListInst() {
		Requests rq = new Requests();
		Platform.runLater(() -> {
			assertEquals(true, rq.ShowClosedRequestListInst());
		});
	}
	
	@Test
	void openRequest() throws IOException {
		Requests rq = new Requests();
		ListView<String> reqs = new ListView<>();
		assertEquals(true, rq.OpenRequest(reqs));
	}
	
	@Test
	void editRequest() throws IOException {
		String desc = "Test Edit";
		Requests rq = new Requests();
		ListView<String> reqs = new ListView<>();
		assertEquals(true, rq.EditRequest(desc, reqs));
	}
	
	@Test
	void showEditRequestWindow() {
		Requests rq = new Requests();
		ListView<String> reqs = new ListView<>();
		Platform.runLater(() -> {
			assertEquals(true, rq.ShowEditRequestWindow(reqs));
		});
	}
}
