
 import java.util.ArrayList;
 import java.util.HashMap;
 public class AutomaticRules{
private static final int T= 0;
private static final int U= 1;
private static final int V= 2;


 public boolean getIsValid(final ArrayList<Integer> nodes){
 return isValid(nodes);
 }
 private boolean rule0 ( final ArrayList<Integer> nodes) {
 return (nodes.get(T) == 2);
 }
  private boolean rule1 ( final ArrayList<Integer> nodes) {
 return ( nodes.get(U) != 3);
 }
 
 boolean isValid(final ArrayList<Integer> nodes) {
 return (rule0(nodes) && rule1(nodes));
 }

 private final ArrayList<Integer> nodes = new ArrayList<Integer>();
 }
