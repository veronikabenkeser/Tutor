import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class QuestionIfParselet implements Parselet{

	@Override
	public String parse(String text) {
		
		Pattern pattern = Pattern.compile("(?i)if(.*)(which|who|what)(.*)");
		Matcher matcher = pattern.matcher(text);
		StringBuilder sb = new StringBuilder();
		
		if(matcher.find()){
			String temp = matcher.group(3);
			String cond=matcher.group(1);
			
			if (temp.contains("MBT")){
				temp="MBT";
			} else if (temp.contains("MBF")){
				temp = "MBF";
			} else if (temp.contains("MayBT")){
				temp = "MayBT";
			} else if (temp.contains("MayBF")){
				temp = "MayBF";
			} else {
				System.out.println("Question type has not been determined.");
			}
//			Pattern pattern2 = Pattern.compile("([A-Z])\\w*([^==]+?)(\\d)");
//			Matcher matcher2 = pattern2.matcher(cond);
//			
//			while(matcher2.find()){
//				String l = matcher2.group(1);
//				String relationship = matcher2.group(2);
//				String num = matcher2.group(3);
//				
//				if (relationship.contains("not") || relationship.contains("cannot")){
//					relationship = "!=";
//				} else {
//					relationship = "==";
//				}
//				
//				cond = matcher2.replaceFirst(l+relationship+num);	
//			}
			
			sb.append("if(");
			sb.append(cond);
			sb.append("){ return (");
			sb.append(temp);
			sb.append("); }");
			text = sb.toString();
		}
		return text;
	}
}
