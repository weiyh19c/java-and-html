
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class LargestQuakes {
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "nov20quakedatasmall.atom";
        String source = "nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for " + list.size());
        /*for (QuakeEntry qe : list) {
            System.out.println(qe);
        }*/
        //int index = indexOfLargest(list);
        //double mag = list.get(index).getMagnitude();
        //System.out.println("largest is at location " + index + " and has magnitude " + mag);
        ArrayList<QuakeEntry> largest = getLargest(list, 50);
        for (QuakeEntry qe : largest) {
            System.out.println(qe);
        }
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        double maxMagnitude = 0.0;
        int maxIndex = 0;
        for (int i = 0; i < data.size(); i++) {
            double magnitude = data.get(i).getMagnitude();
            if (magnitude > maxMagnitude) {
                maxIndex = i;
                maxMagnitude = magnitude;
            }
        }
        return maxIndex;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        if (howMany > copy.size()) {
            howMany = copy.size();
        }
        for (int i = 0; i < howMany; i++) {
            int maxIndex = indexOfLargest(copy);
            answer.add(copy.get(maxIndex));
            copy.remove(maxIndex);
        }
        return answer;
    }
}
