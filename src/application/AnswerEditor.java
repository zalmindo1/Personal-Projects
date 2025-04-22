package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * JavaFX scene that handles editing user answers
 */
public class AnswerEditor {

	public static void show(Stage s, Answers answers, ListView<String> answerList, VBox answerB, Label error) {
		
		VBox layout = new VBox(4);
	
		Label ansPrompt = new Label("Enter your new answer");
		TextArea ansInput = new TextArea();
		Button confirmAns = new Button("Change Answer");
		
		// Edits answer text to match user's input
		confirmAns.setOnAction(e -> {
			try {
				answerB.getChildren().clear();
				String selected = answerList.getSelectionModel().getSelectedItem();
				int originalIdx = answerList.getSelectionModel().getSelectedIndex();
				Answer selectedAns = answers.getAnswer(selected);
				selectedAns.SetAnswer(ansInput.getText());
				answerList.getItems().remove(originalIdx);
				answerList.getItems().add(originalIdx, selectedAns.getAnswerBody());
				answerList.refresh();
				answerB.getChildren().add(new Label("Current Answer:\n" + answers.getAnsInfo(ansInput.getText())));
			} catch (NullPointerException n) {
				error.setText("Answer does not exist!");
			}
			s.close();
		
		});
		
		layout.getChildren().addAll(ansPrompt, ansInput, confirmAns);
		
		Scene winScene = new Scene(layout, 300, 200);
		s.setScene(winScene);
		s.setTitle("Answer Editor");
		s.show();
	}
	
}