
/**
 * To use the class of StorageResource
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class Part1 {
    public int findStopCodon(String dnastr, int startIndex, String stopCodon) {
        String dna = dnastr.toUpperCase();
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
        FileResource fr = new FileResource("brca1line1.fa");
        String dna = fr.asString();
        System.out.println(findStopCodon(dna, 0, "TAA"));
    }
    
    public String findGene(String dnastr, int where) {
        String dna = dnastr.toUpperCase();
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
        return dnastr.substring(startIndex, minIndex +3 );
    }
    
    public void testFindGene() {
        FileResource fr = new FileResource("brca1line1.fa");
        String dna = fr.asString();
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
    
    public StorageResource getAllGenes(String dna) {
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            geneList.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return geneList;
    }
    
    
    public double cgRatio(String dnastr) {
        String dna = dnastr.toUpperCase();
        int num = 0;
        int cIndex = dna.indexOf("C");
        int gIndex = dna.indexOf("G");
        while (true) {
            if (cIndex == -1) {
                break;
            }
            num += 1;
            cIndex = dna.indexOf("C", cIndex + 1);
        }
        while (true) {
            if (gIndex == -1) {
                break;
            }
            num += 1;
            gIndex = dna.indexOf("G", gIndex + 1);
        }
        return ((double)num)/dna.length();
    }
    
    public void testCgRatio() {
        String dna = "atgctctctctctcctaa";
        System.out.println(cgRatio(dna));
    }
    
    public int countCTG(String dnastr) {
        String dna = dnastr.toUpperCase();
        int num = 0;
        int ctgIndex = dna.indexOf("CTG");
        while (ctgIndex != -1) {
            num += 1;
            ctgIndex = dna.indexOf("CTG", ctgIndex + 1);
        }
        return num;
    }
    
    public void processGenes(StorageResource sr) {
        int count60 = 0;
        int countCG = 0;
        int num = 0;
        for (String s : sr.data()) {
            num += 1;
        }
        System.out.println(num + " strings in total");
        for (String s : sr.data()) {
            if (s.length() > 60) {
                //System.out.println(s);
                count60 += 1;
            }
        }
        System.out.println(count60 + " strings longer than 60.");
        for (String s : sr.data()) {
            if (cgRatio(s) > 0.35) {
                //System.out.println(s);
                countCG += 1;
            }
        }
        System.out.println(countCG + " strings whose C-G higher than 0.35.");
        int longLength = 0;
        for (String s : sr.data()) {
            if (s.length() > longLength) {
                longLength = s.length();
            }
        }
        System.out.println("The longest gene is " + longLength + " long");
    }
    
    public void testProcessGenes() {
        System.out.println("This is new.");
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        StorageResource sr = getAllGenes(dna);
        processGenes(sr);
        System.out.println(countCTG(dna) +" times CTG");
    }       
}
