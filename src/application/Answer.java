package application;
public class Answer {
	private int QuestionId;
	private int AnswerId;
	private String Answer;
	private String name;
	private String preferred = "*Preferred*";
	private boolean isPreferred = false;
	// Constructor for the Answer object
	public Answer (String Answer, String name, int QuestionID, int ansID) {
		this.AnswerId = ansID;
		this.Answer = Answer;
		this.name = name;
		this.QuestionId = QuestionID;
	}
	
	// Sets the answer
	public void SetAnswer(String newAnswer) {
		this.Answer = newAnswer;
	}
	
	// Gets the ID of the question that is being answered
	public int getQuesId() {
		return this.QuestionId;
	}
	
	// Gets the ID of the answer
	public int getAnsID() {
		return this.AnswerId;
	}
	
	// Gets the name of the person who wrote the answer
	public String getName() {
		return this.name;
	}
	
	public String getAnswerBody() {
		return Answer;
	}
	
	// Default string output when outputting object to console
@Override
    public String toString() {
        return "User " + this.name + " answered: " + "\n" + this.Answer + (isPreferred ? preferred : "") + "\n\n\n";
    }

	public void preferAnswer() {
		isPreferred = true;
	}
	
	public void dePrefer() {
		isPreferred = false;
	}
}