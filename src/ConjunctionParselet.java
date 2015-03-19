public class ConjunctionParselet implements Parselet {
	@Override
	public String parse(String text) {
		text = text.replaceAll("and", "&&");
		return text;
	}
}
