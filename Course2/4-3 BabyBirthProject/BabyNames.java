
/**
 * Write a description of BabyNames here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyNames {
    public void totalBirths() {
        FileResource fr = new FileResource();
        int totalBirths = 0;
        int totalNames  = 0;
        int totalBoys = 0;
        int boyNames = 0;
        int girlNames = 0;
        int totalGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            totalNames += 1;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                boyNames += 1;
            }
            else {
                totalGirls += numBorn;
                girlNames += 1;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("total boy = " + totalBoys);
        System.out.println("boy names = " + boyNames);
        System.out.println("total girls = " + totalGirls);
        System.out.println("girl names = " + girlNames);
    }
    
    public int getRank(int year, String name, String gender) {
        //FileResource fr = new FileResource("testing/yob" + year + "short.csv");
        String path = "us_babynames/us_babynames_by_year/yob";
        FileResource fr = new FileResource(path + year + ".csv");
        int rank = 1;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                if (rec.get(0).equals(name)) {
                    return rank;
                }
                rank += 1;
            }
        }
        return -1;
    }
    
    public void testGetRank() {
        //int rank = getRank(2012, "Mason", "M");
        //System.out.println(rank);
        //rank = getRank(2012,  "Mason", "F");
        int rank = getRank(1960, "Emily", "F");
        System.out.println(rank);
        rank = getRank(1971, "Frank", "M");
        System.out.println(rank);
    }
    
    public String getName(int year, int rank, String gender) {
        //FileResource fr = new FileResource("testing/yob" + year + "short.csv");
        String path = "us_babynames/us_babynames_by_year/yob";
        FileResource fr = new FileResource(path + year + ".csv");
        int currentRank = 1;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender))  {
                if (currentRank == rank) {
                    return rec.get(0);
                }
                currentRank += 1;
            }
        }
        return "NO NAME";
    }
    
    public void testGetName() {
        //String name = getName(2012, 6, "M");
        String name = getName(1980, 350, "F");
        System.out.println(name);
        //name = getName(2014, 2, "F");
        name = getName(1982, 450, "M");
        System.out.println(name);
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        String it = "she";
        if (gender.equals("M")) {
            it = "he";
        }
        System.out.println(name + " born in " + year + " would be " + 
                            newName + " if " + it + " was born in " + newYear);
    }
    
    public void testWhatIsNameInYear() {
        //whatIsNameInYear("Isabella", 2012, 2014, "F");
        whatIsNameInYear("Susan", 1972, 2014, "F");
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    
    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int highestYear = -1;
        String year = "";
        int rank = -1;
        for (File f : dr.selectedFiles()) {
             year = f.getName().substring(3,7);
             if (getRank(Integer.parseInt(year), name, gender) != -1) {
                 if (getRank(Integer.parseInt(year), name, gender) < rank || 
                                        rank == -1) {
                     rank = getRank(Integer.parseInt(year), name, gender);
                     highestYear = Integer.parseInt(year);
                 }
             }
        }
        return highestYear;
    }
        
    public void testYearOfHighestRank() {
        //int year = yearOfHighestRank("Mason", "M");
        int year = yearOfHighestRank("Genevieve", "F");
        System.out.println(year);
        year = yearOfHighestRank("Mich", "M");
        System.out.println(year);
    }
    
    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        double totalRank = 0.0;
        int numRank = 0;
        String year = "";
        for (File f : dr.selectedFiles()) {
             year = f.getName().substring(3,7);
             if (getRank(Integer.parseInt(year), name, gender) != -1) {
                 totalRank += getRank(Integer.parseInt(year), name, gender);
                 numRank += 1;
             }
        }
        if (numRank == 0) {
            return -1.0;
        }
        else {
            return totalRank / numRank;
        }
    }
    
    public void testGetAverageRank() {
        //double average = getAverageRank("Jacob", "M");
        double average = getAverageRank("Susan", "F");
        System.out.println(average);
        //average = getAverageRank("Mason", "M");
        average = getAverageRank("Robert", "M");
        System.out.println(average);
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        //FileResource fr = new FileResource("testing/yob" + year + "short.csv");
        String path = "us_babynames/us_babynames_by_year/yob";
        FileResource fr = new FileResource(path + year + ".csv");
        int totalBirths = 0;
        int rank = getRank(year, name, gender);
        if (rank == -1) {
            return totalBirths;
        }
        else {
            int num = 1;
            for (CSVRecord rec : fr.getCSVParser(false)) {
                if (rec.get(1).equals(gender)) {
                    if (num < rank) {
                        totalBirths += Integer.parseInt(rec.get(2));
                    }
                    num += 1;
                }
            }
            return totalBirths;
        }
    }
    
    public void testGetTotalBirthsRankedHigher() {
        //int total = getTotalBirthsRankedHigher(2012, "Ethan", "M");
        int total = getTotalBirthsRankedHigher(1990, "Emily", "F");
        System.out.println(total);
        total = getTotalBirthsRankedHigher(1990, "Drew", "M");
        System.out.println(total);
    }
}
