package application;
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

public class ReviewerWindow {
	public void show(Stage primaryStage) {
    	primaryStage.setTitle("Reviewer Window");
    	
    	Students students = new Students();
    	
    	ListView<String> QuestionList = new ListView<>();
    	QuestionList.setEditable(true);
    	
    	Button reviewQuestion = new Button("Review a Question");
		Button ScanReviews = new Button("Look at your reviews");
		
		Label q = new Label();
		
		Label localQ = new Label();
		
		Label error = new Label();
		
		VBox qViewer = new VBox();
		
    	
		HBox buttonBox = new HBox(4, error, reviewQuestion, ScanReviews);
		
		students.StoreStudents("Test", "This is a test", "Lynn Robert Carter", 1);
		QuestionList.getItems().add("Test");
		
		// when ask question button is pressed, show new question window
		reviewQuestion.setOnAction(e -> {
			error.setText(null);
			ReviewerController.show(new Stage(), QuestionList, error, "ques");
		});
		
		ScanReviews.setOnAction(e -> {
			try {
				ReviewerList.show(new Stage(), "Mooey001");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
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
		
		
		
		buttonBox.setStyle("-fx-alignment: center; -fx-padding: 20;");
	
		BorderPane bdrLayout = new BorderPane();
		bdrLayout.setCenter(qViewer);
		bdrLayout.setLeft(QuestionList);
		bdrLayout.setBottom(buttonBox);
		
		Scene windowScene = new Scene(bdrLayout, 854, 540);
		primaryStage.setScene(windowScene);
    	
    }
}