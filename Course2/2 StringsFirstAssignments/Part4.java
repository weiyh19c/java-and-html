
/**
 * Find youtube url in given files.
 * 
 * Function Youtube & testYou were modified by standard textbook.
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class Part4 {
    public String findYoutube(String text) {
        String content = text.toLowerCase();
        String youtube = "youtube.com";
        int length = youtube.length();
        int index = content.indexOf(youtube);
        int startIndex = content.lastIndexOf("\"", index);
        int endIndex = content.indexOf("\"", index+length);
        if ((index != -1) && (startIndex != -1) && (endIndex != -1)) {
            return text.substring(startIndex, endIndex+2);
        }
        return "";
    }
    
    public void testYoutube() {
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String s = fr.asString();
            System.out.println("read " + s.length() + " characters.");
            String result = findYoutube(s);
            System.out.println("found " + result);
        }
    }
    
    public String Youtube(String url) {
        URLResource file = new URLResource(url);
        for (String item : file.words()) {
            String itemLower = item.toLowerCase();
            int pos = itemLower.indexOf("youtube.com");
            if (pos != -1) {
                int beg = itemLower.lastIndexOf("\"", pos);
                int end = itemLower.indexOf("\"", pos+1);
                System.out.println(item.substring(beg+1, end));
            }
        }
        return "";
    }
    
    public void testYou() {
        String url = "https://www.dukelearntoprogram.com//course2/data/manylinks.html";
        Youtube(url);
    }
}
