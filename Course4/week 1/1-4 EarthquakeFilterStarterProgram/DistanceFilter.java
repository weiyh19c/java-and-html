
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter {
    private Location loc;
    private double disMax;
    
    public DistanceFilter(Location locs, double dis) {
        loc = locs;
        disMax = dis;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        double disQE = qe.getLocation().distanceTo(loc);
        //System.out.println(disQE);
        return disQE < disMax;
    }
    
    public String getName() {
        return "Distance";
    }
}
