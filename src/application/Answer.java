package application;
/**
 * Answer object class that hold information about a student answer
 */
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
	/**
	 * sets the answer
	 * @param newAnswer
	 */
	// Sets the answer
	public void SetAnswer(String newAnswer) {
		this.Answer = newAnswer;
	}
	/**
	 * Gets question ID of answer
	 * @return
	 */
	// Gets the ID of the question that is being answered
	public int getQuesId() {
		return this.QuestionId;
	}
	/**
	 * Gets answer ID
	 * @return
	 */
	// Gets the ID of the answer
	public int getAnsID() {
		return this.AnswerId;
	}
	/**
	 * Gets the name of the person who wrote the answer
	 * @return
	 */
	// Gets the name of the person who wrote the answer
	public String getName() {
		return this.name;
	}
	/**
	 * Gets the answer body
	 * @return
	 */
	public String getAnswerBody() {
		return Answer;
	}
	
	// Default string output when outputting object to console
@Override
    public String toString() {
        return "User " + this.name + " answered: " + "\n" + this.Answer + (isPreferred ? preferred : "") + "\n\n\n";
    }
	/**
	 * Method to set a preferred answer
	 */
	public void preferAnswer() {
		isPreferred = true;
	}
	/**
	 * Method to un prefer an answer
	 */
	public void dePrefer() {
		isPreferred = false;
	}
}