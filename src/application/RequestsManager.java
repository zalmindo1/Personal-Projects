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
 * <p> Title: Requests Manager <p>
 * 
 * <p> Description: This is a JavaFX scene that allows for instructors to create a admin work request ticket
   
   <p> Copyright: Zachary Almindo © 2025<p>
   
   @author Zachary Almindo
   @version 1.0
 */
public class RequestsManager {
	/**
	 * Displaying the JavaFX scene
	 * @param superStage
	 * @param user
	 * @throws FileNotFoundException
	 */
	public static void show(Stage superStage, String user) throws FileNotFoundException {
		// Sets title for window
		superStage.setTitle("Admin Request");
		
		// Buttons for sending a pm and canceling
		Button submitReq = new Button("Request Admin Work");
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
			req.WriteRequest(user, Body.getText());
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