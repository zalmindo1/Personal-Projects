package application;
public class AutomatedTesting {
	static Students students = new Students();
	static Answers answers = new Answers();
	
	static String user = "Mooey"; // States the user logged in is Mooey
	
	public static void loadData() {
		// Loading questions from ED Discussion
		students.StoreStudents("HW2 Clarification", "Do we lose points if we utilize more than four classes to implement the functionalities for HW2", "Anonymous", 1);
		students.StoreStudents("Maven/Gradle for Team Project", "Are we able to convert our Team project or HW assignments over to a Maven or Gradle project for easier dependency management? This is how the majority of newer Java projects I have worked on in a Production Environment are configured and it would get rid of having to configure the build path and jars needed in Eclipse. This would also make it easier to user JUnit when we come to that.", "Zachary Chalmers", 2);
		students.StoreStudents("Grade dispute guidance", "I apologize if this question has been asked before. I believe I remember it being answered, but I can't find it when I search.\r\n"
				+ "\r\n"
				+ "If I have a grade dispute, what is the correct process to follow?", "Anonymous", 3);
		students.StoreStudents("Using ArrayList for Questions and Answers class", "I don't understand why we need questions and arrays classes. From my understanding, all that needs to be stored is a list which allows removing  and adding. Arraylist already has this functionality. Can we just use arraylist? ", "Henry Stevens", 4);
		students.StoreStudents("HW2 understanding?", "Hello, so for HW2 are we supposed to implement the classes as well as making it work with the interface or are we supposed to just create the classes and the test cases? Thank you", "Anonymous", 5);
		
	}
	
	public static void main(String[] args) {
		loadData();
		
		// Loading answers from ED Discussion and updating answer count on each question that is answered
		answers.StoreAnswer("Not directly.  If you include material that is not required, you need to make it easy for the grader to see what is required.", "Lynn Carter", 1);
		students.addAnsCount(1);
		answers.StoreAnswer("If you know how to do that and don't need help from the teaching staff, that would be fine.  We are not in a position to help you diagnose and correct problems with these tools on the wide array of hardware and software being used in the class.", "Lynn Carter", 2);
		students.addAnsCount(2);
		answers.StoreAnswer("Start with the grader.  If you do not hear from the grader in a day or two, forward a copy of the email to the TAs.", "Lynn Carter", 3);
		students.addAnsCount(3);
		answers.StoreAnswer("The way I interpreted it was that the Questions class will provide a space for multiple lists of whatever data structure you choose. For me, I have an arraylist that will store all questions, and an arraylist that will store a subset of those questions.", "James Cloud", 4);
		answers.StoreAnswer("Your implementation of a list class can employ an ArrayList.", "Lynn Carter", 4);
		answers.StoreAnswer("Many of the student stories mention different filtering methods that could be employed on the collection of questions or answers.", "Evan Samano", 4);
		students.addAnsCount(4);
		students.addAnsCount(4);
		students.addAnsCount(4);
		answers.StoreAnswer("You are creating a standalone application as mentioned in the deliverable, however you are free to reuse any of your code from the previous HW1 or foundational code", "Preetom Biswas", 5);
		answers.StoreAnswer("You must be able to show that your four classes work.", "Lynn Carter", 5);
		students.addAnsCount(5);
		students.addAnsCount(5);
		
		// Displays all questions asked along with how many answers each question has
		students.ViewStudentQuestions();
		
		System.out.println("--------------------------------------------------------------------------------");
		
		// Shows answers for each question
		System.out.println("Showing answers for 1st question \n");
		answers.showAnswers2(1);
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("Showing answers for 2nd question \n");
		answers.showAnswers2(2);
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("Showing answers for 3rd question \n");
		answers.showAnswers2(3);
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("Showing answers for 4th question \n");
		answers.showAnswers2(4);
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("Showing answers for 5th question \n");
		answers.showAnswers2(5);
		System.out.println("--------------------------------------------------------------------------------");
		
		// Showing that a user cannot change any question titles or bodies unless they are the user that made them
		System.out.println("Showing negative test for changing another user's question body \n");
		students.EditUserQuestionBody(user, 1, "What am I supposed to do for HW2");
		System.out.println("\n \n");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("Showing negative test for changing another user's question title \n");
		students.EditUserQuestionTitle(user, 1, "HW2?");
		System.out.println("--------------------------------------------------------------------------------");
		
		// Showing that a user can add a question
		System.out.println("Storing user's question with ID of 6 \n");
		students.StoreStudents("How do I get started with HW2", "Im confused as to what I'm supposed to do with the user stories", user, 6);
		students.ViewStudentQuestions();
		System.out.println("--------------------------------------------------------------------------------");
		
		// Shows that the user can display only questions they've asked
		System.out.println("Displaying only questions the user has asked \n");
		students.ViewUserQuestions(user);
		System.out.println("--------------------------------------------------------------------------------");
		
		// Shows that on user created questions, both the title and body can be edited
		System.out.println("Showing positive test for changing a user's question body and title \n");
		students.EditUserQuestionBody(user, 6, "What am I supposed to do for HW2");
		students.EditUserQuestionTitle(user, 6, "HW2?");
		students.ViewStudentQuestions();
		System.out.println("--------------------------------------------------------------------------------");
		
		// Shows that the student can answer any question and updates answer counter on question
		System.out.println("Answering Henry's question and updating the answer count to 4 on that question \n");
		answers.StoreAnswer("Yes Henry, using arraylist is fine", user, 4);
		students.addAnsCount(4);
		
		students.ViewStudentQuestions();
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("Showing new answer \n");
		
		answers.showAnswers2(4);
		System.out.println("--------------------------------------------------------------------------------");
		
		// Shows that only user created questions can be edited
		System.out.println("Showing negative test for changing another user's answer \n");		
		answers.editUserAnswer(user, 4, "Hello");
		answers.showAnswers2(4);
		System.out.println("\n \n");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("Showing positive test \n");
		answers.editUserAnswer(user, 8, "Hello");
		answers.showAnswers2(4);
		System.out.println("--------------------------------------------------------------------------------");
		
		// Shows that the user can search for questions by using keywords
		System.out.println("Searching for questions with the keyword HW2 in the title \n");
		students.SearchQuestion("HW2");
		System.out.println("--------------------------------------------------------------------------------");
		
		// User cannot remove a question unless they made it
		System.out.println("Trying to remove a question that is not the user's ID: 5 \n");
		students.RemoveStudentQuesByID(5, user);
		students.ViewStudentQuestions();
		System.out.println("--------------------------------------------------------------------------------");
		
		// User can remove their own questions
		System.out.println("Removing a question that is the user's \n");
		students.RemoveStudentQuesByID(6, user);
		
		students.ViewStudentQuestions();
		System.out.println("--------------------------------------------------------------------------------");
		
		// User cannot remove an answer that is not theirs
		System.out.println("Attempting to remove a answer that is not the user's \n");
		answers.deleteUserAnswer(4, "Evan Samano");
		
		answers.showAnswers2(4);
		
		// User can remove their own answer
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("Removing the user's answer \n");
		answers.deleteUserAnswer(8, user);
		
		answers.showAnswers2(4);
		
		
	}
}
