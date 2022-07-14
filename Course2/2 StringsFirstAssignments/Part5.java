
/**
 * Find a gene with a while loop.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part5 {
    public String findGeneWhile(String dna) {
        int startIndex = dna.indexOf("ATG");
        int endIndex = dna.indexOf("TAA", startIndex+3);
        while (endIndex != -1) {
            if ((endIndex - startIndex) % 3 == 0) {
                return dna.substring(startIndex, endIndex+3);
            }
            else {
                endIndex = dna.indexOf("TAA", endIndex+1);
            }
        }
        return "";
    }
    
    public void testFindGeneWhile() {
        String a = "ATGTGATGATGATGATGATGATGATGATTAATGATGATGATTTAA";
        System.out.println(findGeneWhile(a));
    }
}
