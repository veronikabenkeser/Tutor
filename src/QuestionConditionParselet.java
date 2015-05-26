
public class QuestionConditionParselet implements Parselet{

	@Override
	public String parse(String text) {
		if(text.matches(".*must\\s*be\\s*true.*")){
			text = text.replaceFirst("must\\s*be\\s*true", "MBT");
		} else if (text.matches(".*must\\s*be\\s*false.*")){
			text= text.replaceFirst("must\\s*be\\s*false", "MBF");
		} else if (text.matches(".*can\\s*be\\s*true.*")){
			text=text.replaceFirst("can\\s*be", "MayBT");
			
		} else if (text.matches(".*cannot\\s*be\\s*\\d")){// TODO: work on cases like "who cannot be 3?" and
														// "which player must be 2nd?"
			text=text.replaceFirst("cannot\\s*be", "MayBF"); //re-write!
			
			
		} else if (text.matches(".*cannot\\s*be\\s*true.*")){
			text=text.replaceFirst("cannot\\s*be\\s*true", "MBF");
		} else if ( text.matches(".*cannot\\s*be\\s*false.*")){
			text=text.replaceFirst("cannot\\s*be\\s*false", "MBT");
		} else {
			System.out.println("The question cannot be interpreted."); 
		}
		return text;
	}

}
