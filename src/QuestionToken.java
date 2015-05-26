public class QuestionToken {
	String text;
	Parselet parselet;
	String[] value;
	
	public QuestionToken(String text, Parselet parselet) {
		this.text = text;
		this.parselet = parselet;
		//this.value=secondPart;
	}
	
	public String getText(){
		return text;
	}
	
	public Parselet getParselet(){
		return parselet;
	}
	
	public String[] getValue(){
		return value;
	}
}
