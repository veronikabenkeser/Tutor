import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;


public class AutomaticRulesWriter {	
	//Parser.getAutomaticRules();
	
	//printRulesToFile();
	
	//System.out.println(new File(".").getAbsolutePath());
	public void printRulesToFile(){
			File file = new File("C:\\Users\\Robert\\Desktop\\Veronika's Folder\\my cognitive tutor\\workspace\\Main\\src\\AutomaticRules.java");
			PrintWriter pw = null;
			
			try{
				pw = new PrintWriter(file);
				pw.println(
				"\n public class AutomaticRules(){"
				+ "\n"
				+ "\n public boolean getIsValid(final ArrayList<Integer> nodes){"
				+ "\n return isValid(nodes);"
				+ "\n }"
				+ "\n public final ArrayList<Integer> getNodes(){"
				+ "\n for (int i=0; i<names.length; i++){"
				+ "\n nodes.add(i);"
				+ "\n }"
				+ "\n return nodes;"
				+ "\n }"
				+ "\n public final String[] getNames(){"
				+ "\n return names;"
				+ "\n }"		
				+"\n public HashMap<Integer, String> getLetterIndex(){"
				+ "\n for (int i=0; i<names.length; i++){"
				+ "\n letterIndex.put(i, names[i]);"
				+ "\n }"
				+ "\n return letterIndex;"
				+ "\n }"
				+ "\n private final ArrayList<Integer> nodes = new ArrayList<Integer>();"
				+ "private HashMap<Integer, String> letterIndex = new HashMap<Integer, String>();"
				+ "}"	
				
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						);
				pw.flush();
				//System.out.println(pw.checkError());
			} catch (FileNotFoundException e){
				e.printStackTrace();
			} finally {
				if (pw !=null){
					pw.close();
				}
			}
	}
}