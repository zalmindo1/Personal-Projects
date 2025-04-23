package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.scene.control.ListView;
import javafx.stage.Stage;
/**
 * <p> Title: Requests Controller <p>
 * 
 * <p> Description: This java class handles all functions regarding managing, closing, opening, editing, and viewing admin requests
   
   <p> Copyright: Zachary Almindo © 2025<p>
   
   @author Zachary Almindo
   @version 1.0
 */
public class Requests {
	/**
	 * This method displays all open requests from the requests.csv file into the passed in listview
	 * @param reqs
	 * @return true/false depending on if the method worked properly
	 */
	public boolean showOpenRequests(ListView<String> reqs) {
		File file = new File("requests.csv");
		boolean worked = false;
		try {
			Scanner scn = new Scanner(file);
			while (scn.hasNextLine()) {
				String[] req = scn.nextLine().split(",");
				if (req[2].equals("open")) {
					reqs.getItems().add(req[1]);
				}
			}
			scn.close();
			worked = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return worked;
	}
	
	/**
	 * This method shows the request list window, showing all requests inside the requests.csv file
	 * @return true/false depending on if the method worked properly
	 */
	public boolean ShowRequestList() {
		boolean worked = false;
		try {
			RequestList.show(new Stage());
			worked = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return worked;
	}
	
	/**
	 * This method shows the instructor request list window, showing all requests inside the requests.csv file and allowing for editing of requests
	 * @return true/false depending on if the method worked properly
	 */
	public boolean ShowRequestListInst() {
		boolean worked = false;
		try {
			RequestListInst.show(new Stage());
			worked = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return worked;
	}
	
	/**
	 * This method shows the admin request list window, allowing for admins to perform admin specific functions on requests
	 * @return true/false depending on if the method worked properly
	 */
	public boolean ShowAdminRequestList() {
		boolean worked = false;
		try {
			AdminRequestList.show(new Stage());
			worked = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return worked;
	}
	
	/**
	 * This method shows the requests manager window that lets instructors write a request
	 * @return true/false depending on if the method worked properly
	 */
	public boolean ShowRequestsManager() {
		boolean worked = false;
		try {
			RequestsManager.show(new Stage(), "Instructor");
			worked = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return worked;
	}
	
	/**
	 * This method writes a admin request into the requests.csv file
	 * @param sender
	 * @param desc
	 * @return true/false depending on if method performed correctly
	 */
	public boolean WriteRequest (String sender, String desc) {
		boolean worked = false;
		try {
			BufferedWriter writer1 = new BufferedWriter(new FileWriter("requests.csv", true));
			writer1.write(sender + "," + desc + ",open" + "\n");
			writer1.close();
			worked = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return worked;
	}
	
	/**
	 * This method modifies the request with the close tag so that it removes the request from the requests listview
	 * @param reqs
	 * @return true/false depending on if method performed correctly
	 * @throws IOException
	 */
	public boolean CloseRequest(ListView<String> reqs) throws IOException {
		boolean worked = false;
		File file = new File("requests.csv");
		try {
			Scanner scn = new Scanner(file);
			BufferedWriter writer = new BufferedWriter(new FileWriter("temp.csv"));
			
			while (scn.hasNextLine()) {
				String[] req = scn.nextLine().split(",");
				if (req[1].equals(reqs.getSelectionModel().getSelectedItem())) {
					writer.write(req[0] + "," + req[1] + ",close\n");
				}
				else {
					writer.write(req[0] + "," + req[1] + "," + req[2] + "\n");
				}
			}
			writer.close();
			scn.close();
			
			BufferedWriter writerNew = new BufferedWriter(new FileWriter("requests.csv"));
			File nFile = new File("temp.csv");
			
			Scanner scn1 = new Scanner(nFile);
			
			while(scn1.hasNextLine()) {
				String[] req1 = scn1.nextLine().split(",");
				writerNew.write(req1[0] + "," + req1[1] + "," + req1[2] + "\n");
			}
			writerNew.close();
			scn1.close();
			
			reqs.refresh();
			
			worked = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return worked;
	}
	
	/**
	 * This method displays all closed requests from the requests.csv file into the passed in listview
	 * @param reqs
	 * @return true/false depending on if the method worked properly
	 */
	public boolean showCLosedRequests(ListView<String> reqs) {
		File file = new File("requests.csv");
		boolean worked = false;
		try {
			Scanner scn = new Scanner(file);
			while (scn.hasNextLine()) {
				String[] req = scn.nextLine().split(",");
				if (req[2].equals("close")) {
					reqs.getItems().add(req[1]);
				}
			}
			scn.close();
			worked = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return worked;
	}
	
	/**
	 * This method shows the closed request list window, showing all closed requests inside the requests.csv file
	 * @return true/false depending on if the method worked properly
	 */
	public boolean ShowClosedRequestList() {
		boolean worked = false;
		try {
			ClosedRequestList.show(new Stage());
			worked = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return worked;
	}
	
	/**
	 * This method shows the closed request list window for instructors, showing all closed requests inside the requests.csv file and allowing the reopening of requests
	 * @return true/false depending on if the method worked properly
	 */
	public boolean ShowClosedRequestListInst() {
		boolean worked = false;
		try {
			ClosedRequestListInst.show(new Stage());
			worked = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return worked;
	}
	
	/**
	 * This method modifies the request with the open tag
	 * @param reqs
	 * @return true/false depending on if method performed correctly
	 * @throws IOException
	 */
	public boolean OpenRequest(ListView<String> reqs) throws IOException {
		boolean worked = false;
		File file = new File("requests.csv");
		try {
			Scanner scn = new Scanner(file);
			BufferedWriter writer = new BufferedWriter(new FileWriter("temp.csv"));
			
			while (scn.hasNextLine()) {
				String[] req = scn.nextLine().split(",");
				if (req[1].equals(reqs.getSelectionModel().getSelectedItem())) {
					writer.write(req[0] + "," + req[1] + ",open\n");
				}
				else {
					writer.write(req[0] + "," + req[1] + "," + req[2] + "\n");
				}
			}
			writer.close();
			scn.close();
			
			BufferedWriter writerNew = new BufferedWriter(new FileWriter("requests.csv"));
			File nFile = new File("temp.csv");
			
			Scanner scn1 = new Scanner(nFile);
			
			while(scn1.hasNextLine()) {
				String[] req1 = scn1.nextLine().split(",");
				writerNew.write(req1[0] + "," + req1[1] + "," + req1[2] + "\n");
			}
			writerNew.close();
			scn1.close();
			
			reqs.refresh();
			
			worked = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return worked;
	}
	
	/**
	 * This method allows for the instructor to edit the request
	 * @param reqs
	 * @return true/false depending on if method performed correctly
	 * @throws IOException
	 */
	public boolean EditRequest(String desc, ListView<String> reqs) throws IOException {
		boolean worked = false;
		File file = new File("requests.csv");
		try {
			Scanner scn = new Scanner(file);
			BufferedWriter writer = new BufferedWriter(new FileWriter("temp.csv"));
			
			while (scn.hasNextLine()) {
				String[] req = scn.nextLine().split(",");
				if (req[1].equals(reqs.getSelectionModel().getSelectedItem())) {
					writer.write(req[0] + "," + desc + ",open\n");
				}
				else {
					writer.write(req[0] + "," + req[1] + "," + req[2] + "\n");
				}
			}
			writer.close();
			scn.close();
			
			BufferedWriter writerNew = new BufferedWriter(new FileWriter("requests.csv"));
			File nFile = new File("temp.csv");
			
			Scanner scn1 = new Scanner(nFile);
			
			while(scn1.hasNextLine()) {
				String[] req1 = scn1.nextLine().split(",");
				writerNew.write(req1[0] + "," + req1[1] + "," + req1[2] + "\n");
			}
			writerNew.close();
			scn1.close();
			
			worked = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return worked;
	}
	
	/**
	 * This method shows the edit request window so instructors can edit requests
	 * @return true/false depending on if the method worked properly
	 */
	public boolean ShowEditRequestWindow(ListView<String> reqs) {
		boolean worked = false;
		try {
			EditRequest.show(new Stage(), reqs);
			worked = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return worked;
	}
}
