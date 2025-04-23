package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;

import databasePart1.*;

/**
 * The SetupAdmin class handles the setup process for creating an administrator account.
 * This is intended to be used by the first user to initialize the system with admin credentials.
 */
public class AdminSetupPage {
	
    private final DatabaseHelper databaseHelper;

    public AdminSetupPage(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }
    /**
     * The UI show method to display admin setup page to user
     * @param primaryStage
     */
    public void show(Stage primaryStage) {
    	// Input fields for userName and password
        TextField userNameField = new TextField();
        userNameField.setPromptText("Enter Admin userName");
        userNameField.setMaxWidth(250);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");
        passwordField.setMaxWidth(250);
        
     // Label to display error messages for invalid input or registration issues
        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red; -fx-font-size: 12px;");

        Button setupButton = new Button("Setup");
        
        setupButton.setOnAction(a -> {
        	// Retrieve user input
            String userName = userNameField.getText();
            String password = passwordField.getText();
            
            try {
            	// Checks if username and password are valid
            	// Returns error message if either username or password is not valid
            	String User_e_Label = UserNameRecognizer.checkForValidUserName(userName);
    			String Pass_e_Label = PasswordEvaluator.evaluatePassword(password);
    			if ( User_e_Label != "") {
    				errorLabel.setText(User_e_Label);
    			}
    			else if (Pass_e_Label != ""){
    				errorLabel.setText("Password does not meet crieria. A password must include\n An upper case letter\n A lower case letter\n A number\n A special character(~`!@#$%^&*()_-+{}[]|:,.?/)\n Must be at least 8 characters long\n"
    						+ Pass_e_Label);
    			}
    			else {
    				// Create a new User object with admin role and register in the database
    				User user=new User(userName, password, "admin");
                    databaseHelper.register(user);
                    System.out.println("Administrator setup completed.");
                    
                    // Navigate to the Welcome Login Page
                    new WelcomeLoginPage(databaseHelper).show(primaryStage,user,false,false,false);
    			}
            } catch (SQLException e) {
                System.err.println("Database error: " + e.getMessage());
                e.printStackTrace();
            }
        });

        VBox layout = new VBox(10, userNameField, passwordField, setupButton, errorLabel);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        primaryStage.setScene(new Scene(layout, 800, 400));
        primaryStage.setTitle("Administrator Setup");
        primaryStage.show();
    }
}
