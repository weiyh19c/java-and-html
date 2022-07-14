
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import edu.duke.*;

public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts) {
        for (String s : resource.words()) {
            int length = s.length();
            if (!Character.isLetter(s.charAt(0))) {
                length -= 1;
            }
            if (length != 0) {
                if (!Character.isLetter(s.charAt(length-1))) {
                    length -= 1;
                }
                if (length >= counts.length) {
                    counts[counts.length - 1] += 1;
                }
                else {
                    counts[length] += 1;
                }
            }
            else {
                counts[0] += 1;
            }
        }
    }
    
    public void testCountWordLengths() {
        FileResource resource = new FileResource();
        int[] counts = new int[31];
        countWordLengths(resource, counts);
        for (int i = 0; i < counts.length; i++) {
            System.out.println("Length of " +  i + ": " + counts[i]);
        }
        System.out.println("Most frequent length is "+ indexOfMax(counts));
    }
    
    public int indexOfMax(int[] values) {
        int max = 0;
        int indexMax = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > max) {
                max = values[i];
                indexMax = i;
            }
        }
        return indexMax;
    }
}
