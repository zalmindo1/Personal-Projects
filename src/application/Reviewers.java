package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.scene.control.ListView;
/**
 * <p> Title: Reviews Handler <p>
 * 
 * <p> Description: This java class handles actions regarding reviews
   
   <p> Copyright: Zachary Almindo © 2025<p>
   
   @author Zachary Almindo
   @version 1.0
 */
public class Reviewers {
	/**
	 * Displays all reviews contained in reviews.csv onto the listview passed in
	 * @param revs
	 * @return true/false depending on if the method performed correctly
	 */
	public boolean ShowReviewsStaff(ListView<String> revs) {
		boolean worked = false;
		File file = new File("reviews.csv");
		try {
			Scanner scn = new Scanner(file);
			scn.nextLine();
			while (scn.hasNextLine()) {
				String[] rev = scn.nextLine().split(",");
				revs.getItems().add(rev[2]);
			}
			scn.close();
			worked = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return worked;
	}
}
