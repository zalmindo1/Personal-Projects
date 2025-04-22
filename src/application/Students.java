package application;
import java.util.*;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * <p> Title: Questions Handler <p>
 * 
 * <p> Description: This java class handles actions regarding student questions
   
   <p> Copyright: Zachary Almindo © 2025<p>
   
   @author Zachary Almindo
   @version 1.0
 */
public class Students {
	// Sets up array list to store questions
	ArrayList<Student> stuList = new ArrayList<Student>();
	
	// Stores a question into the list of questions
	public void StoreStudents(String questionTitle, String questionBody, String username, int ID) {
		stuList.add(new Student(questionTitle, questionBody, username, ID));
	}
	
	// Displays all questions to console
	public void ViewStudentQuestions () {
		for (Student stu : stuList)
			System.out.println(stu);
	}
	
	// Displays questions to question list
	public String displayQuestion(String title) {
		String output = null;
		for (Student stu : stuList) {
			if (stu.getQuesTitle().equals(title)) {
				output = stu.getName() + " Wrote:\n" + stu.getQuesTitle() + "\n" + stu.getQuesBody();
			}
		}
		
		return output;
	}
	
	public Student getStudent(String title) {
		for (Student stu : stuList) {
			if (stu.getQuesTitle().equals(title)) {
				return stu;
			}
		}
		return null;
	}
	
	// Displays only questions the user asked
	public void ViewUserQuestions(String user) {
		for (Student stu: stuList)
			if (stu.getName() == user) {
				System.out.println(stu);
			}
	}
	
	// Checks if a question in the list has a specific ID
	public boolean CheckValidQuestionID(int ID) {
		boolean validID = false;
		for (Student stu : stuList)
			if (stu.getId() == ID) {
				validID = true;
			}
		return validID;
	}
	
	public boolean checkValidQuestionTitle(String title) {
		boolean validT = false;
		for (Student stu : stuList)
			if (stu.getQuesTitle().equals(title)) {
				validT = true;
				return validT;
			}
		return validT;
	}
	
	public boolean checkEditPermission(String title, String user) {
		boolean validEdit = false;
		for (Student stu : stuList)
			if (stu.getQuesTitle().equals(title) && stu.getName().equals(user)) {
				validEdit = true;
				return validEdit;
			}
		return validEdit;
	}
	
	// Edits the question title
	public void EditUserQuestionTitle(String username, int ID, String newTitle) {
		for (Student stu : stuList)
			if (stu.getName().equals(username) && stu.getId() == ID) {
				stu.setQuestionTitle(newTitle);
			}
			else {
				System.out.println("Error: you do not have access to edit this question");
			}
	}
	
	// Edits the question body
	public void EditUserQuestionBody(String username, int ID, String newBody) {
		for (Student stu : stuList)
			if (stu.getName().equals(username) && stu.getId() == ID) {
				stu.setQuestionBody(newBody);
				break;
			}
			else {
				System.out.println("Error: you do not have access to edit this question");
			}
	}
	
	// Adds one to the question answer count
	public void addAnsCount(int QuesID) {
		for (Student stu : stuList)
			if (stu.getId() == QuesID) {
				stu.setAnsCount(1);
			}
	}
	
	// Searches for a question through keywords
	public void SearchQuestion(String search) {
		for (Student stu : stuList) 
			if (stu.getQuesTitle().contains(search)) {
				System.out.print(stu);
			}
	}
	
	// Removes a student question by question ID
	public void RemoveStudentQuesByID(int ID, String user) {
        Iterator<Student> iterator = stuList.iterator();
        while (iterator.hasNext()) {
            Student stu = iterator.next();
            if (stu.getId() == ID && stu.getName().equals(user)) {
                iterator.remove();
                return;
            }
        }
        System.out.println("Error: you do not have permission to remove this question");
    }
	
	
	// Removes a student question by question Title
	public void RemoveStudentQuesByTitle(String title, ListView<String> qList, VBox qViewer, String user, Label error) {
        Iterator<Student> iterator = stuList.iterator();
        boolean valid = false;
        while (iterator.hasNext()) {
            Student stu = iterator.next();
            if (stu.getQuesTitle().equals(title) && stu.getName().equals(user)) {
                iterator.remove();
                qList.getItems().remove(qList.getSelectionModel().getSelectedIndex());
    			qViewer.getChildren().clear();
    			valid = true;
                return;
            }
        }
        if (!valid) {
        	error.setText("Error: You do not have permission to delete this question");
        }
    }
	
	// Sorts the question list by showing question with a specific phrase
	public void SortQuestionList(String search, ListView<String> qList) {
		qList.getItems().clear();
		Iterator<Student> iterator = stuList.iterator();
		while (iterator.hasNext()) {
			Student stu = iterator.next();
			if (stu.getQuesTitle().contains(search)) {
				qList.getItems().add(stu.getQuesTitle());
			}
		}
	}
	
	// Edits the user questions and updates the question list to display new question body and title
	public void EditQuestion(String title, String newTitle, String newBody, ListView<String> qList, String user) {
		qList.getItems().clear();
		Iterator<Student> iterator = stuList.iterator();
		while (iterator.hasNext()) {
			Student stu = iterator.next();
			if (stu.getQuesTitle().equals(title) && stu.getName().equals(user)) {
				stu.setQuestionTitle(newTitle);
				stu.setQuestionBody(newBody);
			}
			else {
				
			}
			qList.getItems().add(stu.getQuesTitle());
		}
	}
	
	public String StudentNameByTitle(String title) {
		String name = "";
		for (Student stu : stuList) {
			System.out.println(stu.getQuesTitle());
		}
		return name;
	}
	
}