import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class AutomaticRulesWriter {
	Parser parser;

	public AutomaticRulesWriter(ArrayList<String> allParticipants, Parser parser) {
		mAllParticipants = allParticipants;
		this.parser = parser;
	}

	// System.out.println(new File(".").getAbsolutePath());
	public AutomaticRules printRulesToFile() {
		AutomaticRules a = null;
		
		File file = new File(
				"C:\\Users\\Robert\\Desktop\\Veronika's Folder\\my cognitive tutor\\workspace\\Main\\src\\AutomaticRules.java");
		PrintWriter pw = null;

		try {
			pw = new PrintWriter(file);
			pw.println("\n import java.util.ArrayList;"
					+ "\n import java.util.HashMap;"
					+ "\n public class AutomaticRules{"
					+ "\n"
					+ assignedIndex()
					+ "\n"
					+ "\n public boolean getIsValid(final ArrayList<Integer> nodes){"
					+ "\n return isValid(nodes);"
					+ "\n }"
					+ "\n"
					+ setRules()
					+ "\n"
					+ followRules()
					+ "\n"
					+ "\n private final ArrayList<Integer> nodes = new ArrayList<Integer>();"
					+ "\n }");
			pw.flush();
			// System.out.println(pw.checkError());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
		a = new AutomaticRules();
		return a;
	}
/*
	public void setLetterIndex(){
		mLetterIndex = parser.setLetterIndex();
		System.out.println("LETTEr INDEX : " + mLetterIndex);
	}
*/
	private String assignedIndex() {
		String result = "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < mAllParticipants.size(); i++) {
			sb.append("private static final int " + mAllParticipants.get(i)
					+ "= " + i + ";");
			sb.append("\n");
		}
		result = sb.toString();
		return result;
	}

	private String followRules() {
		StringBuilder sb = new StringBuilder();
		sb.append("final boolean isValid(final ArrayList<Integer> nodes) {");
		sb.append("\n return (");
		for (int i = 0; i < mSentenceArrFinal.size(); i++) {

			if (i == mSentenceArrFinal.size() - 1) {
				sb.append("rule" + i + "(nodes));");
			} else {
				sb.append("rule" + i + "(nodes) && ");
			}
		}
		return sb.toString() + "\n }";
	}

	private String setRules() {
		mSentenceArrFinal = parser.setFinalRules();
		StringBuilder sb = new StringBuilder();
		for (String s : mSentenceArrFinal) {

			if (s.contains("if")) {
				sb.append("private final boolean rule");
				sb.append(mSentenceArrFinal.indexOf(s));
				sb.append("(final ArrayList<Integer> nodes) {");
				sb.append("\n boolean result = false; ");
				sb.append("\n");
				sb.append(s);
				sb.append("\n return result;");
				sb.append("\n }");
				sb.append(" \n ");
			} else {
				sb.append("private final boolean rule");
				sb.append(mSentenceArrFinal.indexOf(s));
				sb.append("(final ArrayList<Integer> nodes) {");
				sb.append("\n return (");
				sb.append(s);
				sb.append(");");
				sb.append("\n }");
				sb.append("\n ");
			}
		}
		return sb.toString();
	}

	private final ArrayList<Integer> nodes = new ArrayList<Integer>();
	private ArrayList<String> mAllParticipants = new ArrayList<String>();
	private ArrayList<String> mSentenceArrFinal = new ArrayList<String>();
}