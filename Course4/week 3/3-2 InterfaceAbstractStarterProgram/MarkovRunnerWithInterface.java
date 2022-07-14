
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 1; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        int seed = 1024;
        
        //MarkovZero mz = new MarkovZero();
        //runModel(mz, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        //MarkovModel mThree = new MarkovModel(3);
        //runModel(mThree, st, size, seed);
        
        //MarkovFour mFour = new MarkovFour();
        //runModel(mFour, st, size, seed);

    }
    
    public void testHashMap() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 500;
        int seed = 531;
        
        EfficientMarkovModel emTwo = new EfficientMarkovModel(5);
        runModel(emTwo, st, size, seed);
        emTwo.printHashMapInfo();
    }
    
    public void compareMethods() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 1000;
        int seed = 42;
        System.out.println(System.nanoTime());
        
        MarkovModel mTwo = new MarkovModel(2);
        runModel(mTwo, st, size, seed);
        System.out.println(System.nanoTime());
        
        EfficientMarkovModel emTwo = new EfficientMarkovModel(2);
        runModel(emTwo, st, size, seed);
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
