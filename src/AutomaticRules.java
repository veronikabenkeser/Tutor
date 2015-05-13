
 import java.util.ArrayList;
 import java.util.HashMap;
 public class AutomaticRules{
private static final int T= 0;
private static final int U= 1;
private static final int V= 2;
private static final int W= 3;
private static final int X= 4;
private static final int Y= 5;
private static final int Z= 6;


 public boolean getIsValid(final ArrayList<Integer> nodes){
 return isValid(nodes);
 }
 private boolean rule0 ( final ArrayList<Integer> nodes) {
 return (nodes.get(X) != 1);
 }
 private boolean rule1( final ArrayList<Integer> nodes) {
 boolean result = false; 
if(   nodes.get(T) != 1){ return (  nodes.get(U) != 2  &&   nodes.get(W) == 3); 
}
 return result;
 } 
 private boolean rule2( final ArrayList<Integer> nodes) {
 boolean result = false; 
if(   nodes.get(U) != 6  &&   nodes.get(W) == 3){ return (   nodes.get(T) != 1); 
}
 return result;
 } 
 
 boolean isValid(final ArrayList<Integer> nodes) {
 return (rule0(nodes) && rule1(nodes) && rule2(nodes));
 }

 private final ArrayList<Integer> nodes = new ArrayList<Integer>();
 }
