
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovModel extends AbstractMarkovModel{
    private int num;
    
    public MarkovModel(int n) {
    	myRandom = new Random();
    	num = n;
    }

    public String getRandomText(int numChars){
    	StringBuilder sb = new StringBuilder();
    	int index = myRandom.nextInt(myText.length()-num);
    	String key = myText.substring(index, index+num);
    	sb.append(key);
    	for(int k=0; k < numChars - num; k++){
    	    ArrayList<String> list = getFollows(key);
    	    if (list.size() == 0) {
    	        break;
    	    }
    	    index = myRandom.nextInt(list.size());
    	    String next = list.get(index);
    	    sb.append(next);
    	    key = key.substring(1) + next;
    	}
    	return sb.toString();
    }
    
    public String toString(){
        return "MarkovModel of order " + num;
    }
}
