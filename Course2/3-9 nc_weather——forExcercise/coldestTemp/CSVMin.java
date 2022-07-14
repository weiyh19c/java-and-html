
/**
 * Assignment for week 3: to find the lowest humidity and coldest temperature
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMin {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestSofar = null;
        for (CSVRecord currentRow : parser) {
            coldestSofar = getLowest(currentRow, coldestSofar, "TemperatureF");
        }
        return coldestSofar;
    }
    
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVRecord largest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature is "+
                        largest.get("TemperatureF")+" at "+largest.get("TimeEDT"));
    }
    
    public CSVRecord getLowest(CSVRecord currentRow, 
                               CSVRecord lowestSofar, String varName) {
        if (lowestSofar == null) {
            lowestSofar = currentRow;
        }
        else {
            if (lowestSofar.get(varName).contains("N/A") ||
                                lowestSofar.get(varName).contains("-9999")) {
                lowestSofar = currentRow;
            }
            else {
                double lowest = Double.parseDouble(lowestSofar.get(varName));
                double current = Double.parseDouble(currentRow.get(varName));
                if (current < lowest) {
                    lowestSofar = currentRow;
                }
            }
        }
        return lowestSofar;
    }
    
    public String fileWithColdestTemperature() {
        CSVRecord coldestSofar = null;
        File coldestFile = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            if (coldestSofar == null) {
                coldestSofar = currentRow;
                coldestFile = f;
            }
            else {
                double lowest = Double.parseDouble(coldestSofar.get("TemperatureF"));
                double current = Double.parseDouble(currentRow.get("TemperatureF"));
                if (current < lowest && current != -9999) {
                    coldestSofar = currentRow;
                    coldestFile = f;
                }
            }
        }
        return coldestFile.getName();
    }
    
    public void testFileWithColdestTemperature() {
        String fileName = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + fileName);
        FileResource fr = new FileResource("data/2013/" + fileName);
        CSVRecord coldestSofar = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was " + coldestSofar.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        for (CSVRecord row : fr.getCSVParser()) {
            System.out.println(row.get("DateUTC") + ": " + row.get("TemperatureF"));
        }
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestSofar = null;
        for (CSVRecord currentRow : parser) {
            if (!currentRow.get("Humidity").contains("N/A")){
                lowestSofar = getLowest(currentRow, lowestSofar, "Humidity");
            }
        }
        return lowestSofar;
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + 
                                " at " + csv.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestSofar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            lowestSofar = getLowest(currentRow, lowestSofar, "Humidity");
        }
        return lowestSofar;
    }
    
    public void testLowestHumidityInManyFiles() {
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + 
                                    " at " + csv.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser) {
        double totalTem = 0.0;
        int numTem = 0;
        for (CSVRecord currentRow : parser) {
            numTem += 1;
            totalTem += Double.parseDouble(currentRow.get("TemperatureF"));
        }
        double averageTem = totalTem / numTem;
        return averageTem;
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        double average = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temperature in file is " + average);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser,
                                    int value) {
        double totalTem = 0.0;
        int numTem = 0;
        for (CSVRecord row : parser) {
            int humidity = Integer.parseInt(row.get("Humidity"));
            if (humidity >=  value) {
                totalTem += Double.parseDouble(row.get("TemperatureF"));
                numTem += 1;
            }
        }
        if (numTem == 0) {
            return 0.0;
        }
        else {
            return totalTem / numTem;
        }
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        int value = 80;
        double average = averageTemperatureWithHighHumidityInFile(parser, value);
        if (average == 0) {
            System.out.println("No Temperatures with that humidity");
        }
        else {
            System.out.println("Average Temp when high Humidigy is " + average);
        }
    }
}
