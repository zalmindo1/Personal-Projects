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
/**
 * JavaFX scene that handles submitting reviews of questions and answers
 */
public class SetTrustedReviewers {
	
	
	public static void show(Stage superStage) {
		Random ran = new Random();
		
		superStage.setTitle("Set Trusted Reviewer");
		
		Button submit = new Button("Submit");
		Button cancel = new Button("Cancel");
		
		Label quesTitle = new Label("Name Trusted Reviewer");
		Label number = new Label("Type a number");
		
		TextField weight = new TextField();
		TextField reviewer = new TextField(); 
		
		quesTitle.setTranslateY(-20.0);
		number.setTranslateY(80);
		weight.setTranslateY(100);
		
		StackPane layout = new StackPane();
		
		// Adds trusted reviewer
		submit.setOnAction(e -> {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter("trusted.csv", true));
				writer.write(reviewer.getText() + "," + weight.getText() + "\n");
				superStage.close();
				writer.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		});
		
			
		// Close window
		cancel.setOnAction(e -> {
			superStage.close();
		});
		
		layout.getChildren().addAll(submit, cancel, reviewer, quesTitle, weight, number);
		
		StackPane.setAlignment(submit, Pos.BOTTOM_LEFT);
		StackPane.setAlignment(cancel, Pos.BOTTOM_RIGHT);
		
		
		Scene scene = new Scene(layout, 400, 300);
		
		superStage.setScene(scene);
		superStage.show();
	}
	
}