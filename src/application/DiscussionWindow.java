package application;
import java.io.FileNotFoundException;
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
 * JavaFX scene that is the discussion board, containing questions and answers
 */
public class DiscussionWindow {
	public void show(Stage primaryStage) {
    	primaryStage.setTitle("Discussion Window");
    	
    	Students students = new Students();
    	
    	ListView<String> QuestionList = new ListView<>();
    	QuestionList.setEditable(true);
    	
    	Button askQuestion = new Button("Ask a question");
		Button editQuestion = new Button("Edit Question");
		Button deleteQuestion = new Button("Delete Question");
		Button viewPM = new Button ("View PMS");
		Button Pm = new Button("Private Messages");
		Button viewReviews = new Button("View Reviews");
		Button trustedReviewers = new Button("Trusted Reviewers");
		Button whitelist = new Button("Whitelist");
		
		TextField quesSearch = new TextField();
		Button searchButton = new Button("Search");
		
		quesSearch.setMaxWidth(200);
		
		Label localQ = new Label();
		
		Label error = new Label();
		
		VBox qViewer = new VBox();
		
		HBox buttonBox = new HBox(2, error, askQuestion, editQuestion, deleteQuestion, viewPM, Pm, viewReviews, trustedReviewers, whitelist, quesSearch, searchButton);
		
		students.StoreStudents("Test", "This is a test", "Lynn Robert Carter", 1);
		QuestionList.getItems().add("Test");
		students.StoreStudents("Hello", "I need some help with TP3", "Zalmindo1", 1);
		QuestionList.getItems().add("Hello");
		
		// when ask question button is pressed, show new question window
		askQuestion.setOnAction(e -> {
			error.setText(null);
			QuestionController.show(new Stage(), QuestionList, students, error);
		});
		
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
		
		// When delete question is clicked, remove the question form the question list and remove it from array list
		deleteQuestion.setOnAction(e -> {
			students.RemoveStudentQuesByTitle(QuestionList.getSelectionModel().getSelectedItem(), QuestionList, qViewer, "Mooey001", error);
		});
		
		// When search button is clicked, sort question list to show only specific questions
		searchButton.setOnMouseClicked(e -> {
			error.setText(null);
			students.SortQuestionList(quesSearch.getText(), QuestionList);
			quesSearch.clear();
		});
		
		// when edit question is clicked, show edit question window
		editQuestion.setOnMouseClicked(e -> {
			error.setText(null);
			EditController.show(new Stage(), QuestionList, students, error);
		});
		
		// //Opens us java fx screen that handles sending private messages
		viewPM.setOnMouseClicked(e -> {
			error.setText(null);
			try {
				PmList.show(new Stage());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		//Opens up java fx screen the handles sending private messages
		Pm.setOnMouseClicked(e -> {
			error.setText(null);
			try {
				PmManager.show(new Stage(), "Zalmindo1");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		
		viewReviews.setOnAction(e -> {
			try {
				ReviewerListStu.show(new Stage(), "Zalmindo1");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		trustedReviewers.setOnAction(e -> {
			try {
				TrusterReviewerList.show(new Stage());
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