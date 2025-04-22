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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
/**
 * <p> Title: Private Messages Window <p>
 * 
 * <p> Description: This is a JavaFX scene that is opened when the "pms" button is clicked. Prompts the user for a username and message body to send a private message
   
   <p> Copyright: Zachary Almindo © 2025<p>
   
   @author Zachary Almindo
   @version 1.0
 */
public class PmManager {
	/**
	 * Displaying the JavaFX scene
	 * @param superStage
	 * @param user
	 * @throws FileNotFoundException
	 */
	public static void show(Stage superStage, String user) throws FileNotFoundException {
		// Sets title for window
		superStage.setTitle("Private Message");
		
		// Buttons for sending a pm and canceling
		Button submitPM = new Button("Send a PM");
		Button cancel = new Button("Cancel");
		
		Label prompt = new Label("Enter your private message");
		
		Label p = new Label("Enter the person you want to DM");
		Label f = new Label("Enter your message body");
		
		// Text boxes to get user input for username and body of pm
		TextField Person = new TextField();
		TextField Body = new TextField();
		
		Body.setTranslateY(-50.0);
		
		p.setTranslateY(-70);
		f.setTranslateY(-20);
		
		StackPane layout = new StackPane();
		
		// Submits a private message when clicked
		submitPM.setOnAction(e -> {
			Pms pms = new Pms();
			pms.WritePM(Body.getText(), Person.getText());
			superStage.close();
		});
		
		// Close window
		cancel.setOnAction(e -> {
			superStage.close();
		});
		
		layout.getChildren().addAll(submitPM, cancel, Person, Body, prompt, p, f);
		
		StackPane.setAlignment(submitPM, Pos.BOTTOM_LEFT);
		StackPane.setAlignment(cancel, Pos.BOTTOM_RIGHT);
		
		StackPane.setAlignment(prompt, Pos.TOP_CENTER);
		
		Scene scene = new Scene(layout, 600, 300);
		
		superStage.setScene(scene);
		superStage.show();
		
	}
}