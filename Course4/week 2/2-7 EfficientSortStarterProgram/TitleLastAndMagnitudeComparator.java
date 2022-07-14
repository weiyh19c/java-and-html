
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        int n1 = q1.getInfo().lastIndexOf(" ");
        int n2 = q2.getInfo().lastIndexOf(" ");
        String wrd1 = q1.getInfo().substring(n1+1);
        String wrd2 = q2.getInfo().substring(n2+1);
        if (wrd1.compareTo(wrd2) == 0) {
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
        return wrd1.compareTo(wrd2);
    }
}
