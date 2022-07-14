/**
 * Find the highest (hottest) temperature in a file of CSV weather data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord largestSofar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            //If largestSoFar is nothing
            if (largestSofar == null) {
                largestSofar = currentRow;
            }
            //Otherwise
            else {
                //Check if currentRow’s temperature > largestSoFar’s
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(largestSofar.get("TemperatureF"));
                if (currentTemp > largestTemp) {
                    //If so update largestSoFar to currentRow
                    largestSofar = currentRow;
                }
            }
        }
        //The largestSoFar is the answer
        return largestSofar;
    }

    public void testHottestInDay () {
        FileResource fr = new FileResource("data/2015/weather-2015-01-02.csv");
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                   " at " + largest.get("TimeEST"));
    }
}
