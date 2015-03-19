import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameParselet implements Parselet {
	StringBuilder sb = new StringBuilder();
	String newString = "";
	String subStr = "";

	@Override
	public String parse(String text) {
		String oldStrLeft = "";
		String oldStrRight = "";

		Pattern pattern0 = Pattern.compile("\\b[A-Z]\\b[^==]+?\\d");
		Matcher matcher0 = pattern0.matcher(text);
		matcher0.find();

		subStr = text.substring(matcher0.start(), matcher0.end());

		for (int i = 0; i < subStr.length(); i++) {
			String st = Character.toString(subStr.charAt(i));
			if (st.matches("\\d")) {

				int digit1 = Integer.parseInt(st);
				Pattern pattern = Pattern.compile("[A-Z]+?");
				Matcher matcher = pattern.matcher(subStr);

				matcher.find();
				int a = matcher.start();
				String letter = Character.toString(subStr.charAt(a));
				sb.append("nodes.get(");
				sb.append(letter);
				sb.append(") == ");
				sb.append(digit1);

				if (text.indexOf(letter) != 0) {
					oldStrLeft = text.substring(0, text.indexOf(letter));
				}

				if (text.indexOf(Integer.toString(digit1)) != text.length() - 1) {
					oldStrRight = text.substring(
							text.indexOf(Integer.toString(digit1)) + 1,
							text.length());
				}
			}
		}

		newString = oldStrLeft + sb.toString() + oldStrRight;
		System.out.println("NameParselet is done. Result " + newString);
		return newString;
	}
}