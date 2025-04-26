package application;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * <p> Title: Instructor Window <p>
 * 
 * <p> Description: This is a JavaFX scene that is displayed when anyone with the instructor role logs into the system, allowing for them to perform instructor functions
   
   <p> Copyright: Zachary Almindo © 2025<p>
   
   @author Zachary Almindo
   @version 1.0
 */
public class InstructorWindow {
	/**
	 * Displaying the JavaFX scene
	 * @param primaryStage
	 */
	// Displays instructor window onto GUI
	public void show(Stage primaryStage) {
    	primaryStage.setTitle("Instructor Window");
    	
    	// Creates students object to store test questions
    	Students students = new Students();
    	
    	// Creates ListView to display questions
    	ListView<String> QuestionList = new ListView<>();
    	QuestionList.setEditable(true);
    	
    	// Creates whitelist, request action, and view requests buttons
    	Button whitelist = new Button("Whitelist Students");
    	Button request = new Button("Request Action");
    	Button viewReq = new Button ("View Requests");
    	Button viewCloseReq = new Button("View Closed Requests");
		Button viewPM = new Button ("View PMS");
		Button Pm = new Button("Private Messages");
		Button viewReviews = new Button("View Reviews");
		
		Label localQ = new Label();
		
		Label error = new Label();
		
		VBox qViewer = new VBox();
		
    	
		HBox buttonBox = new HBox(2, error, whitelist, viewReq, request, viewCloseReq, viewPM, Pm, viewReviews);
		
		students.StoreStudents("Test", "This is a test", "Lynn Robert Carter", 1);
		QuestionList.getItems().add("Test");
		students.StoreStudents("Hello", "I need some help with TP3", "Zalmindo1", 1);
		QuestionList.getItems().add("Hello");
		
		
		HashMap<Student,ScrollPane> answerWindows = new HashMap<>();
		
		// When a question is clicked, show who wrote the question, the question title, and question body
		QuestionList.setOnMouseClicked(e -> {
			error.setText(null);
			Student selectedQuestion = students.getStudent(QuestionList.getSelectionModel().getSelectedItem());
			qViewer.getChildren().clear();
			Answers ans = new Answers();
			if(!answerWindows.containsKey(selectedQuestion)) {
				localQ.setText(students.displayQuestion(QuestionList.getSelectionModel().getSelectedItem()));
				ScrollPane newWin = new AnswerList().display(localQ, selectedQuestion, ans, error);
				qViewer.getChildren().add(newWin);
				answerWindows.put(selectedQuestion, newWin);
			} else {
				localQ.setText(students.displayQuestion(QuestionList.getSelectionModel().getSelectedItem()));
				qViewer.getChildren().add(answerWindows.get(selectedQuestion));
			}
			});
		
		// When button is clicked, all whitelist requests will be approved
		whitelist.setOnAction(e -> {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter("reviewers.csv", true));
				writer.write("Zalmindo1,\n");
				writer.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		});
		
		// When button is clicked, the instructor will create a admin request
		request.setOnAction(e -> {
			Requests req = new Requests();
			req.ShowRequestsManager();
		});
		
		// When button is clicked, view all requests inside of requests.csv
		viewReq.setOnAction(e -> {
			Requests req = new Requests();
			req.ShowRequestListInst();
		});
		
		// When button is clicked, shows all closed requests
		viewCloseReq.setOnAction(e -> {
			Requests req = new Requests();
			req.ShowClosedRequestListInst();
		});
		
		// When view pm button is clicked, open a new window that displays all private messages sent
		viewPM.setOnAction(e -> {
			error.setText(null);
			try {
				PmList.show(new Stage());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		// When Pm button is clicked, open a new window that prompts the user to send a private message to a user
		Pm.setOnAction(e -> {
			error.setText(null);
			try {
				PmManager.show(new Stage(), "Staff123");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		
		// When view reviews button is clicked, open a new window that displays all reviews
		viewReviews.setOnAction(e -> {
			error.setText(null);
			try {
				ReviewerList.show(new Stage(), null);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		buttonBox.setStyle("-fx-alignment: center; -fx-padding: 20;");
	
		BorderPane bdrLayout = new BorderPane();
		bdrLayout.setCenter(qViewer);
		bdrLayout.setLeft(QuestionList);
		bdrLayout.setBottom(buttonBox);
		
		Scene windowScene = new Scene(bdrLayout, 854, 540);
		primaryStage.setScene(windowScene);
    	
    }
}