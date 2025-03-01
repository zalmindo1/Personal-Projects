package application;
import java.util.*;
public class Answers{
	// Creates an array list for storing answers
	LinkedList<Answer> ansList = new LinkedList<Answer>();
	int preferredIndex = -1;
	
	// Stores an answer into the list
	public void StoreAnswer(String Answer, String user, int QuestionID) {
		ansList.add(new Answer(Answer, user, QuestionID, ansList.size()));
	}
	
	// Shows the answer text to a label in GUI
	public ArrayList<String> showAnswerText(int QuesID) {
		ArrayList<String> resultArray = new ArrayList<>();
		for (Answer ans : ansList)
			if (ans.getQuesId() == QuesID) {
				resultArray.add(ans.getAnswerBody());
			}
		return resultArray;
	}
	
	// Gets specific answer to question
	public Answer getAnswer(String answerText) {
		Answer current = ansList.getFirst();
		int i = 1;
		while(current != null) {
			if(current.getAnswerBody().compareTo(answerText) == 0)
				return current;
			current = ansList.get(i++);
		}
		return null;
	}
	
	// Shows all answers for a specific question and displays them in list in GUI
	public ArrayList<Answer> showAnswers(int QuesID) {
		ArrayList<Answer> resultArray = new ArrayList<>();
		for (Answer ans : ansList)
			if (ans.getQuesId() == QuesID) {
				resultArray.add(ans);
			}
		return resultArray;
		}
	
	// Shows all answers for specific question in console
	public void showAnswers2(int QuesID) {
		for (Answer ans : ansList)
			if (ans.getQuesId() == QuesID) {
				System.out.println(ans.getName() + " Wrote: " + ans.getAnswerBody());
			}
		}
	
	// Shows all answers the user wrote
	public void showUserAnswers(String username) {
		for (Answer ans : ansList)
			if(ans.getName() == username) {
				System.out.println(ans);
			}
	}
	
	// Edits the user's answers
	public void editUserAnswer (String user, int AnsID, String newAnswer) {
		for (Answer ans : ansList)
			if (ans.getName().equals(user) && ans.getAnsID() == AnsID) {
				ans.SetAnswer(newAnswer);
			}
			else {
				System.out.println("Error: you do not have access to edit this answer");
			}
	}
	
	// Deletes a user's answer
	public void deleteUserAnswer(int ID, String user) {
        Iterator<Answer> iterator = ansList.iterator();
        while (iterator.hasNext()) {
            Answer ans = iterator.next();
            if (ans.getAnsID() == ID && ans.getName().equals(user)) {
                iterator.remove();
                return;
            }
        }
        System.out.println("Error: you do not have permission to remove this answer \n");
    }
	
	// Gets information on a specific answer
	public String getAnsInfo(String ansBody) {
		if(ansBody!= null) {
			Answer current = ansList.getFirst();
		
			int i = 1;
			while(current != null) {
				if(ansBody.compareTo(current.getAnswerBody()) == 0) {
					System.out.println("Checking: " + ansBody + " and " + current.getAnswerBody());
					return current.toString();
				}
				current = ansList.get(i++);
			}
		}
		return null;
	}
	
	
	// Marks an answer as the solution to a question
	public void preferAnAnswer(String ansBody) {
		if(ansBody!= null) {
			Answer current = ansList.getFirst();
		
			int i = 1;
			while(current != null) {
				if(ansBody.compareTo(current.getAnswerBody()) == 0) {
					System.out.println("Checking: " + ansBody + " and " + current.getAnswerBody());
					current.preferAnswer();
				}
				current = ansList.get(i++);
			}
		}
	}
}