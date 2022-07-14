
/**
 * Assignment for constructor
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class newCaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public newCaesarCipher(int key) {
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet = alphabet.substring(key) + 
                            alphabet.substring(0, key);
        mainKey = key;
    }
    
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            char ch = encrypted.charAt(i);
            int index = alphabet.indexOf(ch);
            if (index != -1) {
                encrypted.setCharAt(i, shiftedAlphabet.charAt(index));
            }
        }
        return encrypted.toString();
    }
    
    public String decrypt(String input) {
        newCaesarCipher cc = new newCaesarCipher(26-mainKey);
        return cc.encrypt(input);
    }
}
