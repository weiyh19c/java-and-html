import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        //Filter f = new MinMagFilter(4.0); 
        //ArrayList<QuakeEntry> m7  = filter(list, f); 
        
        /*Filter f1 = new MagnitudeFilter(4.0, 5.0);
        Filter f2 = new DepthFilter(-35000.0, -12000.0);
        */
        /*Location tokyo = new Location(35.42, 139.43);
        Filter f1 = new DistanceFilter(tokyo, 10000000);
        Filter f2 = new PhraseFilter("end", "Japan");
        */
       Filter f1 = new MagnitudeFilter(3.5, 4.5);
       Filter f2 = new DepthFilter(-55000.0, -20000.0);
       //Location denver = new Location(39.7392, -104.9903);
       //Filter f1 = new DistanceFilter(denver, 1000000);
       //Filter f2 = new PhraseFilter("end", "a");
       ArrayList<QuakeEntry> list1 = filter(list, f1);
       ArrayList<QuakeEntry> list2 = filter(list1, f2);
       System.out.println(list2.size() + " quakes in total");
       /*for (QuakeEntry qe: list2) { 
           System.out.println(qe);
       }*/
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
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
    
    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        /*MagnitudeFilter f1 = new MagnitudeFilter(0.0, 2.0);
        DepthFilter f2 = new DepthFilter(-100000.0, -10000.0);
        PhraseFilter f3 = new PhraseFilter("any", "a");
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        */
        
        MagnitudeFilter f1 = new MagnitudeFilter(1.0, 4.0);
        DepthFilter f2 = new DepthFilter(-180000.0, -30000.0);
        PhraseFilter f3 = new PhraseFilter("any", "o");
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        
        ArrayList<QuakeEntry> af = filter(list, maf);
        System.out.println(af.size() + " quakes in total");
        /*for (QuakeEntry qe : af) {
            System.out.println(qe);
        }
        */
    }
    
    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        /*MagnitudeFilter f1 = new MagnitudeFilter(0.0, 3.0);
        Location tulsa = new Location(36.1314, -95.9372);
        DistanceFilter f2 = new DistanceFilter(tulsa, 10000000);
        PhraseFilter f3 = new PhraseFilter("any", "Ca");
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        maf.getName();
        */
        
        MagnitudeFilter f1 = new MagnitudeFilter(0.0, 5.0);
        Location billund = new Location(55.7308, 9.1153);
        DistanceFilter f2 = new DistanceFilter(billund, 3000000);
        PhraseFilter f3 = new PhraseFilter("any", "e");
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        maf.getName();
        
        ArrayList<QuakeEntry> af = filter(list, maf);
        System.out.println(af.size() + " quakes in total");
        /*for (QuakeEntry qe : af) {
            System.out.println(qe);
        }
        */
    }
}
