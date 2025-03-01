package application;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.*;

public class QuestionController {
	
	
	public static ListView<String> show(Stage superStage, ListView<String> qList, Students stu, Label error) {
		Random ran = new Random();
		
		superStage.setTitle("Question Prompter");
		
		Button submitQuesition = new Button("Submit Question");
		Button cancel = new Button("Cancel");
		
		Label prompt = new Label("Enter your question");
		
		Label quesTitle = new Label("Enter your question title");
		Label quesBody  = new Label("Enter your question body");
		
		TextField quesInputTitle = new TextField(); 
		TextField quesInputBody = new TextField(); 
		
		quesInputBody.setTranslateY(50.0);
		quesTitle.setTranslateY(-20.0);
		quesBody.setTranslateY(30.0);
		
		StackPane layout = new StackPane();
		
		// When submit question is clicked, add question to both question list and array list
		submitQuesition.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if (quesInputTitle.getText().isBlank() || quesInputBody.getText().isBlank()) {
					error.setText("Error, one of the question fields is blank");
				}
				else {
					stu.StoreStudents(quesInputTitle.getText(), quesInputBody.getText(), "Mooey001", ran.nextInt(100000));
					qList.getItems().add(quesInputTitle.getText());
					error.setText(null);
					superStage.close();
				}
			}
		});
		
		
		// Close window
		cancel.setOnAction(e -> {
			error.setText(null);
			superStage.close();
		});
		
		layout.getChildren().addAll(submitQuesition, cancel, quesInputTitle, quesInputBody, prompt, quesTitle, quesBody);
		
		StackPane.setAlignment(submitQuesition, Pos.BOTTOM_LEFT);
		StackPane.setAlignment(cancel, Pos.BOTTOM_RIGHT);
		
		StackPane.setAlignment(prompt, Pos.TOP_CENTER);
		
		Scene scene = new Scene(layout, 400, 300);
		
		superStage.setScene(scene);
		superStage.show();
		
		return qList;
	}
}