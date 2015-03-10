import java.util.ArrayList;
import java.util.HashMap;

public class Rules {
	 private String[] names = { "G", "J", "L", "M", "P", "V", };

	/*
	 * We have a fixed index to hold a particular value.
	 */
	private static final int G = 0; // G is always at index 0
	private static final int J = 1; // J is always at index 1
	private static final int L = 2;
	private static final int M = 3;
	private static final int P = 4;
	private static final int V = 5;

	private final boolean rule1(final ArrayList<Integer> nodes) {
		return ((nodes.get(V) < nodes.get(G)) && (nodes.get(G) < nodes.get(J)) && (nodes.get(J) <nodes.get(P)) && (nodes.get(P)<nodes.get(M)) &&
				(nodes.get(M) <nodes.get(L)));
	}

	private final boolean rule2(final ArrayList<Integer> nodes) {
		return ((nodes.get(V) < nodes.get(G)) && (nodes.get(G) < nodes.get(J)) && (nodes.get(J) <nodes.get(P)) && (nodes.get(P)<nodes.get(M)) &&
				(nodes.get(M) <nodes.get(L)));
	}

	private final boolean rule3(final ArrayList<Integer> nodes) {
		return ((nodes.get(V) < nodes.get(G)) && (nodes.get(G) < nodes.get(J)) && (nodes.get(J) <nodes.get(P)) && (nodes.get(P)<nodes.get(M)) &&
				(nodes.get(M) <nodes.get(L)));
	}

	private final boolean rule4(final ArrayList<Integer> nodes) {
		return ((nodes.get(V) < nodes.get(G)) && (nodes.get(G) < nodes.get(J)) && (nodes.get(J) <nodes.get(P)) && (nodes.get(P)<nodes.get(M)) &&
				(nodes.get(M) <nodes.get(L)));
	}

	 private final boolean isValid(final ArrayList<Integer> nodes) {
		return rule1(nodes) && rule2(nodes) && rule3(nodes) && rule4(nodes);
	}
	 
	 public boolean getIsValid(final ArrayList<Integer> nodes){
			return isValid(nodes); 
	 }
	 
	public final ArrayList<Integer> getNodes(){
		for (int i=0; i<names.length; i++){
			nodes.add(i);
		}
		return nodes;
	}
	
	public final String[] getNames(){
		return names;
	}
	
	public HashMap<Integer, String> getLetterIndex(){
		for (int i=0; i<names.length; i++){
			letterIndex.put(i, names[i]);
		}
		return letterIndex;
	}
	/*Private instance variables */
	private final ArrayList<Integer> nodes = new ArrayList<Integer>();
	private HashMap<Integer, String> letterIndex = new HashMap<Integer, String>();
}