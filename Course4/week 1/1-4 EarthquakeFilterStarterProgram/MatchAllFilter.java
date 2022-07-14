
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;


public class MatchAllFilter implements Filter {
    private ArrayList<Filter> maf;
    private StringBuilder name;
    
    public MatchAllFilter() {
        maf = new ArrayList<Filter>();
        name = new StringBuilder();
    }
    
    public void addFilter(Filter f) {
        maf.add(f);
        name.append(f.getName());
        name.append(" ");
    }
    
    public boolean satisfies(QuakeEntry qe) {
        for (Filter f : maf) {
            if (!f.satisfies(qe)) {
                return false;
            }
        }
        return true;
    }
    
    public String getName() {
        System.out.println("Filters used are: " + name);
        return name.toString();
    }
}
