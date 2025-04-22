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
 * JavaFX scene that handles showing user reviews from trusted users
 */
public class TrusterReviewerList {
	public static void show(Stage superStage) throws IOException {
		superStage.setTitle("Trusted Reviewers List");
		
		File file = new File("trusted.csv");
		File nfile = new File("reviews.csv");
		
		Scanner scn = new Scanner(file);
		
		ListView<String> reviews = new ListView<>();
		Button trusted = new Button("Who is a trusted reviewer");
		Button close = new Button ("Close");
		VBox rViewer = new VBox();
		Label r = new Label();
		
		close.setOnAction(e -> {
			superStage.close();
		});
		
		String[] trust = new String[0];
		
		
		
		Scanner Nscn = new Scanner(nfile);
		
		// Puts trusted reviewers and weightage in csv file
		while (scn.hasNextLine()) {
			trust = scn.nextLine().split(",");
			while (Nscn.hasNextLine()) {
				String[] trusts = Nscn.nextLine().split(",");
				if (trusts[0].equals(trust[0])) {
					reviews.getItems().add(trusts[2]);
					break;
				}
			}
		}
		
		// Shows reviews when list view element is clicked
		reviews.setOnMouseClicked(e -> {
			try {
				Scanner scn1 = new Scanner(nfile);
				rViewer.getChildren().clear();
				while (scn1.hasNextLine()) {
					String[] revs = scn1.nextLine().split(",");
					if (revs[2].equals(reviews.getSelectionModel().getSelectedItem()) && revs[4].equals("ques")) {
						r.setText(revs[0] + " Reviewed question " + revs[3] + "\n" + revs[2]);
						rViewer.getChildren().add(r);
					}
					else if (revs[2].equals(reviews.getSelectionModel().getSelectedItem()) && revs[4].equals("ans")) {
						r.setText(revs[0] + " Reviewed answer to question " + revs[3] + "\n" + revs[2]);
						rViewer.getChildren().add(r);
				}
			  }
			}
				catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
		});
		
		// Opens up java fx screen the handles setting trusted reviewers
		trusted.setOnMouseClicked(e -> {
			SetTrustedReviewers.show(new Stage());
			superStage.close();
		});
		
		HBox buttonBox = new HBox(4, trusted, close);
		
		BorderPane bdrLayout = new BorderPane();
		bdrLayout.setCenter(rViewer);
		bdrLayout.setLeft(reviews);
		bdrLayout.setBottom(buttonBox);
		
		Scene scene = new Scene(bdrLayout, 600, 400);
		
		superStage.setScene(scene);
		superStage.show();
	}
}
