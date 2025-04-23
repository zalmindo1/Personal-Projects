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
public class EditRequest {
	/**
	 * Displaying the JavaFX scene
	 * @param superStage
	 * @throws FileNotFoundException
	 */
	public static void show(Stage superStage, ListView<String> reqs) throws FileNotFoundException {
		// Sets title for window
		superStage.setTitle("Edit Admin Request");
		
		// Buttons for sending a pm and canceling
		Button submitReq = new Button("Edit");
		Button cancel = new Button("Cancel");
		
		Label prompt = new Label("Enter your request");
		
		Label f = new Label("Enter your message body");
		
		// Text boxes to get request description
		TextField Body = new TextField();
		
		f.setTranslateY(-20);
		
		StackPane layout = new StackPane();
		
		// When button is clicked, write request
		submitReq.setOnAction(e -> {
			Requests req = new Requests();
			try {
				req.EditRequest(Body.getText(), reqs);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			superStage.close();
		});
		
		// Close window
		cancel.setOnAction(e -> {
			superStage.close();
		});
		
		layout.getChildren().addAll(submitReq, cancel, Body, prompt, f);
		
		StackPane.setAlignment(submitReq, Pos.BOTTOM_LEFT);
		StackPane.setAlignment(cancel, Pos.BOTTOM_RIGHT);
		
		StackPane.setAlignment(prompt, Pos.TOP_CENTER);
		
		Scene scene = new Scene(layout, 600, 300);
		
		superStage.setScene(scene);
		superStage.show();
		
	}
}