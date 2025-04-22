package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.scene.control.ListView;
/**
 * <p> Title: Private Messages Handler <p>
 * 
 * <p> Description: This java class handles showing private messages and sending private messages
   
   <p> Copyright: Zachary Almindo © 2025<p>
   
   @author Zachary Almindo
   @version 1.0
 */
public class Pms {
	/**
	 * This method displays all private messages from the pm.csv file into the passed in listview
	 * @param pms
	 * @return true/false depending on if the method performed correctly
	 */
	public boolean showPmsStaff(ListView<String> pms) {
		File file = new File("pm.csv");
		boolean worked = false;
		try {
			Scanner scn = new Scanner(file);
			while (scn.hasNextLine()) {
				String[] pm = scn.nextLine().split(",");
				pms.getItems().add(pm[2]);
			}
			scn.close();
			worked = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return worked;
	}
	/**
	 * This method writes a private message into the pm.csv file, using the passed in user and body as who the message is going to and what it contains
	 * @param user
	 * @param body
	 * @return true/false depending on if the method performed correctly
	 */
	public boolean WritePM(String user, String body) {
		boolean worked = false;
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("pm.csv", true));
			writer.write("Staff123" + "," + user + "," + body + "\n");
			writer.close();
			worked = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return worked;
	}
}
