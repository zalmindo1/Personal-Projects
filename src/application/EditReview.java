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

public class EditReview {
	public static void show(Stage superStage, ListView<String> rList) throws FileNotFoundException {
		superStage.setTitle("Edit Review");
		
		Button submitReview = new Button("Edit Review");
		Button cancel = new Button("Cancel");
		
		Label prompt = new Label("Enter your new review");
		
		TextField NewReview = new TextField(); 
		
		
		StackPane layout = new StackPane();
		
		File file = new File("reviews.csv");
		Scanner scn = new Scanner(file);
		
		submitReview.setOnAction(e -> {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter("temp.csv"));
				while (scn.hasNextLine()) {
					String[] revs = scn.nextLine().split(",");
					if (revs[1].equals(rList.getSelectionModel().getSelectedItem())) {
						writer.write(revs[0] + "," + NewReview.getText() + "," + revs[2] + "," + revs[3] + "\n");
					}
					else {
						writer.write(revs[0] + "," + revs[1] + "," + revs[2] + "," + revs[3] + "\n");
					}
				}
				writer.close();
				scn.close();
				
				BufferedWriter writerNew = new BufferedWriter(new FileWriter("reviews.csv"));
				File nFile = new File("temp.csv");
				
				Scanner scn1 = new Scanner(nFile);
				
				while(scn1.hasNextLine()) {
					String[] revs1 = scn1.nextLine().split(",");
					writerNew.write(revs1[0] + "," + revs1[1] + "," + revs1[2] + "," + revs1[3] + "\n");
				}
				writerNew.close();
				scn1.close();
				
				superStage.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		// Close window
		cancel.setOnAction(e -> {
			superStage.close();
		});
		
		layout.getChildren().addAll(submitReview, cancel, NewReview, prompt);
		
		StackPane.setAlignment(submitReview, Pos.BOTTOM_LEFT);
		StackPane.setAlignment(cancel, Pos.BOTTOM_RIGHT);
		
		StackPane.setAlignment(prompt, Pos.TOP_CENTER);
		
		Scene scene = new Scene(layout, 600, 300);
		
		superStage.setScene(scene);
		superStage.show();
		
	}
}