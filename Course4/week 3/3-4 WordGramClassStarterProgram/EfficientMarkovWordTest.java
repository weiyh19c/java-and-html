
/**
 * Write a description of EfficientMarkovWordTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class EfficientMarkovWordTest implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<String, ArrayList<String>> myMap;
    
    public EfficientMarkovWordTest(int order) {
        myOrder = order;
        myRandom = new Random();
        myMap = new HashMap<String, ArrayList<String>>();
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
        String key = kGram.toString();
        if (myMap.containsKey(key)) {
            return myMap.get(key);
        }
        return follows;
    }
    
    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        myMap = buildMap();
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
    
    private HashMap<String, ArrayList<String>> buildMap() {
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        for (int i = 0; i < myText.length - myOrder; i++) {
            WordGram wg = new WordGram(myText, i, myOrder);
            String key = wg.toString();
            String newWord = myText[i + myOrder];
            ArrayList<String> follows = new ArrayList<String>();
            if (!map.containsKey(key)) {
                follows.add(newWord);
            }
            else {
                follows = map.get(key);
                follows.add(newWord);
            }
            map.put(key, follows);
        }
        WordGram last = new WordGram(myText, myText.length-myOrder, myOrder);
        String key = last.toString();
        System.out.println(key);
        if (!map.containsKey(key)) {
            ArrayList<String> follows = new ArrayList<String>();
            map.put(key, follows);
        }
        //printHashMapInfo();
        return map;
    }
    
    public String toString() {
        return "Efficient MarkoWord of order " + myOrder;
    }
    
    public void printHashMapInfo() {
        int sizeMax = 0;
        String codeMax = "";
        /*for(String key : myMap.keySet()) {
            System.out.println("code [" + key + "], list: " + myMap.get(key));
            if (myMap.get(key).size() > sizeMax) {
                sizeMax = myMap.get(key).size();
                codeMax = key;
            }
        }*/
        System.out.println("size of map: " + myMap.size());
        System.out.println("largest value is " + sizeMax + " with key " + codeMax);
    }
}

