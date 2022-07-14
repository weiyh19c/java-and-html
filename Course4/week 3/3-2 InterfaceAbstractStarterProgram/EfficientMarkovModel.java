
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class EfficientMarkovModel extends AbstractMarkovModel{
    private int num;
    private HashMap<String, ArrayList<String>> keysMap;
    
    public EfficientMarkovModel(int n) {
    	myRandom = new Random();
    	num = n;
    }

    public String getRandomText(int numChars){
    	StringBuilder sb = new StringBuilder();
    	keysMap = buildMap();
    	printHashMapInfo();
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
    
    public ArrayList<String> getFollows(String key) {
        ArrayList<String> list = new ArrayList<String>();
        if (keysMap.containsKey(key)) {
            return keysMap.get(key);
        }
        return list;
    }
        
    public void printHashMapInfo() {
        String maxKey = "";
        int size = 0;
        for (String s: keysMap.keySet()) {
            //System.out.println("key: [" + s + "] " + keysMap.get(s).size() + " values");
            if (keysMap.get(s).size() > size) {
                maxKey = s;
                size = keysMap.get(s).size();
            }
        }
        System.out.println(keysMap.size());
        System.out.println(maxKey + " has maximum value " + keysMap.get(maxKey).size());
    }
    
    public HashMap<String, ArrayList<String>> buildMap() {
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        for (int i = 0; i < myText.length() - num; i++) {
            int pos = i + num;
            String key = myText.substring(i, pos);
            String c = myText.substring(pos, pos + 1);
            if (map.containsKey(key)) {
                map.get(key).add(c);
            }
            else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(c);
                map.put(key, list);
            }
        }
        String end = myText.substring(myText.length()-num);
        if (!map.containsKey(end)) {
            ArrayList<String> list = new ArrayList<String>();
            map.put(end, list);
        }
        return map;
    }
    
    public String toString(){
        return "EfficientMarkovModel of order " + num;
    }
}
