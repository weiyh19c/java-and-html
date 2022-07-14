
/**
 * Assignment 2: Caesar Cipher
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = "abcdefghijklmnopqrstuvwxyz";
        String shiftedAlphabetUpper = alphabetUpper.substring(key) + alphabetUpper.substring(0, key);
        String shiftedAlphabetLower = alphabetLower.substring(key) + alphabetLower.substring(0, key);
        for (int i = 0; i < input.length(); i++) {
            char chInput = encrypted.charAt(i);
            int indexUpper = alphabetUpper.indexOf(chInput);
            int indexLower = alphabetLower.indexOf(chInput);
            if (indexUpper != -1) {
                char chNew = shiftedAlphabetUpper.charAt(indexUpper);
                encrypted.setCharAt(i, chNew);
            }
            if (indexLower != -1) {
                char chNew = shiftedAlphabetLower.charAt(indexLower);
                encrypted.setCharAt(i, chNew);
            }
        }
        return encrypted.toString();
    }
    
    public void testEncrypt() {
        //System.out.println(encrypt("FIRST LEGION ATTACK EAST FLANK!", 23));
        //System.out.println(encrypt("First Legion", 23));
        //System.out.println(encrypt("First Legion", 17));
        //String input = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String input = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        System.out.println(encrypt(input, 15));
    }
    
    public void testCaesar() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, 3);
        System.out.println("key is " + 3 + "\n" + encrypted);
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        String encrypted = "";
        for (int i = 0; i < input.length(); i++) {
            String ch = null;
            if (i % 2 == 0) {
                ch = encrypt(input.substring(i, i+1), key1);
            }
            else {
                ch = encrypt(input.substring(i, i+1), key2);
            }
            encrypted += ch;
        }
        return encrypted;
    }
    
    public void testEncryptTwoKeys() {
        //System.out.println(encryptTwoKeys("First Legion", 23, 17));
        //String input = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String input = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        System.out.println(encryptTwoKeys(input, 21, 8));
    }
        
}
