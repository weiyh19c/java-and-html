
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class Tester {
    public void testGetFollows() {
        MarkovOne markov = new MarkovOne();
        String text = "this is a test yes this is a test.";
        markov.setTraining(text);
        ArrayList<String> list = markov.getFollows("e");
        System.out.println(list);
    }
    
    public void testGetFollowsWithFile() {
        MarkovOne markov = new MarkovOne();
        FileResource fr = new FileResource();
        String text = fr.asString();
        markov.setTraining(text);
        ArrayList<String> list = markov.getFollows("he");
        System.out.println(list.size());
    }
}
