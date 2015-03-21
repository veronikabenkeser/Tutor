import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * This class reads a given text file--as long as it is formatted the same way-- and modifies it.
 * The class also extracts players' names and calculates the number of players from the text.
 */

public class Reader {
	private static final int SI = 9;
	private String a;
	private File file;
	private String fullText;
	private String prompt = "";
	private String question = "";
	private String newText = "";
	
	String[] arr= { 
			"one", "two", "three",
			"four", "five", "six",
			"seven", "eight", "nine",
			"ten", "first", "second", "third",
			"fourth", "fifth", "sixth", "seventh",
			
	};
	
	public void readProblem(String a) {

		BufferedReader rd = null;

		try {
			//String b= ".txt";
			//a+=b;
			file = new File(a);
			rd = new BufferedReader(new FileReader(a));
			StringBuilder sb = new StringBuilder();
		
			while (true) {
			
					String line = rd.readLine();
					if (line == null) break;
					sb.append(line +"\n");
				    fullText = sb.toString();
						
				
			}	
			
			allParticipants.removeAll(allParticipants);
			players.removeAll(players);

			if(fullText.contains("QUESTIONS")) getParticipants();
			//checkForErrors();
			
			
		}catch (FileNotFoundException e) {
			System.out.println("File not found: " + file.toString());
			
		} catch (IOException e) {
			System.out.println("Unable to read file: " + file.toString());
			
		} finally {
			
			try {
				rd.close();
				
			} catch (IOException e) {
				
				e.printStackTrace();
				System.out.println("Unable to close file: " + file.toString());
				
			} catch (NullPointerException ex) {
			}
		}
	}

	public void setFile(String a) { //TODO still need this ?? ? ?
		this.a = a;
	}
	
	public String showPrompt(){
		int index = fullText.indexOf("QUESTIONS");
		prompt = fullText.substring(0, index);
		return prompt;
	}
	
	public String showQuestion(){
		int index = fullText.indexOf("QUESTIONS")+SI;
		question = fullText.substring(index);
		return question;
	}
	
	private void namesOfPlayers(){
		String text = showPrompt();
		Pattern participants;
		int endPoint =0;
		participants = Pattern.compile("([A-Z]+[^,\\s]+[,])");
		Matcher m1 = participants.matcher(text);
		
		while(m1.find()){
			String result = m1.group();
			players.add(result);
			endPoint = m1.end();
		}
		String strLeft = text.substring(endPoint, text.length());
		Pattern pattern2= Pattern.compile("[A-Z]+[a-z]*");
		Matcher m2 = pattern2.matcher(strLeft);
		m2.find();
		String lastPlayer = m2.group();
		players.add(lastPlayer);
		
		System.out.println("players: " + players);
	}
	
	private void getParticipants(){		
		namesOfPlayers();
		for(int i=0; i < players.size(); i++){
			char ch = (players.get(i).charAt(0));
			allParticipants.add(i, Character.toString(ch));
		}
		System.out.println(allParticipants);	
	}
	
	public String getNewText(){
		String text = showPrompt();
		return convertStrToNums(text);
	}
	
	private void checkpoint1(String newText){
		//String newText = getNewText();
		//String result = "";
		if (!totalIsCorrect(newText)){
			//result = findNoun(newText);
			System.out.println("Could not confirm the number of participants.");
		}
	}
	/*
	
	public void checkForErrors(){
		checkpoint1(getNewText());
	}
	*/
	/*
	public String getNoun(){
		String newText = getNewText();
		String result = "";
		if (totalIsCorrect(newText)){
			result = findNoun(newText);
		} else {
			System.out.println("Could not confirm the number of participants.");
		}
		return result;
	}
	*/

	
	private String convertStrToNums(String text){
		//splitting into words and punctuation
		String [] words = text.split("\\b");
		for (int i=0; i<words.length; i++){
			for(String ss: arr){
				if (words[i].toLowerCase().equals(ss)){
					words[i] = numStrToInt(ss).toString();	
				}
			}
			
		}		
		
		StringBuilder sb = new StringBuilder();
		for (String s: words){
			sb.append(s);
			sb.append(" ");
		}
		newText = sb.toString();
		System.out.println("NEWTEXT" + newText);
		return newText;
	}
	/*
	private String findNoun(String newText){
		int index=-1; 
		for(int i=0; i<newText.length();i++){
			if(newText.contains("-")){
				index = newText.indexOf("-");
			} else if (newText.contains("--")){
				index = newText.indexOf("--");
			} else if (newText.contains("�")){
				index = newText.indexOf("�");
			} else {
				index=-1;
			}
		}
		
		if(!(index==-1)){
			String subStr = newText.substring(0, index);
			String [] words2 = subStr.split(" ");
			noun = words2[words2.length-1];
		}
		
		String result2 ="";
		for(int q=0; q<noun.length()-1; q++){
			result2 += noun.charAt(q);
		}
		
		noun=result2;
		
		System.out.println("NOUN: " + noun);
		return noun; 
	}

	*/

	private Integer numStrToInt(String numStr){
		hm.put("one", 1);
		hm.put("first", 1);
		hm.put("two", 2);
		hm.put("second", 2);
		hm.put("three", 3);
		hm.put("third", 3);
		hm.put("four", 4);
		hm.put("fourth", 4);
		hm.put("five", 5);
		hm.put("fifth", 5);
		hm.put("six", 6);
		hm.put("sixth", 6);
		hm.put("seven", 7);
		hm.put("seventh", 7);
		return hm.get(numStr);
		
		
		
	}
		
	

	
	
	/*
	 * This method finds the first number in the text and compares it to the size of the allParticipants array.
	 * The first digit in the prompt text must be the total number of players.
	 */

	
	private boolean totalIsCorrect (String newText){
		getParticipants();
		
		boolean correct = false;
		Pattern participantsNum;
		participantsNum = Pattern.compile("\\d");
		Matcher m1 = participantsNum.matcher(newText);
		m1.find();
		int result = Integer.parseInt(m1.group());
	
		if (result == allParticipants.size()){
			correct = true;
		}
		
		return correct;
	}
	
	/*Private instance variables*/
	
	private ArrayList<String> players = new ArrayList<String>();
	private ArrayList<String> allParticipants = new ArrayList<String>();
	private HashMap<String , Integer> hm = new HashMap<String , Integer>();	 
}
