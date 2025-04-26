package application;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
/**
 * JavaFX scene that handles submitting reviews of questions and answers
 */
public class ReviewerController {
	/**
 	* Loads a review based on review ID.
 	*/
	public static String[] loadReview(int ID) {
		try {
			BufferedReader reviewReader = new BufferedReader(new FileReader("reviews.csv"));
			String line = reviewReader.readLine();
			String[] comp;
			while(line != null) {
				comp = line.split(",");
				int currentID = Integer.parseInt(comp[5]);
				if(ID == currentID)
					return comp;
				line = reviewReader.readLine();
			}
		} catch (IOException i) {
			i.printStackTrace();
		}
		String[] result = new String[2];
		result[0] = "NULL";
		result[1] = "NULL";
		return result;
	}

	/**
 	* Stores a grade based on a reviewer ID, score, and the user name.
  	* @return the result of the funtion, true if successful.
 	*/
	public static boolean storeGrade(int revID, int score, String name) {
		if(score <= 0 || score > 5) {
			return false;
		}
		String[] review = loadReview(revID);
		if(revID > 1000 || review == null || revID <= 0) {
			return false;
		}
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("reviewGrades.csv", true));
			writer.append(revID + "," + score + "," + name + "\n");
			writer.close();
			return true;
		} catch (IOException i) {
			i.printStackTrace();
		}
		return false;
	}

	public static String[] computeReviewScore(int revID) {
		int total = 0;
		int number = 0;
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("reviewGrades.csv"));
			String line = reader.readLine();
			while(line != null) {
				String[] comp = line.split(",");
				if(Integer.parseInt(comp[0]) == revID) {
					total += Integer.parseInt(comp[1]);
					number++;
				}
				line = reader.readLine();
			}
			double avg = ((double)total /(number*5));
			String result  = revID + "," + avg;
			return result.split(",");
		} catch (IOException i) {
			i.printStackTrace();
		}
		return null;
	}

	/**
	 * Returns the ID of the review.
	 * @param name
	 * @return
	 */
	public static int getRevID(String name) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("reviewers.csv"));
			String line = reader.readLine();
			String[] comp;
			while(line != null) {
				comp = line.split(",");
				if(comp[0] == name) {
					return Integer.parseInt(comp[5]);
				}
				line = reader.readLine();
			}
		} catch (IOException i) {
			i.printStackTrace();
		}
		return -1;
	}

	/**
	 * Returns the percentage of favorable ratings
	 * @param revID
	 * @return
	 */
	public static double computeFavorabilityPercentage(int revID) {
		int total = 0;
		int number = 0;
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("reviewGrades.csv"));
			String line = reader.readLine();
			while(line != null) {
				String[] comp = line.split(",");
				if(Integer.parseInt(comp[0]) == revID) {
					if((Integer.parseInt(comp[1])) > 3) {
						total++;
					}
					number++;
				}
				line = reader.readLine();
			}
			if(total == 0)
				return -1;
			double avg = ((double)total /number);
			String result  = revID + "," + avg;
			return ((double) total)/(double) number;
		} catch (IOException i) {
			i.printStackTrace();
		}
		return -1;
	}
	
	public static void show(Stage superStage, ListView<String> List, Label error, String type) {
		
		superStage.setTitle("Review Prompter");
		
		Button submitReview = new Button("Submit Review");
		Button cancel = new Button("Cancel");
		
		Label quesTitle = new Label("Enter your review");
		
		TextField quesInputTitle = new TextField(); 
		
		quesTitle.setTranslateY(-20.0);
		
		StackPane layout = new StackPane();
		
		// Submits review when clicked
		submitReview.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if (quesInputTitle.getText().isBlank()) {
					error.setText("Error: Review body is blank");
				}
				else {
					try {
						BufferedWriter writer = new BufferedWriter(new FileWriter("reviews.csv", true));
						writer.write("Mooey001,Zalmindo1," + quesInputTitle.getText() + "," + List.getSelectionModel().getSelectedItem() + "," + type + "\n");
						writer.close();
						superStage.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			}
		});
			
		// Close window
		cancel.setOnAction(e -> {
			error.setText(null);
			superStage.close();
		});
		
		layout.getChildren().addAll(submitReview, cancel, quesInputTitle, quesTitle);
		
		StackPane.setAlignment(submitReview, Pos.BOTTOM_LEFT);
		StackPane.setAlignment(cancel, Pos.BOTTOM_RIGHT);
		
		
		Scene scene = new Scene(layout, 400, 300);
		
		superStage.setScene(scene);
		superStage.show();
	}
	

	    public static List<String> loadAllReviews() {
	        List<String> reviews = new ArrayList<>();
	        try (Scanner scanner = new Scanner(new java.io.File("reviews.csv"))) {
	            while (scanner.hasNextLine()) {
	                reviews.add(scanner.nextLine());
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return reviews;
	    }
}