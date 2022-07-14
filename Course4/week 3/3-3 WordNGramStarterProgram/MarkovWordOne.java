
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            //System.out.println(key + " is followed by " + next);
            sb.append(next);
            sb.append(" ");
            key = next;
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length - 1) {
            int start = indexOf(myText, key, pos);
            if (start == -1) {
                break;
            }
            if ((start + 1) >= myText.length) {
                break;
            }
            String next = myText[start+1];
            follows.add(next);
            pos = start + 1;
        }
        return follows;
    }
    
    private int indexOf(String[] words, String target, int start) {
        for (int i = start; i < words.length; i++) {
            if (target.equals(words[i])) {
                return i;
            }
        }
        return -1;
    }
    
    public void testIndexOf() {
        String[] words = "this is just a test yes this is a simple test".split("\\s+");
        //System.out.println(indexOf(words, "frog", 0));
        myText = words;
        getRandomText(5);
    }
}
