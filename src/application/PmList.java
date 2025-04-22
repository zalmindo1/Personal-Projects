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
 * <p> Title: Private Messages List <p>
 * 
 * <p> Description: This is a JavaFX scene that is opened when the "view pms" button is clicked. Contains a list of all pms
   
   <p> Copyright: Zachary Almindo © 2025<p>
   
   @author Zachary Almindo
   @version 1.0
 */
public class PmList {
	/**
	 * Displaying the JavaFX scene
	 * @param superStage
	 * @throws IOException
	 */
	public static void show(Stage superStage) throws IOException {
		// Sets title for window
		superStage.setTitle("Private Messages List");
		
		// File variable is set to pm.csv
		File file = new File("pm.csv");
		
		// List view contains all of the private messages
		ListView<String> pms = new ListView<>();
		
		Button close = new Button ("Close");
		VBox pmViewer = new VBox();
		Label r = new Label();
		
		// Closes the window on button click
		close.setOnAction(e -> {
			superStage.close();
		});
		
		Pms pm = new Pms();
		
		// Adds private messages to pms ListView
		pm.showPmsStaff(pms);
		
		// Show who wrote the pm when list view element is clicked
		pms.setOnMouseClicked(e -> {
			try {
				Scanner scn1 = new Scanner(file);
				pmViewer.getChildren().clear();
				while (scn1.hasNextLine()) {
					String[] msgs = scn1.nextLine().split(",");
					if (msgs[2].equals(pms.getSelectionModel().getSelectedItem())) {
						r.setText(msgs[0] + " says " + '"' + msgs[2] + '"' + " to " + msgs[1]);
						pmViewer.getChildren().add(r);
					}
			  }
			}
				catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
		});
		
		
		
		HBox buttonBox = new HBox(4, close);
		
		BorderPane bdrLayout = new BorderPane();
		bdrLayout.setCenter(pmViewer);
		bdrLayout.setLeft(pms);
		bdrLayout.setBottom(buttonBox);
		
		Scene scene = new Scene(bdrLayout, 600, 400);
		
		superStage.setScene(scene);
		superStage.show();
	}
}
