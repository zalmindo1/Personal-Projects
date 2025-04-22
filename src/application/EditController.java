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
/**
 * JavaFX scene that handles editing student questions
 */
public class EditController {
	public static ListView<String> show(Stage superStage, ListView<String> qList, Students stu, Label error) {
		superStage.setTitle("Edit Question");
		
		Button submitQuesition = new Button("Edit Question");
		Button cancel = new Button("Cancel");
		
		Label prompt = new Label("Enter your question");
		
		Label currentQues = new Label("Enter the title of the question you'd like to edit (must be exact title, including spaces)");
		Label newQuesTitle = new Label("Enter the new title");
		Label newQuesBody = new Label("Enter the new body");
		
		TextField quesInputTitle = new TextField(); 
		TextField NewquesInputTitle = new TextField(); 
		TextField NewquesInputBody = new TextField(); 
		
		quesInputTitle.setTranslateY(-50.0);
		NewquesInputBody.setTranslateY(50.0);
		
		currentQues.setTranslateY(-70.0);
		newQuesTitle.setTranslateY(-20.0);
		newQuesBody.setTranslateY(30.0);
		
		StackPane layout = new StackPane();
		
		// When edit question button is clicked, edit the question list with the new title and edit question in array list
		submitQuesition.setOnAction(new EventHandler<ActionEvent>() {		
			@Override
			public void handle(ActionEvent event) {
				if (quesInputTitle.getText().isBlank() || NewquesInputTitle.getText().isBlank() || NewquesInputBody.getText().isEmpty()) {
					error.setText("Error, one of the question fields is blank");
				}
				else if (!stu.checkValidQuestionTitle(quesInputTitle.getText())){
					error.setText("Error, no question found");
				}
				else if (!stu.checkEditPermission(quesInputTitle.getText(), "Mooey001")) {
					error.setText("You do not have permission to edit this question");
				}
				else {
					stu.EditQuestion(quesInputTitle.getText(), NewquesInputTitle.getText(), NewquesInputBody.getText(), qList, "Mooey001");
					superStage.close();
				}
			}
		});
		
		// Close window
		cancel.setOnAction(e -> {
			error.setText(null);
			superStage.close();
		});
		
		layout.getChildren().addAll(submitQuesition, cancel, quesInputTitle, NewquesInputTitle, NewquesInputBody, prompt, currentQues, newQuesTitle, newQuesBody);
		
		StackPane.setAlignment(submitQuesition, Pos.BOTTOM_LEFT);
		StackPane.setAlignment(cancel, Pos.BOTTOM_RIGHT);
		
		StackPane.setAlignment(prompt, Pos.TOP_CENTER);
		
		Scene scene = new Scene(layout, 600, 300);
		
		superStage.setScene(scene);
		superStage.show();
		
		return qList;
	}
}