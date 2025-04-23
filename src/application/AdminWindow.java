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
 * <p> Title: Staff Window <p>
 * 
 * <p> Description: This is a JavaFX scene that is displayed when anyone with the admin role logs into the system, allowing for them to perform admin functions
   
   <p> Copyright: Zachary Almindo © 2025<p>
   
   @author Zachary Almindo
   @version 1.0
 */
public class AdminWindow {
	// Displays admin window onto GUI
	public void show(Stage primaryStage) {
		// Sets title for admin window
    	primaryStage.setTitle("Admin Window");
    	
    	Students students = new Students();
    	
    	ListView<String> QuestionList = new ListView<>();
    	QuestionList.setEditable(true);
    	
    	Button viewReq = new Button ("View Requests");
    	Button viewCloseReq = new Button("View Closed Requests");
		
		TextField quesSearch = new TextField();
		Button searchButton = new Button("Search");
		
		quesSearch.setMaxWidth(200);
		
		Label localQ = new Label();
		
		Label error = new Label();
		
		VBox qViewer = new VBox();
		
    	
		HBox buttonBox = new HBox(2, error, viewReq, viewCloseReq, quesSearch, searchButton);
		
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
		
		// When button is clicked, open admin request list
		viewReq.setOnAction(e -> {
			Requests req = new Requests();
			req.ShowAdminRequestList();
		});
		
		// When button is clicked, shows all closed requests
		viewCloseReq.setOnAction(e -> {
			Requests req = new Requests();
			req.ShowClosedRequestList();
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