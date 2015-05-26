import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RelationshipParselet implements Parselet {

	@Override
	public String parse(String text) {
		Pattern pattern = Pattern.compile("(\\(\\s*[A-Z]\\s*\\))?.*([A-Z]).*immediately\\s*after\\s*([A-Z])");
		Matcher matcher = pattern.matcher(text);
		
		Pattern pattern2 = Pattern.compile("(\\(\\s*[A-Z]\\s*\\))?.*([A-Z]).*immediately\\s*before\\s*([A-Z])");
		Matcher matcher2 = pattern2.matcher(text);
		
		if (matcher.find()){
			String qNum  = matcher.group(1);
			String player1 = matcher.group(2);
			String player2 = matcher.group(3);
			
			if (text.contains("cannot") || text.contains("not")||text.contains("can't")){
				text = qNum + "nodes.get("+player1+") != nodes.get("+player2+") + 1";
			} else {
				text= qNum + " nodes.get("+player1+") == nodes.get("+player2+") + 1";
			}
			
		} else if (matcher2.find()){
			String qNum  = matcher2.group(1);
			String player1 = matcher2.group(2);
			String player2 = matcher2.group(3);
			if (text.contains("cannot") || text.contains("not")||text.contains("can't")){
					text = qNum + " nodes.get("+player1+")+1 != nodes.get("+player2+")";
			} else {
				System.out.println("almost text" + text);
					text= qNum + " nodes.get("+player1+")+1 == nodes.get("+player2+") ";
			}
		} else {
			System.out.println("Error in the Relationship Parselet.");
		}
		return text;
	}
}
