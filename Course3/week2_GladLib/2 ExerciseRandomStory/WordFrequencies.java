
/**
 * Program 2: To tell a random story.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import edu.duke.*;
import java.util.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String> ();
        myFreqs = new ArrayList<Integer> ();
    }
    
    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource source = new FileResource();
        for (String s: source.words()) {
            int index = myWords.indexOf(s.toLowerCase());
            if (index == -1) {
                myWords.add(s.toLowerCase());
                myFreqs.add(1);
            }
            else {
                int currNum = myFreqs.get(index);
                myFreqs.set(index, currNum + 1);
            }
        }
    }
    
    public void tester() {
        findUnique();
        int size = myWords.size();
        int maxIndex = findIndexOfMax();
        System.out.println("Number of unique words: " + size);
        //for  (int i = 0; i < size; i++) {
            //System.out.println(myFreqs.get(i) + " " + myWords.get(i));
        //}
        System.out.println("The word that occurs most often and its count are: " +
                            myWords.get(maxIndex) + " " + myFreqs.get(maxIndex));
    }
    
    public int findIndexOfMax() {
        int maxIndex = -1;
        int max = 0;
        for (int i = 0; i < myWords.size(); i++) {
            if (myFreqs.get(i) > max) {
                maxIndex = i;
                max = myFreqs.get(i);
            }
        }
        return maxIndex;
    }
}
