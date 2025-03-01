package application;

import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AnswerList {
	public ScrollPane display(Label q, Student topic, Answers ans, Label error) {
		
		HBox layout = new HBox(2);
		
		VBox leftLayout = new VBox(3);
		ObservableList<String> sourceStrList = FXCollections.observableArrayList(ans.showAnswerText(topic.getId()));
		ListView<String> answerList = new ListView<String>(sourceStrList);
		HBox answerAndPrefer = new HBox(4);
		Button answerButton = new Button("Create an answer");
		Button preferAnswer=  new Button("Prefer Answer");
		Button deleteAnswer = new Button ("Delete Answer");
		Button editAnswer = new Button("Edit Answer");
		HBox editAndDelete = new HBox(4);
		VBox answerButtons = new VBox(4);
		editAndDelete.getChildren().addAll(deleteAnswer, editAnswer);
		answerAndPrefer.getChildren().addAll(answerButton, preferAnswer);
		answerButtons.getChildren().addAll(answerAndPrefer, editAndDelete);
		VBox answerBox = new VBox();
		
		
		answerButtons.setMinHeight(60);
		
		answerButton.setOnAction(e -> {
			AnswerController.show(new Stage(), answerList, ans, topic, error);
			answerList.refresh();
		});
		
		leftLayout.getChildren().addAll(q, answerList, answerButtons);
		
		VBox answerB = new VBox();
		
		
		Label answerInfo = new Label("An Answer will show up here.");
		answerB.getChildren().add(answerInfo);
		layout.getChildren().addAll(leftLayout, answerB);
		ScrollPane answers = new ScrollPane(layout);
		answerBox.getChildren().add(answerInfo);
		
		// When an answer is clicked, show who wrote the answer and the answer text
		answerList.setOnMouseClicked(a -> {
			try {
				String selected = answerList.getSelectionModel().getSelectedItem();
				answerB.getChildren().clear();
				int starIdx = selected.indexOf('*');
				if(starIdx == -1) {
					answerB.getChildren().add(new Label("Current Answer:\n" + ans.getAnsInfo(answerList.getSelectionModel().getSelectedItem())));
				} else {
					answerB.getChildren().add(new Label("Current Answer:\n" + ans.getAnsInfo(answerList.getSelectionModel().getSelectedItem().substring(0, starIdx-1))));
				}
			} catch(Exception e) {
					error.setText("Please select a valid answer");
				}
		});
		
		// changes answer list text to show that the answer is preferred
		preferAnswer.setOnAction(e -> {
			
			
			int idx = answerList.getSelectionModel().getSelectedIndex();
			ObservableList<String> l = answerList.getItems();
			String s = l.get(idx);
			l.remove(idx);
			l.add(idx, s + " *preferred*");
			Answer current = ans.getAnswer(s);
			if(current != null) {
				ans.preferAnAnswer(current.getAnswerBody());
				error.setText("Success");
			} else {
				error.setText("The answer does not exist");
			}
			
		});
		
		// When delete answer is clicked, delete answer from both answer list and array list
		deleteAnswer.setOnAction(e -> {
			String selected = answerList.getSelectionModel().getSelectedItem();
			answerList.getItems().remove(selected);
			ans.deleteUserAnswer(ans.getAnswer(selected).getAnsID(), "Mooey001");
			answerBox.getChildren().clear();
			error.setText("Success!");
			answerB.getChildren().clear();
		});
		
		// edit answer in answer list and array list
		editAnswer.setOnAction(e -> {
			AnswerEditor.show(new Stage(),ans,answerList, answerB, error);
			
		});
		
		return answers;
	}
	
	
	
}