
/**
 * To count the words in Macbeth
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
import java.io.*;

public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> counts;
    
    public CharactersInPlay() {
        names = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }
    
    public void update(String person) {
        int index = names.indexOf(person);
        if (index == -1) {
            names.add(person);
            counts.add(1);
        }
        else {
            int value = counts.get(index);
            counts.set(index, value + 1);
        }
    }
    
    public void findAllCharacters() {
        names.clear();
        counts.clear();
        FileResource source = new FileResource();
        for (String line : source.lines()) {
            int indexPeriod = line.indexOf(".");
            if (indexPeriod != -1) {
                String s = line.substring(0, indexPeriod);
                update(s);
            }
        }
    }
    
    public void tester() {
        findAllCharacters();
        int size = names.size();
        for (int i = 0; i < size; i++) {
            if (counts.get(i) > 3) {
                System.out.println(names.get(i) + ": " + counts.get(i));
            }
        }
        System.out.println(" ");
        charactersWithNumParts(10, 15);
    }
    
    public void charactersWithNumParts(int num1, int num2) {
        int size = names.size();
        for (int i = 0; i < size; i++) {
            if (counts.get(i) >= num1 && counts.get(i) <= num2) {
                System.out.println(names.get(i) + ": " + counts.get(i));
            }
        }
    }
}
