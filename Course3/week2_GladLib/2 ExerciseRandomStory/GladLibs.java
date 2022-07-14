
/**
 * 11.9 updated
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.*;
import edu.duke.*;

public class GladLibs {
    private HashMap<String, Integer> map;
    private ArrayList<String> commonCodon;
    private Random myRandom;
    private int numTotal;
    private int numMax;
    
    public GladLibs() {
        map = new HashMap<String, Integer>();
        commonCodon = new ArrayList<String>();
        myRandom = new Random();
    }
    
    public void buildCodonMap(int start, String dna) {
        map.clear();
        for (int i = start; i < dna.length()-3; i+=3) {
            String codon = dna.substring(i, i+3);
            if (!map.containsKey(codon)) {
                map.put(codon, 1);
            }
            else {
                map.put(codon, map.get(codon)+1);
            }
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    public String getMostCommonCodon() {
        numMax = 0;
        commonCodon.clear();
        for (String s : map.keySet()) {
           if (map.get(s) > numMax) {
               numMax = map.get(s);
            }
        }
        for (String s : map.keySet()) {
           if (map.get(s) == numMax) {
               commonCodon.add(s);
            }
        }
        return randomFrom(commonCodon);
    }
    
    public void printCodonCounts(int start, int end) {
        for (String s : map.keySet()) {
            if (map.get(s) >= start && map.get(s) <= end) {
                System.out.println(s + ": " + map.get(s));
            }
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase();
        dna = dna.trim();
        for (int i = 0; i < 3; i++) {
            map.clear();
            numTotal = 0;
            buildCodonMap(i, dna);
            for (String s : map.keySet()) {
                numTotal += 1;
            }
            System.out.println("start num: " + i);
            System.out.println("number of codons is " + numTotal);
            String common = getMostCommonCodon();
            System.out.println("most common codon " + common + 
                    " appears " + numMax + " times");
            printCodonCounts(7,7);
        }
        ///printCodonCounts(1, 5);
        //buildCodonMap(1, dna);
        //printCodonCounts(6, 6);
    }
}
