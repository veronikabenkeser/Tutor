import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
 * The code for permutations with letters has been adopted from http://stackoverflow.com/questions/4845674/
 * how-do-i-write-a-program-to-solve-multiple-questions and https://ideone.com/wwxG0
 */
public class Permutations {
     private boolean perms = true;

     private int nodes;

     public boolean hasPerms() {
         return perms;
     }
     
     public Permutations(ArrayList<Integer> nodes){
    	 arrToTest = nodes;
     }
     
     public ArrayList<Integer> getNodes(){
    	 return arrToTest;
     }

     /*
     public ArrayList<Integer>getArrToTest(){
    	 arrToTest = ar.setNodes();
    	 return arrToTest;
     }
     */
     //public Permutations(ArrayList<Integer> arrToTest) {
     //	this.arrToTest=arrToTest;
    // }

     /*
      * This method calculates the next Permutation. For example, if our initial list is 
      * 0, 1, 2, 3, 4, it becomes 0, 1, 2, 4, 3.
      * We cannot do permutations if we are working with less than 2 digits. In other words, there are 
      * no permutations we can do if the number of things in the array-2< 0.
      */
     ArrayList<Integer> nextPerm() {
         int temp;
         int j = arrToTest.size()- 2;
         while (arrToTest.get(j)> arrToTest.get(j + 1)) {
        	 j--;
        	 if (j < 0) {
        		 perms = false;
        		 break;
        	 }
         }
         if (perms) {
	         int k = arrToTest.size() - 1;
	         while (arrToTest.get(j)> arrToTest.get(k)) {
	        	 k--;
	         }
	         temp = arrToTest.get(k);
	         arrToTest.set(k, arrToTest.get(j));
	         arrToTest.set(j, temp);
	         int r = arrToTest.size() - 1;
	         int s = j + 1;
	         
	         while (r > s) {
		         temp = arrToTest.get(s);
		         arrToTest.set(s, arrToTest.get(r));
		         arrToTest.set(r, temp);
		         r--;
		         s++;
	         }
         }
         return arrToTest;
     }
     
    

 //String[] arrS = new String[6];
 //Iterator<String> it = arr.iterator();

 /*
 private ArrayList<String> getArrFromMap(){
 	ArrayList<String> arrUpdated= new ArrayList<String>();
 	for (int i=1; i<=names.length; i++){
 		arrUpdated.add(myMap.get(i));
 		variations.addAll(arrUpdated);
 	}
 	return arrUpdated;	    	
 }
 
 private ArrayList<String>  getMBT(){
 	System.out.println(variations);
		return variations;

 }
 */
 
 /*Private instance variables*/
private HashMap<Integer, String> myMap = new HashMap<Integer, String>();
private  ArrayList<String> variations;
private ArrayList<Integer> arrToTest = new ArrayList<Integer>();

}
