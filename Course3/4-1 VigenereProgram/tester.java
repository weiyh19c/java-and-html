
/**
 * Write a description of tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.*;
import edu.duke.*;
import java.util.*;

public class tester {
    public void testCaesarCipher() {
        CaesarCipher cipher = new CaesarCipher(3);
        FileResource fr = new FileResource();
        String words = fr.asString();
        System.out.println(cipher.encrypt(words));
    }
    
    public void testBreaker1() {
        VigenereBreaker vb = new VigenereBreaker();
        System.out.println(vb.sliceString("abcdefghijklm", 0, 3));
        System.out.println(vb.sliceString("abcdefghijklm", 1, 3));
        System.out.println(vb.sliceString("abcdefghijklm", 2, 3));
        System.out.println(vb.sliceString("abcdefghijklm", 0, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 1, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 2, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 3, 4));
    }
    
    public void testBreaker2() {
        FileResource fr = new FileResource();
        String words = fr.asString();
        VigenereBreaker vb = new VigenereBreaker();
        int[] key = vb.tryKeyLength(words, 4, 'e');
        for (int i = 0; i < key.length; i++) {
            System.out.println(key[i]);
        }
    }
    
    public void testBreaker3() {
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();
    }
    
    public void testDebug() {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource();
        HashSet<String> set = vb.readDictionary(fr);
        //System.out.println(set.contains("On") + "");
        // find the bug: the transaction of upper/lower case
        //String word = "On?on on%On";
        //System.out.println(vb.countWords(word, set));
        FileResource wordFr = new FileResource();
        String words = wordFr.asString();
        int[] key = vb.tryKeyLength(words, 38, 'e');
        VigenereCipher vc = new VigenereCipher(key);
        String answer = vc.decrypt(words); 
        System.out.println(vb.countWords(answer, set));
    }
}
