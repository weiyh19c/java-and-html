
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCoden, String stopCoden) {
        String result = "";
        String newdna = dna.toUpperCase();
        int startIndex = newdna.indexOf(startCoden);
        if (startIndex == -1) {
            return result;
        }
        int endIndex = newdna.indexOf(stopCoden, startIndex+3);
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
        String a = "atgctatcgatcgattaa";
        String result = findSimpleGene(a, "ATG", "TAA");
        System.out.println("The String " + a + " has the Gene " +result);
    }
}
