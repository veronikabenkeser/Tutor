import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Reader {

	public void read(){
		File file = new File("LG_07_G3.txt");
		BufferedReader rd = null;
		
		try{
			
			rd = new BufferedReader(new FileReader(file));
			
			while(true){
				String line =rd.readLine();
				if (line == null) break;
				StringBuilder sb = new StringBuilder();
				String text="";
				text=line;
				
				while(!line.equals("QUESTIONS") ){
					sb.append(text);
				}
				
				String prompt = sb.toString();
				System.out.println("PROMPT" + prompt);
			}
		 } catch (FileNotFoundException e) {
             System.out.println("File not found: " + file.toString());
         } catch (IOException e) {
             System.out.println("Unable to read file: " + file.toString());
         }
         finally {
             try {
                 rd.close();
             } catch (IOException e) {
            	 e.printStackTrace();
                 System.out.println("Unable to close file: " + file.toString());
             }
             catch(NullPointerException ex) {
             }
         }
	}
}
