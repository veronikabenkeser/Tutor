import java.util.ArrayList;
import java.util.HashMap;

public interface Answer {
	public void eval(HashMap<String, String[]> map, ArrayList<String[]> allSolutions);
}