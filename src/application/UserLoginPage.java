package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

import databasePart1.*;

/**
 * The UserLoginPage class provides a login interface for users to access their accounts.
 * It validates the user's credentials and navigates to the appropriate page upon successful login.
 */
public class UserLoginPage {
	
    private final DatabaseHelper databaseHelper;

    public UserLoginPage(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public void show(Stage primaryStage) {
    	// Input field for the user's userName, password
        TextField userNameField = new TextField();
        userNameField.setPromptText("Enter userName");
        userNameField.setMaxWidth(250);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");
        passwordField.setMaxWidth(250);
        
        CheckBox reviewerBox = new CheckBox();
        reviewerBox.setText("Reviewer");
        
        CheckBox staffBox = new CheckBox();
        staffBox.setText("Staff");
        
        CheckBox instBox = new CheckBox();
        instBox.setText("Instructor");
        
        // Label to display error messages
        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red; -fx-font-size: 12px;");


        Button loginButton = new Button("Login");
        
        loginButton.setOnAction(a -> {
        	// Retrieve user inputs
            String userName = userNameField.getText();
            String password = passwordField.getText();
            try {
            	User user=new User(userName, password, "");
            	WelcomeLoginPage welcomeLoginPage = new WelcomeLoginPage(databaseHelper);
            	
            	// Retrieve the user's role from the database using userName
            	String role = databaseHelper.getUserRole(userName);
            	
            	if(role!=null) {
            		user.setRole(role);
            		if(databaseHelper.login(user)) {
            			ValidRoleCheck validr = new ValidRoleCheck();
            			if (reviewerBox.isSelected()) {
            				if (validr.CheckValidReviewer(userName)) {
            					welcomeLoginPage.show(primaryStage, user, true, false, false);
            				}
            				else {
            					errorLabel.setText("Error: you are not a reviewer");
            				}
            			}
            			else if (staffBox.isSelected()) {
            				if (validr.CheckValidStaff(userName)) {
            					welcomeLoginPage.show(primaryStage, user, false, true, false);
            				}
            				else {
            					errorLabel.setText("Error: you are not a staff member");
            				}
            			}
            			else if(instBox.isSelected()) {
            				if (validr.CheckValidInstructor(userName)) {
            					welcomeLoginPage.show(primaryStage, user, false, false, true);
            				}
            				else {
            					errorLabel.setText("Error: you are not an instructor");
            				}
            			}
            			else {
            				welcomeLoginPage.show(primaryStage, user, false, false, false);
            			}
            		}
            		else {
            			// Display an error if the login fails
                        errorLabel.setText("Error logging in");
            		}
            	}
            	else {
            		// Display an error if the account does not exist
                    errorLabel.setText("user account doesn't exists");
            	}
            	
            } catch (SQLException e) {
                System.err.println("Database error: " + e.getMessage());
                e.printStackTrace();
            } 
        });

        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        layout.getChildren().addAll(userNameField, passwordField, reviewerBox, staffBox, instBox, loginButton, errorLabel);

        primaryStage.setScene(new Scene(layout, 800, 400));
        primaryStage.setTitle("User Login");
        primaryStage.show();
    }
}
