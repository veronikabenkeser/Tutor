public class QuestionFinal {
	String prompt;
	String[] answerChoices;
	int answer;
	
	public QuestionFinal(String prompt, String[] answerChoices, int answer){
		this.prompt = prompt;
		this.answerChoices = answerChoices;
		this.answer = answer;
	}
	
	public String getPrompt(){
		return prompt;
	}
	
	public String[] getAnswerChoices(){
		return answerChoices;
	}
	
	public int getAnswer(){
		return answer;
	}
}