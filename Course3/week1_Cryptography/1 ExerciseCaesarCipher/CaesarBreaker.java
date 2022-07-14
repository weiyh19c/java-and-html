
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarBreaker {
    public int[] countLetters(String words) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int i = 0; i < words.length(); i++) {
            char ch = words.charAt(i);
            int index = alphabet.indexOf(ch);
            if (index != -1) {
                counts[index] += 1;
            }
        }
        return counts;
    }
    
    public int maxIndex(int[] counts) {
        int maxIndex = -1;
        int maxCount = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > maxCount) {
                maxCount = counts[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    //public String decrypt(String encrypted) {
        //CaesarCipher cc = new CaesarCipher();
        //int[] counts = countLetters(encrypted);
        //int max = maxIndex(counts);
        //int key = max - 4;
        //if (max < 4) {
            //key = 4 - max;
        //}
        //String message = cc.encrypt(encrypted, 26 - key);
        //return message;
    //}
    
    public String decrypt(String encrypted, int key) {
        CaesarCipher cc = new CaesarCipher();
        String message = cc.encrypt(encrypted, 26 - key);
        return message;
    }
    
    public void testDecrypt() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        //System.out.println(decrypt(encrypted));
    }
    
    public String halfOfString(String message, int start) {
        String halfMessage = "";
        for (int i = start; i < message.length(); i += 2) {
            halfMessage += message.charAt(i);
        }
        return halfMessage;
    }
    
    public void testHalfOfString() {
        System.out.println(halfOfString("Qbkm Zgis", 0));
        System.out.println(halfOfString("Qbkm Zgis", 1));
    }
    
    public int getKey(String s) {
        int[] counts = countLetters(s);
        int max = maxIndex(counts);
        int key = max - 4;
        if (max < 4) {
            key = 4 - max;
        }
        return key;
    }
    
    public String decryptTwoKeys(String encrypted) {
        String decrypt = "";
        String firstString = halfOfString(encrypted, 0);
        String secondString = halfOfString(encrypted, 1);
        //int firstKey = getKey(firstString);
        int firstKey = 14;
        System.out.println("Key1 = " + firstKey);
        //int secondKey = getKey(secondString);
        int secondKey = 24;
        System.out.println("Key2 = " + secondKey);
        String firstEncrypted = decrypt(firstString, firstKey);
        String secondEncrypted = decrypt(secondString, secondKey);
        
        for (int i = 1; i < (encrypted.length() + 1); i++) {
            if (i % 2 != 0) {
                decrypt += firstEncrypted.charAt(i / 2);
            }
            else {
                decrypt += secondEncrypted.charAt(i / 2 - 1);
            }
        }
        return decrypt;
    }
    
    public void testDecryptTwoKeys() {
        //System.out.println(decryptTwoKeys("Qbkm Zgis"));
        //System.out.println(decryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx"));
        //System.out.println(decryptTwoKeys("Akag tjw Xibhr awoa aoee xakex znxag xwko"));
        // //FileResource fr = new FileResource();
        // //String encrypted = fr.asString();
        String encrypted = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        System.out.println(decryptTwoKeys(encrypted));
    }
    
    public void twoKeys(String encrypted) {
        String firstString = halfOfString(encrypted, 0);
        String secondString = halfOfString(encrypted, 1);
        for (int key1 = 0; key1 < 26; key1++) {
            for (int key2 = 3; key2 < 6; key2++) {
                String decrypt = "";
                String firstEncrypted = decrypt(firstString, key1);
                String secondEncrypted = decrypt(secondString, key2);
                for (int i = 1; i < (encrypted.length() + 1); i++) {
                    if (i % 2 != 0) {
                        decrypt += firstEncrypted.charAt(i / 2);
                    }
                    else {
                        decrypt += secondEncrypted.charAt(i / 2 - 1);
                    }
                }
                System.out.println("Key1 = " + key1 + " Key2 = " + key2 +
                                ", " + decrypt + "\n");
            }
        }
    }
    
    public void testTwoKeys() {
        //twoKeys("Akag tjw Xibhr awoa aoee xakex znxag xwko");
        FileResource fr = new FileResource();
        String encrypted = fr.asString().substring(0, 41);
        //String encrypted = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx";
        twoKeys(encrypted);
    }
}
