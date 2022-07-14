
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        //la.readFile("short-test_log");
        la.readFile("weblog2_log");
        System.out.println(la.countUniqueIPs());
    }
    
    public void testHigherNum() {
        LogAnalyzer la = new LogAnalyzer();
        //la.readFile("short-test_log");
        //la.printAllHigherThanNum(5);
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }
    
    public void testIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        //la.readFile("weblog-short_log");
        //System.out.println(la.uniqueIPVisitsOnDay("Sep 14").size());
        //System.out.println(la.uniqueIPVisitsOnDay("Sep 30").size());
        //la.readFile("weblog1_log");
        //System.out.println(la.uniqueIPVisitsOnDay("Mar 17").size());
        la.readFile("weblog2_log");
        System.out.println(la.uniqueIPVisitsOnDay("Sep 24").size());
    }
    
    public void testCountInRange() {
        LogAnalyzer la = new LogAnalyzer();
        //la.readFile("short-test_log");
        //System.out.println(la.countUniqueIPsInRange(200,299));
        //System.out.println(la.countUniqueIPsInRange(300,399));
        //la.readFile("weblog1_log");
        //System.out.println(la.countUniqueIPsInRange(300,399));
        la.readFile("weblog2_log");
        System.out.println(la.countUniqueIPsInRange(400,499));
    }
    
    public void testCountsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        System.out.println(la.countVisitsPerIP());
    }
    
    public void testMostNumberVisits() {
        LogAnalyzer la = new LogAnalyzer();
        //la.readFile("weblog3-short_log");
        //la.readFile("weblog1_log");
        la.readFile("weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(la.mostNumberVisitsByIP(counts));
    }
    
    public void testIPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        //la.readFile("weblog3-short_log");
        //la.readFile("weblog1_log");
        la.readFile("weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        ArrayList<String> list = la.iPsMostVisits(counts);
        System.out.println(list);
    }
    
    public void testIPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String, ArrayList<String>> counts = la.iPsForDays();
        System.out.println(counts);
    }
    
    public void testDayWithMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        //la.readFile("weblog3-short_log");
        //la.readFile("weblog1_log");
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> counts = la.iPsForDays();
        System.out.println(la.dayWithMostIPVisits(counts));
    }
    
    public void testIPsMostOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        //la.readFile("weblog3-short_log");
        //la.readFile("weblog1_log");
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> counts = la.iPsForDays();
        System.out.println(la.iPsWithMostVisitsOnDay(counts, "Sep 29"));
    }
}
