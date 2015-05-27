import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameParselet implements Parselet {
	@Override
	public String parse(String text) {
		StringBuilder sb = new StringBuilder();

		Pattern pattern0 = Pattern.compile("(\\(\\s*([\\w])+\\s*\\))?\\s*(\\b[A-Z]\\b)(\\W[^==]+?)(\\d)");
		Matcher matcher0 = pattern0.matcher(text);
		
		
		if(matcher0.find()){
			String answerChoiceLetter = matcher0.group(1);
			String player = matcher0.group(3);
			String negation = matcher0.group(4);
			String num=matcher0.group(5);
			
			if (answerChoiceLetter !=null){
				sb.append(answerChoiceLetter + " ");
			}
			sb.append("nodes.get(");
			sb.append(player);

			if (negation.contains("not") || negation.contains("cannot")) {
				sb.append(") != ");
			} else {
				sb.append(") == ");
			}
			sb.append(num);
			System.out.println("Text :" + text);
			
			text = matcher0.replaceFirst(sb.toString());
		}
		System.out.println("NameParselet is done. Result " + text);
		return text;
	}
}

