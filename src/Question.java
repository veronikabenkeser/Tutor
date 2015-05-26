import java.util.ArrayList;

public class Question {
	String question;
    String[] answers;
    int correct;
    
    public Question(String questionPrompt, String[] answers, int answer) {
    	this.question = questionPrompt;
    	this.answers = answers;
    	this.correct = answer;
	}

	public boolean isCorrectAnswer(int selection) {
        return selection==correct;
    }
	
	public String getQuestionPrompt(){
		return question;
	}
	
	public String[] getAnswers(){
		return answers;
	}
	
	public int getAnswerInt(){
		return correct;
	}
    
}
