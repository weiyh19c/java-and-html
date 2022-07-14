
/**
 * 11.9 updated count words in many files
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFiles {
    private HashMap<String, ArrayList> map;
    private int maxNum;
    
    public WordsInFiles() {
        map = new HashMap<String, ArrayList>();
    }
    
    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        for (String word : fr.words()) {
            if (!map.containsKey(word)) {
                ArrayList<String> fileList = new ArrayList<String>();
                fileList.add(f.getName());
                map.put(word, fileList);
            }
            else {
                int index = map.get(word).indexOf(f.getName());
                if (index == -1) {
                    ArrayList<String> list = map.get(word);
                    list.add(f.getName());
                    map.put(word, list);
                }
            }
        }
    }
    
    public void buildWordFileMap() {
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber() {
        maxNum = 0;
        for (String s : map.keySet()) {
            int length = map.get(s).size();
            if (length > maxNum) {
                maxNum = length;
            }
        }
        return maxNum;
    }
    
    public ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> wordsList = new ArrayList<String>();        
        for (String s : map.keySet()) {
            if (map.get(s).size() == number) {
                wordsList.add(s);
            }
        }
        return wordsList;
    }
    
    public void printFilesIn(String word) {
        if (map.containsKey(word)) {
            ArrayList<String> fileList = map.get(word);
            System.out.println(fileList.size());
            for (int i = 0; i < fileList.size(); i++) {
                System.out.println(fileList.get(i));
            }
        }
    }
    
    public void tester() {
        buildWordFileMap();
        System.out.println("\n"+ "number 7");
        System.out.println(wordsInNumFiles(7).size());
        System.out.println("\n"+"number 4");
        System.out.println(wordsInNumFiles(4).size());
        System.out.println("\n"+"sea");
        printFilesIn("sea");
        System.out.println("\n"+"tree");
        printFilesIn("tree");
        //int max = maxNumber();
        //System.out.println("The greatest number of files a word appears in is " + max);
        //System.out.println("there are files: ");
        //for (String s : wordsInNumFiles(max)) {
            //System.out.println(s);
        //}
        //printFilesIn("cats");
        //printFilesIn("and");
        //System.out.println("number 5");
        //System.out.println(wordsInNumFiles(5).size());
        //System.out.println("number 5");
        //System.out.println(wordsInNumFiles(4).size());
        //System.out.println("sad");
        //printFilesIn("sad");
        //System.out.println("red");
        //printFilesIn("red");
    }
}
