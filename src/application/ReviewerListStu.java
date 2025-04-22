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
 * JavaFX scene that handles students being able to see their reviews
 */
public class ReviewerListStu {
	public static void show(Stage superStage, String user) throws IOException {
		superStage.setTitle("Review List");
		
		File file = new File("reviews.csv");
		
		Scanner scn = new Scanner(file);
		
		ListView<String> reviews = new ListView<>();
		Button close = new Button ("Close");
		VBox rViewer = new VBox();
		Label r = new Label();
		
		close.setOnAction(e -> {
			superStage.close();
		});
		
		while (scn.hasNextLine()) {
			String[] revs = scn.nextLine().split(",");
			if (revs[1].equals(user)) {
				reviews.getItems().add(revs[2]);
			}
		}
		
		// Shows student reviews
		reviews.setOnMouseClicked(e -> {
			try {
				Scanner scn1 = new Scanner(file);
				rViewer.getChildren().clear();
				while (scn1.hasNextLine()) {
					String[] revs = scn1.nextLine().split(",");
					if (revs[2].equals(reviews.getSelectionModel().getSelectedItem()) && revs[4].equals("ques")) {
						r.setText(revs[0] + " Reviewed your question " + revs[3] + "\n" + revs[2]);
						rViewer.getChildren().add(r);
					}
					else if (revs[2].equals(reviews.getSelectionModel().getSelectedItem()) && revs[4].equals("ans")) {
						r.setText(revs[0] + " Reviewed your answer " + revs[3] + "\n" + revs[2]);
						rViewer.getChildren().add(r);
				}
			  }
			}
				catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
		});
		
		
		HBox buttonBox = new HBox(4, close);
		
		BorderPane bdrLayout = new BorderPane();
		bdrLayout.setCenter(rViewer);
		bdrLayout.setLeft(reviews);
		bdrLayout.setBottom(buttonBox);
		
		Scene scene = new Scene(bdrLayout, 600, 400);
		
		superStage.setScene(scene);
		superStage.show();
	}
}
