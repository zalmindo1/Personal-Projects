package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class Admin {
	public boolean ShowAllUsersWindow() {
		boolean worked = false;
		try {
			AllUsersList.show(new Stage());
			worked = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return worked;
	}
	
	public boolean PopulateAllUsers(ListView<String> users) {
		boolean worked = false;
		boolean r_file_pass = false;
		boolean s_file_pass = false;
		boolean i_file_pass = false;
		File rfile = new File("reviewers.csv");
		File sfile = new File("staff.csv");
		File ifile = new File("instructors.csv");
		users.getItems().add("Mooey");
		users.getItems().add("Zalmindo1");
		try {
			Scanner scn = new Scanner(rfile);
			while (scn.hasNextLine()) {
				String[] revs = scn.nextLine().split(",");
				if (!revs[0].equals("Reviewer")) {
					users.getItems().add(revs[0]);
					r_file_pass = true;
				}
			}
			scn.close();
			Scanner scn1 = new Scanner(sfile);
			while (scn1.hasNextLine()) {
				String[] stf = scn1.nextLine().split(",");
				if (!stf[0].equals("Staff")) {
					users.getItems().add(stf[0]);
					s_file_pass = true;
				}
			}
			scn1.close();
			Scanner scn2 = new Scanner(ifile);
			while (scn2.hasNextLine()) {
				String[] insts = scn2.nextLine().split(",");
				users.getItems().add(insts[0]);
				i_file_pass = true;
			}
			scn2.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
		if (r_file_pass && s_file_pass && i_file_pass) {
			worked = true;
		}
		return worked;
	}
	
	public boolean CheckRoleOfUser(Label r, String user) {
		boolean worked = false;
		boolean r_file_pass = false;
		boolean s_file_pass = false;
		boolean i_file_pass = false;
		boolean admin_pass = false;
		boolean student_pass = false;
		if (user.equals("Mooey")) {
			r.setText("This user is an admin");
			admin_pass = true;
		}
		if (user.equals("Zalmindo1")) {
			r.setText("This user is a student");;
			student_pass = true;
		}
		File rfile = new File("reviewers.csv");
		File sfile = new File("staff.csv");
		File ifile = new File("instructors.csv");
		try {
			Scanner scn = new Scanner(rfile);
			while (scn.hasNextLine()) {
				String[] revs = scn.nextLine().split(",");
				if (revs[0].equals(user)) {
					r.setText("This user is a reviewer");
					r_file_pass = true;
				}
			}
			scn.close();
			Scanner scn1 = new Scanner(sfile);
			while (scn1.hasNextLine()) {
				String[] stf = scn1.nextLine().split(",");
				if (stf[0].equals(user)) {
					r.setText("This user is a staff member");
					s_file_pass = true;
				}
			}
			scn1.close();
			Scanner scn2 = new Scanner(ifile);
			while (scn2.hasNextLine()) {
				String[] insts = scn2.nextLine().split(",");
				if (insts[0].equals(user)) {
					r.setText("This user is an instructor");
					i_file_pass = true;
				}
			}
			scn2.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
		if (r_file_pass || s_file_pass || i_file_pass || admin_pass || student_pass) {
			worked = true;
		}
		return worked;
	}
	
	public boolean AddInstructor(String username) {
		boolean worked = false;
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("instructors.csv", true));
			writer.write(username + ",\n");
			writer.close();
			worked = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return worked;
	}
	
	public boolean AddStaff(String username) {
		boolean worked = false;
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("staff.csv", true));
			writer.write(username + ",\n");
			writer.close();
			worked = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return worked;
	}
	
	public boolean AddReviewer(String username) {
		boolean worked = false;
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("reviewers.csv", true));
			writer.write(username + ",\n");
			writer.close();
			worked = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return worked;
	}
}
