
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class TestCaesarCipher {
    public void countWordLengths(FileResource resource, int[] counts) {
        for (String s : resource.words()) {
            int length = s.length();
            if (!Character.isLetter(s.charAt(0))) {
                length -= 1;
            }
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
    }
    
    public int indexOfMax(int[] values) {
        int max = 0;
        int indexMax = 0;
        for (int i = 1; i < values.length; i++) {
            if (values[i] > max) {
                max = values[i];
                indexMax = i;
            }
        }
        return indexMax;
    }
}
