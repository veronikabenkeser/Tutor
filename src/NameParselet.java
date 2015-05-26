//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class NameParselet implements Parselet {
//	String newString = "";
//	String subStr = "";
//
//	@Override
//	public String parse(String text) {
//		StringBuilder sb = new StringBuilder();
//		String oldStrLeft = "";
//		String oldStrRight = "";
//
//		Pattern pattern0 = Pattern.compile("\\b[A-Z]\\b[^==]+?\\d");
//		Matcher matcher0 = pattern0.matcher(text);
//
//		matcher0.find();
//
//		subStr = text.substring(matcher0.start(), matcher0.end());
//
//		oldStrLeft = text.substring(0, matcher0.start());
//		oldStrRight = text.substring(matcher0.end() + 1);
//
//		for (int i = 0; i < subStr.length(); i++) {
//
//			String st = Character.toString(subStr.charAt(i));
//			if (st.matches("\\d")) {
//
//				int digit1 = Integer.parseInt(st);
//				int digit1Position = subStr.indexOf(digit1);
//				int d = Math
//						.abs((text.indexOf(matcher0.start()) + digit1Position));
//
//				Pattern pattern1 = Pattern.compile("[A-Z]+?");
//				Matcher matcher1 = pattern1.matcher(subStr);
//
//				matcher1.find();
//				int a = matcher1.start();
//				String letter = Character.toString(subStr.charAt(a));
//
//				sb.append("nodes.get(");
//				sb.append(letter);
//
//				if (subStr.contains("not")) {
//					sb.append(") != ");
//				} else {
//					sb.append(") == ");
//				}
//				sb.append(digit1);
//			}
//		}
//		newString = oldStrLeft + sb.toString() + oldStrRight;
//		System.out.println("NameParselet is done. Result " + newString);
//		return newString;
//	}
//}

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
			System.out.println("TEXTTTTTTTT" + text);
			
			text = matcher0.replaceFirst(sb.toString());
		}
		System.out.println("NameParselet is done. Result " + text);
		return text;
	}
}

