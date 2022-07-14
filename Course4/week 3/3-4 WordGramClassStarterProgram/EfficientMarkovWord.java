
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<Integer, ArrayList<String>> myMap;
    
    public EfficientMarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
        myMap = new HashMap<Integer, ArrayList<String>>();
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
        int code = kGram.hashCode();
        if (myMap.containsKey(code)) {
            follows = myMap.get(code);
        }
        return follows;
    }
    
    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        myMap = buildMap();
        printHashMapInfo();
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
    
    private HashMap<Integer, ArrayList<String>> buildMap() {
        HashMap<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();
        for (int i = 0; i < myText.length - myOrder; i++) {
            WordGram wg = new WordGram(myText, i, myOrder);
            int code = wg.hashCode();
            System.out.println(wg);
            String word = myText[i + myOrder];
            if (map.containsKey(code)) {
                map.get(code).add(word);
                /*ArrayList<String> list = map.get(code);
                list.add(word);
                map.put(code, list);*/
            }
            else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(word);
                map.put(code, list);
            }
        }
        WordGram endWG = new WordGram(myText, myText.length - myOrder, myOrder);
        int code = endWG.hashCode();
        System.out.println(endWG);
        if (!map.containsKey(code)) {
            ArrayList<String> list = new ArrayList<String>();
            map.put(code, list);
        }
        //printHashMapInfo();
        return map;
    }
    
    public String toString() {
        return "Efficient MarkoWord of order " + myOrder;
    }
    
    public void printHashMapInfo() {
        int sizeMax = 0;
        int codeMax = 0;
        for(int code : myMap.keySet()) {
            System.out.println("code [" + code + "], list: " + myMap.get(code));
            if (myMap.get(code).size() > sizeMax) {
                sizeMax = myMap.get(code).size();
                codeMax = code;
            }
        }
        System.out.println("size of map: " + myMap.size());
        System.out.println("largest value is " + sizeMax + " with key " + codeMax);
    }
}
