
/**
 * To count the number of genes
 * 
 * @MECatherine
 * @version (a version number or a date)
 */
public class Part3 {
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
    
    public void printAllGenes(String dna) {
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }
    
    public int CountGenes(String dna) {
        int startIndex = 0;
        int count = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            count += 1;
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        System.out.println(count);
        return count;
    }
    
    public void testCountGenes() {
        String dna = "ATGTAAGATGCCCTAGT";
        CountGenes(dna);
    }
}
