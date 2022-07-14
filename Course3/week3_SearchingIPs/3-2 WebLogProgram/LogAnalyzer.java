
/**
 * updated 11.11.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()) {
             WebLogParser wp = new WebLogParser();
             records.add(wp.parseEntry(line));
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs() {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records) {
             if (!uniqueIPs.contains(le.getIpAddress())) {
                 uniqueIPs.add(le.getIpAddress());
             }
         }
         return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum(int num) {
         for (LogEntry le : records) {
             if (le.getStatusCode() > num) {
                 System.out.println(le.getStatusCode());
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records) {
             String ip = le.getIpAddress();
             String date = le.getAccessTime().toString();
             if (date.contains(someday)) {
                 if (!uniqueIPs.contains(ip)) {
                     uniqueIPs.add(ip);
                 }
             }
         }
         return uniqueIPs;
     }
     
     public int countUniqueIPsInRange(int low, int high) {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records) {
             int statusCode = le.getStatusCode();
             String ip = le.getIpAddress();
             if (statusCode >= low && statusCode <= high) {
                 if (!uniqueIPs.contains(ip)) {
                     uniqueIPs.add(ip);
                 }
             }
         }
         return uniqueIPs.size();
     }
     
     public HashMap<String,Integer> countVisitsPerIP() {
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         for (LogEntry le : records) {
             String ip = le.getIpAddress();
             if (!counts.containsKey(ip)) {
                 counts.put(ip, 1);
             }
             else {
                 counts.put(ip, counts.get(ip)+1);
             }
         }
         return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
         int maxNum = 0;
         for (String ip : counts.keySet()) {
             if (counts.get(ip) > maxNum) {
                 maxNum = counts.get(ip);
             }
         }
         return maxNum;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts) {
         ArrayList<String> mostVisits = new ArrayList<String>();
         int maxNum = mostNumberVisitsByIP(counts);
         for (String ip : counts.keySet()) {
             if (counts.get(ip) == maxNum) {
                 mostVisits.add(ip);
             }
         }
         return mostVisits;
     }
     
     public HashMap<String,ArrayList<String>> iPsForDays() {
         HashMap<String,ArrayList<String>> map = new HashMap<String,ArrayList<String>>();
         for (LogEntry le : records) {
             String ip = le.getIpAddress();
             String time = le.getAccessTime().toString();
             String date = time.substring(4,10);
             if (!map.containsKey(date)) {
                 ArrayList<String> ipList = new ArrayList<String>();
                 ipList.add(ip);
                 map.put(date, ipList);
             }
             else {
                 ArrayList<String> ipList = map.get(date);
                 ipList.add(ip);
                 map.put(date, ipList);
             }
         }
         return map;
     }
     
     public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> map) {
         int maxNum = 0;
         String maxDay = "";
         for (String day : map.keySet()) {
             if (map.get(day).size() > maxNum) {
                 maxNum = map.get(day).size();
                 maxDay = day;
             }
         }
         return maxDay;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> map,
                                 String date) {
         ArrayList<String> iPOnDay = map.get(date);
         ArrayList<String> mostVisit = new ArrayList<String>();
         HashMap<String,Integer> iPCount = new HashMap<String,Integer>();
         for (String ip : iPOnDay) {
             if (!iPCount.containsKey(ip)) {
                 iPCount.put(ip, 1);
             }
             else {
                 iPCount.put(ip, iPCount.get(ip)+1);
             }
         }
         int maxNum = mostNumberVisitsByIP(iPCount);
         for (String ip : iPCount.keySet()) {
             if (iPCount.get(ip) == maxNum) {
                 mostVisit.add(ip);
             }
         }
         return mostVisit;
     }   
}