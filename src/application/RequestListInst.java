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
 * <p> Title: Instructor Request List <p>
 * 
 * <p> Description: This is a JavaFX scene that displays all requests from requests.csv into a listview and allows for the editing of requests
   
   <p> Copyright: Zachary Almindo © 2025<p>
   
   @author Zachary Almindo
   @version 1.0
 */
public class RequestListInst {
	/**
	 * Displaying the JavaFX scene
	 * @param superStage
	 * @throws IOException
	 */
	public static void show(Stage superStage) throws IOException {
		// Sets title for window
		superStage.setTitle("Open Requests List");
		
		// File variable is set to pm.csv
		File file = new File("requests.csv");
		
		// List view contains all of the private messages
		ListView<String> reqs = new ListView<>();
		
		Button close = new Button ("Close");
		Button editReq = new Button ("Edit Request");
		
		VBox pmViewer = new VBox();
		Label r = new Label();
		
		// Closes the window on button click
		close.setOnAction(e -> {
			superStage.close();
		});
		
		Requests req = new Requests();
		
		// Adds open requests to reqs ListView
		req.showOpenRequests(reqs);
		
		// Show the description of the request and who wrote it
		reqs.setOnMouseClicked(e -> {
			try {
				Scanner scn1 = new Scanner(file);
				pmViewer.getChildren().clear();
				while (scn1.hasNextLine()) {
					String[] re = scn1.nextLine().split(",");
					if (re[1].equals(reqs.getSelectionModel().getSelectedItem())) {
						r.setText(re[0] + " requests: " + re[1]);
						pmViewer.getChildren().add(r);
					}
			  }
			}
				catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
		});
		
		editReq.setOnAction(e -> {
			req.ShowEditRequestWindow(reqs);
		});
		
		HBox buttonBox = new HBox(4, close, editReq);
		
		BorderPane bdrLayout = new BorderPane();
		bdrLayout.setCenter(pmViewer);
		bdrLayout.setLeft(reqs);
		bdrLayout.setBottom(buttonBox);
		
		Scene scene = new Scene(bdrLayout, 600, 400);
		
		superStage.setScene(scene);
		superStage.show();
	}
}
