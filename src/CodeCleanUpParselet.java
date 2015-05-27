import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeCleanUpParselet implements Parselet {

	@Override
	public String parse(String text) {
		//text = addSemicolon(removePeriod(text));

		//if (text.contains(".")){
		//	text = removePeriod(text);
		//}
		
		text = removePeriod(text);
		return text;
	}

	private String removePeriod(String text) {
		Pattern pattern = Pattern.compile("[^(nodes)][.]");
		Matcher matcher = pattern.matcher(text);
		if (matcher.find()){
			String tempStr = text.substring(matcher.start(), matcher.end());
			if (tempStr.contains(".")){
				tempStr = tempStr.replace(".", "");
				text = text.replace(text.substring(matcher.start(), matcher.end()),
					tempStr);
			}
		}
		return text;
	}

	/*
	private String addSemicolon(String text) {
		text = text + ";";
		return text;
	}
	*/
}


