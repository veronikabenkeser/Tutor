import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * The class reads the prompt and interprets the rules into their logical structure. 
 */

public class Parser {
	private Reader reader;
	private String newText;
	private String noun;

	private static String[] precedingArr = { "in front of", "before",
			"prior to", "preceded", "earlier", "preceds", "precede", "faster",
			"better" };

	private static String[] followingArr = { "behind", "after", "later" };

	private static String[] relational = { "than" };

	private static String[] neg = { "no", "not" };

	private static String[] conj = { "and", "along with" };

	private static String[] conjInFront = { "both" };

	private static String[] be = { "is", "are" };

	private static String[] quantifiers = { "each", "all", "either", "neither",
			"some", "most", "none", "no", "at least", "at most" };

	private static String ifOnlyIfRegex = "((if)+\\s+(but|or|and)+\\s+only if)";

	private static String orderingGameRegex = "((\\beach\\b|\\bEach\\b)+.*?\\bstudent\\b.*?\\bexactly\\b.?\\b1\\b)";

	private static String[] boundary = { "through", "to" };

	private static String[] gameClassificationKeywords = { "group", "grouped",
			"order", "ordered" };

	public Parser(Reader reader) {
		this.reader = reader;
	}

	public boolean isSimpleOrderingGame() {
		noun = reader.getNoun();
		newText = reader.getNewText();

		Pattern pattern = Pattern.compile(orderingGameRegex);
		Matcher m = pattern.matcher(reader.getNewText());
		return (m.find());
	}

	public void updateSentenceArr() {
		newText = newText.substring(newText.indexOf(":") + 1);

		Pattern pattern = Pattern.compile("[a-zA-Z]");
		Matcher matcher = pattern.matcher(newText);
		matcher.find();
		int tempIndex = matcher.start();
		newText = newText.substring(tempIndex);

		System.out.println("TEXT TEXT: " + newText);
		System.out.println("**");
		Scanner sc = new Scanner(newText);

		System.out.println("TEXT222: " + newText);
		System.out.println("TEXT 444 " + newText);

		while (sc.hasNextLine()) {

			// sc.useDelimiter("([a-zA-Z]+.*\\.)");

			String sentence = sc.nextLine();

			Pattern pattern2 = Pattern.compile("([a-zA-Z]+.*\\.)");
			Matcher matcher2 = pattern2.matcher(sentence);
			if (matcher2.find())
				sentenceArr.add(sentence);
		}
		System.out.println("printing " + sentenceArr);
		System.out.println("**");
		System.out.println(sentenceArr.size());

	}

	public void totalParse() {
		updateSentenceArr();
		parse1();
		// System.out.println(parse1());
	}

	public String parse1() {
		String result = "";
		for (int i = 0; i < sentenceArr.size(); i++) {

			// String firstWord = sentenceArr.get(i).
			if (!sentenceArr.get(i).isEmpty()) {
				String[] words = sentenceArr.get(i).replaceAll("^\\s+\\b", "")
						.split("\\s+");
				System.out.println("words " + Arrays.toString(words));
				System.out.println("word at 0 : " + words[0]);
			}
		}

		return result;
	}

	//private HashMap<String, Parselet> parselets = new HashMap<String, Parselet>();
	private String[] updatedText;
	private ArrayList<String> sentenceArr = new ArrayList<String>();
}
