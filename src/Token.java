public class Token {
	String text;
	Parselet parselet;
	
	public Token(String text, Parselet parselet) {
		this.text = text;
		this.parselet = parselet;
	}
	
	public String getText(){
		return text;
	}
	
	public Parselet getParselet(){
		return parselet;
	}
}
