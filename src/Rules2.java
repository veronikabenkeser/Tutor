import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Rules2 {
	String[] names = { "G", "J", "L", "M", "P", "V", };

	/*
	 * We have a fixed index to hold a particular value.
	 */
	private static final int G = 0; // G is always at index 0
	private static final int J = 1; // J is always at index 1
	private static final int L = 2;
	private static final int M = 3;
	private static final int P = 4;
	private static final int V = 5;

	final boolean rule1(final ArrayList<Integer> nodes) {
		return nodes.get(P) < nodes.get(M);
	}

	final boolean rule2(final ArrayList<Integer> nodes) {
		return nodes.get(M) < nodes.get(L);
	}

	final boolean rule3(final ArrayList<Integer> nodes) {
		return ((nodes.get(G) < nodes.get(J) && !(nodes.get(G) > nodes.get(L))) || (nodes.get(G) > nodes.get(L))
				&& !(nodes.get(G) < nodes.get(J)));
	}

	final boolean rule4(final ArrayList<Integer> nodes) {
		return ((nodes.get(V) < nodes.get(G) && !(nodes.get(V) > nodes.get(P))) || (nodes.get(V)> nodes.get(P))
				&& !(nodes.get(V)< nodes.get(G)));
	}

	final boolean isValid(final ArrayList<Integer> nodes) {
		return rule1(nodes) && rule2(nodes) && rule3(nodes) && rule4(nodes);
	}
	
	final ArrayList<Integer> getNodes(){
		for (int i=1; i<=names.length; i++){
			nodes.add(i);
		}
		return nodes;
	}
	/*Private instance variables */
	private final ArrayList<Integer> nodes = new ArrayList<Integer>();
}