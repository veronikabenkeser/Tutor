import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * The class defines interpretation rules for the text.
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

		Scanner sc = new Scanner(newText);

		while (sc.hasNextLine()) {

			// sc.useDelimiter("([a-zA-Z]+.*\\.)");

			String sentence = sc.nextLine();

			Pattern pattern2 = Pattern.compile("([a-zA-Z]+.*\\.)");
			Matcher matcher2 = pattern2.matcher(sentence);
			if (matcher2.find())
				sentenceArr.add(sentence);
		}
	}

	public void totalParse() {
		updateSentenceArr();
		// parse1();
		// System.out.println(parse1());
	}

	/*
	 * public String[] separateIntoWords(ArrayList<String> sentenceArr) { String
	 * result = ""; for (int i = 0; i < sentenceArr.size(); i++) {
	 * 
	 * // String firstWord = sentenceArr.get(i). if
	 * (!sentenceArr.get(i).isEmpty()) { String[] words =
	 * sentenceArr.get(i).replaceAll("^\\s+\\b", "") .split("\\s+");
	 * System.out.println("words " + Arrays.toString(words));
	 * System.out.println("word at 0 : " + words[0]); } } return result; }
	 */

	public void doVisibleActions() {
		while (!sentenceArr.isEmpty()) {
			parseInAccordanceWithParselet(createTokens(sentenceArr));
		}

		System.out.println("Resulting interpretations: " + sentenceArrFinal);
	}

	public ArrayList<Token> createTokens(ArrayList<String> sentenceArr) {
		String text = "";
		Parselet parselet = null;
		boolean continueMakingTokens = true;

		for (int i = 0; i < sentenceArr.size(); i++) {

			String sentence = sentenceArr.get(i);

			Pattern pattern0 = Pattern.compile("\\b[A-Z]\\b[^==]+?\\d");
			Matcher matcher0 = pattern0.matcher(sentence);

			if (sentence.matches(".*(if|If)\\s*[^(){}]+")) {
				text = sentence;
				parselet = mParselets.get("if");
				System.out
						.println("Getting the value associated with the if key");
			} else if (sentence.matches(".*\\b(Either|either)\\b.*(or).*")) {
				text = sentence;
				parselet = mParselets.get("either");
				System.out
						.println("Getting the value associated with the either key");

			} else if (sentence.matches(".*\\b(Neither|neither)\\b.*(nor).*")) {
				text = sentence;
				parselet = mParselets.get("neither");
				System.out
						.println("Getting the value associated with the neither key"); // !
			} else if (matcher0.find()) {
				text = sentence;
				parselet = mParselets.get("name");
				System.out
						.println("Getting the value associated with the name key");
			} else if (sentence.contains("and")) {
				text = sentence;
				parselet = mParselets.get("conjunction");
				System.out
						.println("Getting the value associated with the conjunction key");
			} else {
				text = sentence;
				parselet = mParselets.get("cleanup");
				System.out
						.println("Getting the value associated with the cleanup key");

				/*
				 * System.out.println("Unable to find the correct parselet.");
				 * sentenceArrFinal.add(sentence);
				 * System.out.println("HERE ARE ALL THE FINAL RULES: " +
				 * sentenceArrFinal);
				 */
			}
			Token t = new Token(text, parselet);
			allTokens.add(t);
		}
		sentenceArr.removeAll(sentenceArr);
		return allTokens;
	}

	public void parseInAccordanceWithParselet(ArrayList<Token> allTokens) {
		for (Token t : allTokens) {
			segment(t);
		}
		allTokens.removeAll(allTokens);
	}

	public void segment(Token token) {

		String text = token.getText();
		Parselet parselet = token.getParselet();

		Parselet a = parselet;
		String newString = a.parse(text);

		if (a.getClass() == CodeCleanUpParselet.class) {
			sentenceArrFinal.add(newString);
		} else {
			sentenceArr.add(newString);
		}
	}

	public boolean allParsingIsComplete() {
		return (createTokens(sentenceArr).isEmpty());
	}

	public void initializemParselets() {
		mParselets.put("if", new ConditionalParselet());
		mParselets.put("name", new NameParselet());
		mParselets.put("either", new EitherOrParselet());
		mParselets.put("conjunction", new ConjunctionParselet());
		mParselets.put("cleanup", new CodeCleanUpParselet());
	}

	private HashMap<String, Parselet> mParselets = new HashMap<String, Parselet>();
	public ArrayList<String> sentenceArr = new ArrayList<String>();
	private ArrayList<Token> allTokens = new ArrayList<Token>();
	private ArrayList<String> sentenceArrFinal = new ArrayList<String>();
}

