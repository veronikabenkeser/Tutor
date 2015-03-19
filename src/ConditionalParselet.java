public class ConditionalParselet implements Parselet {

	public String parse(String text) {

		if (text.contains("only if")) {
			doOnlyIfP(text);
		} else if (text.contains("if and only if")) {
			doIfAndOnlyIfP(text);
		} else if (text.contains("if and only if")
				|| text.contains("if but only if")) { // potential comma errors.
														// TODO: redo with
														// regex.
			doIfAndOnlyIf(text);
		} else if (text.contains("If")) {
			text = doIfP(text);
		}
		System.out.println("ConditionalParselet is done. Result: " + text);
		return text;
	}

	private String doIfP(String text) {
		// TODO Auto-generated method stub
		if (commaIsPresent(text)) {
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
		String consequent = "";
		String antecedent = "";

		int comma = text.indexOf(","); // TODO:potential error if more than one
										// comma
		antecedent = text.substring(3, comma);

		String a = "then";
		if (text.contains(a)) {
			consequent = text.substring(text.indexOf(a) + a.length());
		} else {
			consequent = text.substring(comma + 1);
		}

		StringBuilder sb = new StringBuilder();
		sb.append("if(");
		sb.append(antecedent);
		sb.append("){");
		sb.append(consequent);
		sb.append("}");
		text = sb.toString();
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
