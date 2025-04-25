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
 * <p> Title: All Users Window <p>
 * 
 * <p> Description: This is a JavaFX scene that displays all usernames and the role's they play within the application
   
   <p> Copyright: Zachary Almindo © 2025<p>
   
   @author Zachary Almindo
   @version 1.0
 */
public class AllUsersList {
	/**
	 * Displaying the JavaFX scene
	 * @param superStage
	 * @throws IOException
	 */
	public static void show(Stage superStage) throws IOException {
		// Sets title for window
		superStage.setTitle("Users List");
		
		// List view contains all of the private messages
		ListView<String> Users = new ListView<>();
		
		Button close = new Button ("Close");
		VBox pmViewer = new VBox();
		Label r = new Label();
		
		// Closes the window on button click
		close.setOnAction(e -> {
			superStage.close();
		});
		
		Admin an = new Admin();
		
		// Adds all users to Users listview
		an.PopulateAllUsers(Users);
		
		// Show the role of the user when the user's username is clicked
		Users.setOnMouseClicked(e -> {
			pmViewer.getChildren().clear();
			an.CheckRoleOfUser(r, Users.getSelectionModel().getSelectedItem());
			pmViewer.getChildren().add(r);
		});
		
		HBox buttonBox = new HBox(4, close);
		
		BorderPane bdrLayout = new BorderPane();
		bdrLayout.setCenter(pmViewer);
		bdrLayout.setLeft(Users);
		bdrLayout.setBottom(buttonBox);
		
		Scene scene = new Scene(bdrLayout, 600, 400);
		
		superStage.setScene(scene);
		superStage.show();
	}
}
