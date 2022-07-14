import java.util.*;
import edu.duke.*;
import java.io.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private Random myRandom;
    private ArrayList<String> labelConsidered;
    
    public GladLibMap() {
        myMap = new HashMap<String, ArrayList<String>>();
        myRandom = new Random();
        labelConsidered = new ArrayList<String>();
        initializeFromSource("datalong");
    }
    
    private void initializeFromSource(String source) {
        String[] labels = {"adjective", "noun", "color", "country", "name", 
                            "animal",  "timeframe", "verb",  "fruit"};
        for (String s : labels) {
            ArrayList<String> list = readIt(source+"/"+s+".txt");
            if (!myMap.containsKey(s)) {
                myMap.put(s, list);
            }
        }
    }
    
    private String randomFrom(ArrayList<String> source){ 
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if ("number".equals(label)) {
            return ""+myRandom.nextInt(50)+5;
        }
        if (myMap.keySet().contains(label)) {
            return randomFrom(myMap.get(label));
        }
        return "**UNKNOWN**";
    }
    
    private String processWord(String w) {
        int first = w.indexOf("<");
        int last = w.indexOf(">");
        if (first == -1 || last == -1) {
            return w;
        }
        String prefix = w.substring(0, first);
        String suffix = w.substring(last+1);
        String label = w.substring(first+1, last);
        String sub = getSubstitute(label);
        if (labelConsidered.indexOf(label) == -1) {
            labelConsidered.add(label);
        }
        while (true) {
            if (w.indexOf(sub) == -1) {
                break;
            }
            sub = getSubstitute(w.substring(first+1, last));
        }
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth) {
        int charsWritten = 0;
        for (String w : s.split("\\s+")) {
            if (charsWritten + w.length() > lineWidth) {
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source) {
        String story = "";
        FileResource fr = new FileResource(source);
        for (String word : fr.words()) {
            story = story + processWord(word) + " ";
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source) {
        ArrayList<String> list = new ArrayList<String>();
        FileResource fr = new FileResource(source);
        for (String line : fr.lines()) {
            list.add(line);
        }
        return list;
    }
    
    public void makeStory() {
        labelConsidered.clear();
        String story = fromTemplate("datalong/madtemplate3.txt");
        printOut(story, 60);
        System.out.println("number of words in map: " + totalWordsInMap());
        System.out.println("words considered: " + totalWordsConsidered());
    }
    
    private int totalWordsInMap() {
        int totalNum = 0;
        for (String s : myMap.keySet()) {
            totalNum += myMap.get(s).size();
        }
        return totalNum;
    }
    
    private int totalWordsConsidered() {
        int totalNum = 0;
        for (int i = 0; i < labelConsidered.size(); i++) {
            String label = labelConsidered.get(i);
            totalNum += myMap.get(label).size();
        }
        return totalNum;
    }
}