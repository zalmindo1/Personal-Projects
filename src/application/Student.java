package application;
/**
 * Class that contains information about student question
 */
public class Student {
	
	private int Id;
	private String questionTitle;
	private String questionBody;
	private String name;
	private int ansCount;
	
	// Constructor for the Student question object 
	public Student(String questionTitle, String questionBody, String username, int ID) {
		this.Id = ID;
		this.questionTitle = questionTitle;
		this.questionBody = questionBody;
		this.name = username;
		this.ansCount = 0;
	}
	
	// Gets question ID
	public int getId() {
		return this.Id;
	}
	
	// Gets number of answers
	public int getAnsCount() {
		return this.ansCount;
	}
	
	// Gets question title
	public String getQuesTitle() {
		return this.questionTitle;
	}
	
	public String getQuesBody () {
		return this.questionBody;
	}
	
	// Sets question ID
	public void setId(int id) {
		this.Id = id;
	}
	
	// Sets number of answers
	public void setAnsCount(int count) {
		this.ansCount = this.ansCount + count;
	}
	
	// Gets the name of the user who wrote the question
	public String getName() {
		return this.name;
	}
	
	// Sets question title
	public void setQuestionTitle(String newTitle) {
		this.questionTitle = newTitle;
	}
	
	// Sets question body
	public void setQuestionBody(String newBody) {
		this.questionBody = newBody;
	}
	
	// Default string output when outputting object to console
	@Override
    public String toString() {
        return "QuestionID: " + this.Id + "\n" + "Username: " + this.name + "\n\n" + this.questionTitle + "\n" + this.questionBody + "\n" + "Number of answers: " + this.ansCount +  "\n\n\n";
    }
}
