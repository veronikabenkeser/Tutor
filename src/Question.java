public class Question {
	String questionToDisplay;
	String modifiedQuestion;
	String[] answerChoicesToDisplay;
	String[] modifiedAnswerChoices;
	QuestionParser3 qp;
	
	public Question(String questionToDisplay, String modifiedQuestion, String[] answerChoicesToDisplay2, String[] modifiedAnswerChoicesToDisplay){
		this.questionToDisplay=questionToDisplay;
		this.modifiedQuestion=  modifiedQuestion;
		this.answerChoicesToDisplay = answerChoicesToDisplay2;
		this.modifiedAnswerChoices=modifiedAnswerChoicesToDisplay;
	}
	
	public String getQuestionToDisplay(){
		return questionToDisplay;
	}
	
	public String getModifiedQuestion(){
		return convertPromptToLogic();
	}
	
	public String[] getAnswerChoicesToDisplay(){
		return answerChoicesToDisplay;
	}
	
	public String[] getModifiedAnswerChoices(){
		return convertAnswerChoicesToLogic();
	}
	
	private String convertPromptToLogic(){
		String result="";
		System.out.println("This is the modifiedQuestion object : " + modifiedQuestion );
		QuestionParser3 qp = new QuestionParser3();
		result = qp.doVisibleActions(modifiedQuestion);
		return result;
	}
	
	private String[] convertAnswerChoicesToLogic(){
		QuestionParser3 qp = new QuestionParser3();
		String[] result = modifiedAnswerChoices;
		for (int i=0; i<modifiedAnswerChoices.length ; i++){
			result[i] = qp.doVisibleActions(modifiedAnswerChoices[i]);
		}
		
		//print the result
		for(String s: result){
			System.out.println("printing the result after convertAnswerChoicesToLogic is done: " + s);
		}
		
		
		return result;
	}
}