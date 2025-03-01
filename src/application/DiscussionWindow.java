package application;
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

public class DiscussionWindow {
	public void show(Stage primaryStage) {
    	primaryStage.setTitle("Discussion Window");
    	
    	Students students = new Students();
    	
    	ListView<String> QuestionList = new ListView<>();
    	QuestionList.setEditable(true);
    	
    	Button askQuestion = new Button("Ask a question");
		Button editQuestion = new Button("Edit Question");
		Button deleteQuestion = new Button("Delete Question");
		
		TextField quesSearch = new TextField();
		Button searchButton = new Button("Search");
		
		quesSearch.setMaxWidth(200);
		
		Label q = new Label();
		
		Label localQ = new Label();
		
		Label error = new Label();
		
		VBox qViewer = new VBox();
		
    	
		HBox buttonBox = new HBox(4, error, askQuestion, editQuestion, deleteQuestion, quesSearch, searchButton);
		
		students.StoreStudents("Test", "This is a test", "Lynn Robert Carter", 1);
		QuestionList.getItems().add("Test");
		
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
		
		buttonBox.setStyle("-fx-alignment: center; -fx-padding: 20;");
	
		BorderPane bdrLayout = new BorderPane();
		bdrLayout.setCenter(qViewer);
		bdrLayout.setLeft(QuestionList);
		bdrLayout.setBottom(buttonBox);
		
		Scene windowScene = new Scene(bdrLayout, 854, 540);
		primaryStage.setScene(windowScene);
    	
    }
}