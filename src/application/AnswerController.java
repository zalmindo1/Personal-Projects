package application;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
/**
 * JavaFX scene that handles sumbmitting new answers into the discussion board
 */
public class AnswerController {
	public static ListView<String> show(Stage superStage, ListView<String> qList, Answers answers, Student topic, Label error) {
		
		superStage.setTitle("Answer Prompter");
		
		Button submitAnswer = new Button("Submit Answer");
		Button cancel = new Button("Cancel");
		
		Label prompt = new Label("Enter your answer");
		
		TextField UserAnswer = new TextField(); 
		
		StackPane layout = new StackPane();
		
		// Store answer in array list and show in answer list
		submitAnswer.setOnAction(e -> {
			answers.StoreAnswer(UserAnswer.getText(), "Mooey001", topic.getId());

			answers.StoreAnswer(UserAnswer.getText(), topic.getName(), topic.getId());
		    qList.getItems().add(UserAnswer.getText());
		    error.setText("Success!");
			superStage.close();
		});
		
		layout.getChildren().addAll(submitAnswer, cancel, UserAnswer, prompt);
		
		StackPane.setAlignment(submitAnswer, Pos.BOTTOM_LEFT);
		StackPane.setAlignment(cancel, Pos.BOTTOM_RIGHT);
		
		StackPane.setAlignment(prompt, Pos.TOP_CENTER);
		
		Scene scene = new Scene(layout, 400, 300);
		
		superStage.setScene(scene);
		superStage.show();
		
		return qList;
	}
}