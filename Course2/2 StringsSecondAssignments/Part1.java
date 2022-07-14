
/**
 * For week2,to find more genes in a dna string.
 * 
 * @MECatherine
 * @version (a version number or a date)
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (stopIndex != -1) {
            if ((stopIndex - startIndex) % 3 == 0) {
                return stopIndex;
            }
            stopIndex = dna.indexOf(stopCodon, stopIndex + 1);
        }
        return dna.length();
    }
    
    public void testFindStopCodon() {
        String dna = "ATGTGATGATGATGATGA";
        System.out.println(findStopCodon(dna, 0, "TGA"));
    }
    
    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        if (minIndex == dna.length()) {
            return "";
        }
        return dna.substring(startIndex, minIndex +3 );
    }
    
    public void testFindGene() {
        String dna = "TGTGTGTGTGTGTGTG";
        System.out.println(findGene(dna, 0));
        dna = "ATGTAAGTGTGTGATGTGTG";
        System.out.println(findGene(dna, 0));
        dna = "ATGTAGTGTGTGATGTGTG";
        System.out.println(findGene(dna, 0));
        dna = "ATGTGTGTGTTGTGATGTGTG";
        System.out.println(findGene(dna, 0));
    }
    
    public void printAllGenes(String dna) {
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene == "") {
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }
    
}
