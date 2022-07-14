
/**
 * To find multiple occurrences.
 * 
 * @MeCatherine
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb) {
        int num = 0;
        int occurIndex = stringb.indexOf(stringa);
        while (occurIndex != -1) {
            num += 1;
            occurIndex = stringb.indexOf(stringa, occurIndex + stringa.length());
        }
        return num;
    }
    
    public void testHowMany() {
        String a = "GAA";
        String b = "ATGAACGAATTGAATC";
        System.out.println(howMany(a, b));
        a = "AA";
        b = "ATAAAA";
        System.out.println(howMany(a, b));
        a = "AA";
        b = "BBB";
        System.out.println(howMany(a, b));
    }
}
