package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
/**
 * <p> Title: Reviewer Profile Window <p>
 * 
 * <p> Description: This is a JavaFX scene that displays the reviewer profile which includes review, experience, and feedback
  
   <p> Copyright: Syed Ali © 2025<p>
   
   @author Syed Ali
   @version 1.0
 */
public class ReviewerProfileWindow {
	/**
	 * Displaying the JavaFX scene
	 * @param superStage
	 * @param user
	 * @throws IOException
	 */
    public static void show(Stage superStage, String username) throws IOException {
        superStage.setTitle("Reviewer Profile - " + username);

        File reviewsFile = new File("reviews.csv");
        File reviewersFile = new File("reviewers.csv");
        File feedbackFile = new File("feedback.csv");

        // UI Components
        ListView<String> reviews = new ListView<>();
        ListView<String> feedbackList = new ListView<>();
        VBox reviewDetails = new VBox();
        Label experienceLabel = new Label("Experience: (not found)");
        Button close = new Button("Close");
		Label r = new Label();

        // Load experience from reviewers.csv
        try (Scanner sc = new Scanner(reviewersFile)) {
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(",", 2);
                if (data.length == 2 && data[0].equals(username)) {
                    experienceLabel.setText("Experience: " + data[1]);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Load reviews from reviews.csv
        try {
			Scanner scn = new Scanner(reviewsFile);
			scn.nextLine();
			while (scn.hasNextLine()) {
				String[] rev = scn.nextLine().split(",");
				reviews.getItems().add(rev[2]);
			}
			scn.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

        // Handle review click: show details
        reviews.setOnMouseClicked(e -> {
        	try {
				Scanner scn1 = new Scanner(reviewsFile);
				reviewDetails.getChildren().clear();
				while (scn1.hasNextLine()) {
					String[] revs = scn1.nextLine().split(",");
					if (revs[2].equals(reviews.getSelectionModel().getSelectedItem()) && revs[4].equals("ques")) {
						r.setText(revs[0] + " Reviewed question " + revs[3] + "\n" + revs[2]);
						reviewDetails.getChildren().add(r);
					}
					else if (revs[2].equals(reviews.getSelectionModel().getSelectedItem()) && revs[4].equals("ans")) {
						r.setText(revs[0] + " Reviewed answer to question " + revs[3] + "\n" + revs[2]);
						reviewDetails.getChildren().add(r);
				}
			  }
			}
				catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
        });

        // Load feedback from Feedback.csv         
        try {
			Scanner scn = new Scanner(feedbackFile);
			scn.nextLine();
			while (scn.hasNextLine()) {
				String[] rev = scn.nextLine().split(",");
				feedbackList.getItems().add(rev[1]);
			}
			scn.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

        // Close button
        close.setOnAction(e -> superStage.close());

        // Layout
        VBox leftPane = new VBox(new Label("Reviews by " + username), reviews);
        VBox rightPane = new VBox(new Label("Feedback from Students"), feedbackList);
        VBox centerPane = new VBox(experienceLabel, new Separator(), reviewDetails);
        HBox bottomPane = new HBox(close);

        leftPane.setSpacing(5);
        centerPane.setSpacing(10);
        rightPane.setSpacing(5);
        bottomPane.setSpacing(10);

        BorderPane layout = new BorderPane();
        layout.setLeft(leftPane);
        layout.setCenter(centerPane);
        layout.setRight(rightPane);
        layout.setBottom(bottomPane);

        Scene scene = new Scene(layout, 800, 400);
        superStage.setScene(scene);
        superStage.show();
    }
}
