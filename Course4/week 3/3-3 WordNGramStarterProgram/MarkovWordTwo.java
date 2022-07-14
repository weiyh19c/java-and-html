
/**
 * Write a description of MarkovWordTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
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
        int index = myRandom.nextInt(myText.length-2);  // random word to start with
        String key1 = myText[index];
        sb.append(key1);
        sb.append(" ");
        String key2 = myText[index + 1];
        sb.append(key2);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key1, key2);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            //System.out.println(key + " is followed by " + next);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length - 2) {
            int start = indexOf(myText, key1, key2, pos);
            if (start == -1) {
                break;
            }
            if ((start + 2) >= myText.length) {
                break;
            }
            String next = myText[start+2];
            follows.add(next);
            pos = start + 1;
        }
        return follows;
    }
    
    private int indexOf(String[] words, String target1, String target2, int start) {
        for (int i = start; i < words.length - 1; i++) {
            if (target1.equals(words[i])) {
                if (target2.equals(words[i+1])) {
                    return i;
                }
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
