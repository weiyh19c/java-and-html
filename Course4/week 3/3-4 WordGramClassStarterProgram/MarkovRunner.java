
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 1; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 1; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        //MarkovWordOne markovWord = new MarkovWordOne(); 
        //runModel(markovWord, st, 200); 
        MarkovWord markovWord = new MarkovWord(5);
        runModel(markovWord, st, 200, 844);
    } 
    
    public void testHashMap() {
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        //st = "a b c b c a b c c a b c";
        //st = "this is a test yes this is really a test yes a test this is wow";
        EfficientMarkovWordTest markovWord = new EfficientMarkovWordTest(3);
        runModel(markovWord, st, 200, 371);
        markovWord.printHashMapInfo();
    }
    
    public void compareMethods() {
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        
        MarkovWord markovWord1 = new MarkovWord(2);
        EfficientMarkovWord markovWord2 = new EfficientMarkovWord(2);
        System.out.println(System.nanoTime());
        runModel(markovWord1, st, 100, 42);
        System.out.println(System.nanoTime());
        runModel(markovWord2, st, 100, 42);
        System.out.println(System.nanoTime());
    }
    
    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 

}
