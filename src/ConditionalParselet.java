

public  class ConditionalParselet implements Parselet{
	public ConditionalParselet(){
		
	}
	
	public String parse(String text){
		System.out.println("this is the original text : " + text);
		text = text.toLowerCase();
		
		
			if(text.contains("only if")){
				doOnlyIfP(text);
			} else if (text.contains("if and only if")){
				doIfAndOnlyIfP(text);
			} else if (text.contains("if and only if") || text.contains("if but only if")){ //potential comma errors. TODO: redo with regex.
				doIfAndOnlyIf(text);
			} else if(text.contains("if")){
				text = doIfP(text);
			}
		return text;
	}

	private String doIfP(String text) {
		// TODO Auto-generated method stub
		if(commaIsPresent(text)){
			divideByComma(text);
		} else {
			;
		}
		return divideByComma(text);
	}

	private boolean commaIsPresent(String text) {
		return text.contains(",");
	}

	private String divideByComma(String text) {
		int comma= text.indexOf(","); //TODO:potential error if more than one comma
		String antecedent = text.substring(3, comma);
		String consequent = text.substring(comma+1);
		StringBuilder sb = new StringBuilder();
		sb.append("if(");
		sb.append(antecedent);
		sb.append("){");
		sb.append(consequent);
		sb.append("};");
		text=sb.toString();

		System.out.println("THIS IS THE NW STRING " + text);
		return text;
	}

	private void doIfAndOnlyIf(String text) {
		// TODO Auto-generated method stub
		
	}

	private void doIfAndOnlyIfP(String text) {
		// TODO Auto-generated method stub
		
	}

	private void doOnlyIfP(String text) {
		// TODO Auto-generated method stub
		
	}
}
