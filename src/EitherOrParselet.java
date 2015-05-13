import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EitherOrParselet implements Parselet {
	StringBuilder sb = new StringBuilder();
	String oldString = "";

	@Override
	public String parse(String text) {
		String newString = "";
		String oldStringRight = "";
		String oldStringLeft = "";

		if (text.matches("\\w*\\s*([(])+.*")) { // if it is in the form if(){
												// blah blah};

			int begIndex = text.indexOf("either");
			int endIndex = text.indexOf("or");
			String tempSubStr = text.substring(begIndex, endIndex);

			oldStringRight = text.substring(0, begIndex);

			parseTempSubStr(tempSubStr);

			begIndex = text.indexOf("or");

			tempSubStr = text.substring(begIndex);

			if (tempSubStr.contains(")")) {
				endIndex = text.indexOf(")");
				tempSubStr = text.substring(begIndex, endIndex);
				oldStringLeft = text.substring(endIndex);

			} else {
				tempSubStr = text.substring(begIndex);
				oldStringLeft = "};";
			}

			parseTempSubStr(tempSubStr);

		}

		newString = sb.toString();
		newString = oldStringRight + newString + oldStringLeft;

		System.out.println("EitherOrParselet is done. Result: " + newString);
		return newString;
	}

	private void parseTempSubStr(String tempSubStr) {
		for (int i = 0; i < tempSubStr.length(); i++) {
			String st = Character.toString(tempSubStr.charAt(i));

			if (st.matches("\\d")) {

				int digit1 = Integer.parseInt(st);
				Pattern pattern = Pattern.compile("[A-Z]+?");
				Matcher matcher = pattern.matcher(tempSubStr);

				matcher.find();
				String letter1 = Character.toString(tempSubStr.charAt(matcher
						.start()));
				sb.append("nodes.get(");
				sb.append(letter1);
				sb.append(") == ");
				sb.append(digit1);
				if (!tempSubStr.contains("or"))
					sb.append("||");
			}
		}
	}
}
