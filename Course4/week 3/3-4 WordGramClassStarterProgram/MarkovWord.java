
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text) {
        myText = text.split("\\s+");
    }
    
    private int indexOf(String[] words, WordGram target, int start) {
        int size = target.length();
        for (int i = start; i < words.length - size + 1; i++) {
            WordGram test = new WordGram(words, i, size);
            if (target.equals(test)){
                return i;
            }
        }
        return -1;
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int size = kGram.length();
        int pos = 0;
        while(pos < myText.length - size) {
            int start = indexOf(myText, kGram, pos);
            if (start == -1) {
                break;
            }
            if ((start + size) >= myText.length) {
                break;
            }
            String word = myText[start + size];
            follows.add(word);
            pos = start + 1;
        }
        return follows;
    }
    
    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder);
        WordGram wg = new WordGram(myText, index, myOrder);
        sb.append(wg.toString());
        sb.append(" ");
        for (int i = 0; i < numWords - myOrder; i++) {
            ArrayList<String> follows = getFollows(wg);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String word = follows.get(index);
            sb.append(word);
            sb.append(" ");
            wg = wg.shiftAdd(word);
        }
        return sb.toString().trim();
    }
    
    public String toString() {
        return "MarkoWord of order " + myOrder;
    }
}
