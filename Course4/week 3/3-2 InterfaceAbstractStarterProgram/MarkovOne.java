
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovOne extends AbstractMarkovModel {
    
    public MarkovOne() {
    	myRandom = new Random();
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
    
    public String toString(){
        return "MarkovModel of order 1";
    }
}
