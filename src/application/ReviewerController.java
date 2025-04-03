package application;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ReviewerController {
	
	
	public static void show(Stage superStage, ListView<String> List, Label error, String type) {
		Random ran = new Random();
		
		superStage.setTitle("Review Prompter");
		
		Button submitReview = new Button("Submit Review");
		Button cancel = new Button("Cancel");
		
		Label quesTitle = new Label("Enter your review");
		
		TextField quesInputTitle = new TextField(); 
		
		quesTitle.setTranslateY(-20.0);
		
		StackPane layout = new StackPane();
		
		// When submit question is clicked, add question to both question list and array list
		submitReview.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if (quesInputTitle.getText().isBlank()) {
					error.setText("Error: Review body is blank");
				}
				else {
					try {
						BufferedWriter writer = new BufferedWriter(new FileWriter("reviews.csv", true));
						writer.write("Mooey001," + quesInputTitle.getText() + "," + List.getSelectionModel().getSelectedItem() + "," + type + "\n");
						writer.close();
						superStage.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			}
		});
		
		
		// Close window
		cancel.setOnAction(e -> {
			error.setText(null);
			superStage.close();
		});
		
		layout.getChildren().addAll(submitReview, cancel, quesInputTitle, quesTitle);
		
		StackPane.setAlignment(submitReview, Pos.BOTTOM_LEFT);
		StackPane.setAlignment(cancel, Pos.BOTTOM_RIGHT);
		
		
		Scene scene = new Scene(layout, 400, 300);
		
		superStage.setScene(scene);
		superStage.show();
	}
}