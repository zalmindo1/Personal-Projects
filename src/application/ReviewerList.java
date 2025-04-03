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

public class ReviewerList {
	public static void show(Stage superStage, String user) throws IOException {
		superStage.setTitle("Review List");
		
		File file = new File("reviews.csv");
		
		Scanner scn = new Scanner(file);
		
		ListView<String> reviews = new ListView<>();
		Button edit = new Button("Edit a Review");
		Button delete = new Button("Delete a Review");
		Button close = new Button ("Close");
		VBox rViewer = new VBox();
		Label r = new Label();
		
		close.setOnAction(e -> {
			superStage.close();
		});
		
		while (scn.hasNextLine()) {
			String[] revs = scn.nextLine().split(",");
			if (revs[0].equals(user)) {
				reviews.getItems().add(revs[1]);
			}
		}
		
		
		reviews.setOnMouseClicked(e -> {
			try {
				Scanner scn1 = new Scanner(file);
				rViewer.getChildren().clear();
				while (scn1.hasNextLine()) {
					String[] revs = scn1.nextLine().split(",");
					if (revs[1].equals(reviews.getSelectionModel().getSelectedItem()) && revs[3].equals("ques")) {
						r.setText(revs[0] + " Reviewed question " + revs[2] + "\n" + revs[1]);
						rViewer.getChildren().add(r);
					}
					else if (revs[1].equals(reviews.getSelectionModel().getSelectedItem()) && revs[3].equals("ans")) {
						r.setText(revs[0] + " Reviewed answer to question " + revs[2] + "\n" + revs[1]);
						rViewer.getChildren().add(r);
				}
			  }
			}
				catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
		});
		
		edit.setOnAction(e -> {
			try {
				EditReview.show(new Stage(), reviews);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		
		delete.setOnAction(e -> {
			try {
				Scanner sc1 = new Scanner(new File("reviews.csv"));
				
				BufferedWriter writer1 = new BufferedWriter(new FileWriter("temp.csv"));
				
				while (sc1.hasNextLine()) {
					String[] revs = sc1.nextLine().split(",");
					if (revs[1].equals(reviews.getSelectionModel().getSelectedItem())) {
						continue;
					}
					else {
						writer1.write(revs[0] + "," + revs[1] + "," + revs[2] + "," + revs[3] + "\n");
					}
				}
				writer1.close();
				sc1.close();
				
				Scanner sc2 = new Scanner(new File("temp.csv"));
				
				BufferedWriter writer2 = new BufferedWriter(new FileWriter("reviews.csv"));
				
				while (sc2.hasNextLine()) {
					String[] revs = sc2.nextLine().split(",");
					writer2.write(revs[0] + "," + revs[1] + "," + revs[2] + "," + revs[3] + "\n");
				}
				writer2.close();
				sc2.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		
		HBox buttonBox = new HBox(4, edit, delete, close);
		
		BorderPane bdrLayout = new BorderPane();
		bdrLayout.setCenter(rViewer);
		bdrLayout.setLeft(reviews);
		bdrLayout.setBottom(buttonBox);
		
		Scene scene = new Scene(bdrLayout, 600, 400);
		
		superStage.setScene(scene);
		superStage.show();
	}
}
