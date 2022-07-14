
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public String twoOccurrences(String stringa, String stringb) {
        boolean estimate = false;
        int lengtha = stringa.length();
        int occur = stringb.indexOf(stringa);
        if (occur != -1) {
            int occur2 = stringb.indexOf(stringa, occur+lengtha);
            if (occur2 != -1) {
                estimate = true;
            }
        }
        String result = String.valueOf(estimate);
        return result;
    }
    
    public String lastPart(String stringa, String stringb) {
        String result = "";
        int lengtha = stringa.length();
        int lengthb = stringb.length();
        int indexa = stringb.indexOf(stringa);
        if (indexa == -1) {
            result = stringb;
        }
        else {
            result = stringb.substring(indexa+lengtha);
        }
        return result;
    }
    
    public void TwoOccurrences() {
        String a = "atg";
        String b = "ctgtatgta";
        String result = twoOccurrences(a, b);
        System.out.println(result);
    }
    
    public void lastPart() {
        String a = "zoo";
        String b = "forest";
        String result = lastPart(a, b);
        System.out.println("The part of the string after "+a+" in "+b+" is "+result);
    }
}
