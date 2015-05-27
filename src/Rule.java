import java.util.ArrayList;

public interface Rule {
	public boolean evaluate(ArrayList<Integer> nodes);
	public boolean eval(ArrayList<String> scenarios);
}