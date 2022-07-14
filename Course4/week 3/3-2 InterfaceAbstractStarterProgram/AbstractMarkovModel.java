
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    public void setRandom(int seed){
    	myRandom = new Random(seed);
    }
 
    abstract public String getRandomText(int numChars);
    
    protected ArrayList<String> getFollows(String key) {
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
    
}
