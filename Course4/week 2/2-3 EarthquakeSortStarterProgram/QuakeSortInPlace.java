
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/earthQuakeDataDec6sample2.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        sortByMagnitude(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        int minIdx = from;
        for (int i = from + 1; i < quakeData.size(); i++) {
            if (quakeData.get(i).getDepth() > quakeData.get(minIdx).getDepth()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        for (int i = 0; i < 50; i++) {
            int minIdx = getLargestDepth(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i, qmin);
            in.set(minIdx, qi);
        }
    }
    
    public void testSort2() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/earthquakeDataSampleSix2.atom";
        //String source = "data/earthQuakeDataDec6sample1.atom";
        //String source = "data/earthQuakeDataWeekDec6sample2.atom";
        //String source = "data/earthQuakeDataWeekDec6sample1.atom";
        String source = "data/earthQuakeDataDec6sample2.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSort(list);
        //sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        //System.out.println("EarthQuakes in sorted order:");
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        for (int i = 0; i < quakeData.size()-numSorted-1; i++) {
            QuakeEntry qe1 = quakeData.get(i);
            QuakeEntry qe2 = quakeData.get(i+1);
            if (qe1.getMagnitude() > qe2.getMagnitude()) {
                quakeData.set(i, qe2);
                quakeData.set(i+1, qe1);
            }
        }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        for (int i = 0; i < in.size()-1; i++) {
            onePassBubbleSort(in, i);
            System.out.println("Sort after pass " + i);
            for (QuakeEntry qe : in) {
                System.out.println(qe);
            }
        }
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        for (int i = 0; i < quakes.size()-1; i++) {
            if (quakes.get(i).getMagnitude() > quakes.get(i+1).getMagnitude()) {
                return false;
            }
        }
        return true;
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        boolean check = false;
        int num;
        for (num = 0; num < in.size()-1; num++) {
            check = checkInSortedOrder(in);
            if (check) {
                System.out.println("Sort after pass " + num);
                break;
            }
            onePassBubbleSort(in, num);
        }
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        int numPass = 0;
        for (int i = 0; i < in.size(); i++) {
            if (checkInSortedOrder(in)) {
                break;
            }
            int minIdx = getSmallestMagnitude(in, i);
            numPass += 1;
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i, qmin);
            in.set(minIdx, qi);
        }
        System.out.println("Sort after pass " + numPass);
    }
}
