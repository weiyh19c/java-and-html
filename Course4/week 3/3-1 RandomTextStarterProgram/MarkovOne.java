
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovOne {
    private String myText;
    private Random myRandom;
    
    public MarkovOne() {
    	myRandom = new Random();
    }
    
    public void setRandom(int seed){
    	myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
    	myText = s.trim();
    }
    
    public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int i = 0;
        while (i < myText.length() - key.length()) {
            int index = myText.indexOf(key, i);
            if (index == -1) {
                break;
            }
            int pos = index + key.length();
            if (pos > myText.length() - 1) {
                break;
            }
            follows.add(myText.substring(pos, pos + 1));
            i = index + 1;
        }
        return follows;
    }
    
    public String getRandomText(int numChars){
    	StringBuilder sb = new StringBuilder();
    	int index = myRandom.nextInt(myText.length()-1);
    	String key = myText.substring(index, index+1);
    	sb.append(key);
    	for(int k=0; k < numChars - 1; k++){
    	    ArrayList<String> list = getFollows(key);
    	    if (list.size() == 0) {
    	        break;
    	    }
    	    index = myRandom.nextInt(list.size());
    	    String next = list.get(index);
    	    sb.append(next);
    	    key = next;
    	}
    	return sb.toString();
    }
}
