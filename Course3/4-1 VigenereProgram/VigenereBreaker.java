import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder sliceMessage = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            char c = message.charAt(i);
            sliceMessage.append(c);
        }
        return sliceMessage.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        for (int i = 0; i < klength; i++) {
            String slice = sliceString(encrypted, i, klength);
            CaesarCracker cc = new CaesarCracker(mostCommon);
            key[i] = cc.getKey(slice);
        }
        return key;
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        //FileResource fr = new FileResource();
        //String encrypted = fr.asString();
        //FileResource frDict = new FileResource();
        //HashSet<String> dictionary = readDictionary(frDict);
        //String answer = breakForLanguage(encrypted, dictionary);
        //System.out.println(answer.substring(0, 90));
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource singleFR = new FileResource(f);
            String fileName = f.getName();
            if (!languages.containsKey(fileName)) {
                HashSet<String> dict = readDictionary(singleFR);
                languages.put(fileName, dict);
            }
        }
        
        String answer = breakForAllLangs(encrypted, languages);
        System.out.println(answer);
        
        //int[] key = tryKeyLength(encrypted, 4, 'e');
        //VigenereCipher vc = new VigenereCipher(key);
        //String answer = vc.decrypt(encrypted);
        //System.out.println(answer.substring(0, 60));
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> set = new HashSet<String>();
        for (String line : fr.lines()) {
            set.add(line.toLowerCase());
        }
        return set;
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
        int count = 0;
        for (String word : message.split("\\W+")) {
            if (dictionary.contains(word.toLowerCase())) {
                count += 1;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary, char commonChar) {
        int keyLength = 1;
        int countMax = 0;
        String decrypted = "";
        for (int i = 1; i <= 100; i++) {
            int[] key = tryKeyLength(encrypted, i, commonChar);
            VigenereCipher vc = new VigenereCipher(key);
            String answer = vc.decrypt(encrypted);
            int count = countWords(answer, dictionary);
            if (count > countMax) {
                countMax = count;
                keyLength = i;
                decrypted = answer;
            }
        }
        //System.out.println("key length is " + keyLength);
        //System.out.println("valid words: " + countMax);
        //System.out.println("total words: " + encrypted.length());
        return decrypted;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (String word : dictionary) {
            for (char ch : word.toCharArray()) {
                int index = alpha.indexOf(Character.toLowerCase(ch));
                if (index != -1) {
                    counts[index] += 1;
                }
            }
        }
        int countMax = 0;
        char commonChar = 'a';
        for (int i = 0; i < alpha.length(); i++) {
            if (counts[i] > countMax) {
                countMax = counts[i];
                commonChar = alpha.charAt(i);
            }
        }
        return commonChar;
    }
    
    public String breakForAllLangs(String encrypted, 
             HashMap<String, HashSet<String>> languages) {
        int countMax = 0;
        String langChosen  = "";
        String decrypted = "";
        for (String language : languages.keySet()) {
            HashSet<String> dictionary = languages.get(language);
            char commonChar = mostCommonCharIn(dictionary);
            String answer = breakForLanguage(encrypted, dictionary, commonChar);
            int count = countWords(answer, dictionary);
            if (count > countMax) {
                countMax = count;
                langChosen = language;
                decrypted = answer;
            }
        }
        System.out.println("language is: " + langChosen);
        System.out.println("valid words: " + countMax);
        return decrypted;
    }
}
