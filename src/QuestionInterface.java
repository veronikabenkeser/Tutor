import java.util.ArrayList;

public interface QuestionInterface {
	public int eval(ArrayList<String[]> allSolutions);
	public String getTextPrompt();
	public String[] getTextAnswerChoices();
}
