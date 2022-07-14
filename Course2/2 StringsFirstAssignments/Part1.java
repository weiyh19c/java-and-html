
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna) {
        String result = "";
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return result;
        }
        int endIndex = dna.indexOf("TAA", startIndex+3);
        if (endIndex == -1) {
            return result;
        }
        if ((endIndex-startIndex) % 3 == 0) {
            result = dna.substring(startIndex, endIndex+3);
            return result;
        }
        else {
            return result;
        }
    }
    
    public void testSimpleGene() {
        String a = "ATGCGTCGTATGATGCTAAT";
        String result = findSimpleGene(a);
        System.out.println("The String " + a + " has the Gene " +result);
    }
}
