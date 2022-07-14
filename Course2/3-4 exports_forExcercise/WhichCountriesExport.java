/**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 * to find countries with different exports, with my update
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        //for each row in the CSV File
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Exports");
            //Check if it contains exportOfInterest
            if (export.contains(exportOfInterest)) {
                //If so, write down the "Country" from that row
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }

    public void whoExportsCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
    }
    
    public CSVParser tester(String fileName) {
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser();
        return parser;
    }
    
    public String countryInfo(CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            String Country = record.get("Country");
            if (Country.equals(country)) {
            //if (Country.contains(country)) {
                System.out.println(Country);
                String export = record.get("Exports");
                String value = record.get("Value (dollars)");
                return country + ": " + export + ": " + value;
               }
        }
        return "NOT FOUND";
    }
    
    public void testCountryInfo() {
        CSVParser parser = tester("exportdata.csv");
        System.out.println(countryInfo(parser, "Nauru"));
    }
    
    public void listExportersTwoProducts(CSVParser parser, 
                                            String exportItem1,
                                            String exportItem2) {
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            String country = record.get("Country");
            if (export.contains(exportItem1) && export.contains(exportItem2)) {
                System.out.println(country);
            }
        }
    }
    
    public void testList() {
        CSVParser parser = tester("exportdata.csv");
        listExportersTwoProducts(parser, "cotton", "flowers");
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem)) {
                count += 1;
            }
        }
        return count;
    }
    
    public void testNumber() {
        CSVParser parser = tester("exportdata.csv");
        System.out.println(numberOfExporters(parser, "cocoa"));
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            String country = record.get("Country");
            String value = record.get("Value (dollars)");
            if (value.length() > amount.length()) {
                System.out.println(country + " " + value);
            }
        }
    }
    
    public void testBig() {
        CSVParser parser = tester("exportdata.csv");
        bigExporters(parser, "$999,999,999,999");
    }
}
