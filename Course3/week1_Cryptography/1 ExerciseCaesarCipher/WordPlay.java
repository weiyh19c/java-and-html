
/**
 * Assignment 1: Word Play 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel(char ch) {
        String vowel = "AEIOU";
        int index = vowel.indexOf(Character.toUpperCase(ch));
        if (index == -1) {
            return false;
        }
        else {
            return true;
        }
    }
    
    public void testIsVowel() {
        System.out.println(isVowel('C'));
        System.out.println(isVowel('a'));
    }
    
    public String replaceVowels(String phrase, char ch) {
        StringBuilder rePhrase = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++) {
            char c = rePhrase.charAt(i);
            if (isVowel(c)) {
                rePhrase.setCharAt(i, ch);
            }
        }
        return rePhrase.toString();
    }
    
    public void testReplaceVowels() {
        System.out.println(replaceVowels("Hello World", '*'));
    }
    
    public String emphasize(String phrase, char ch) {
        StringBuilder emphasize = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++) {
            char c = emphasize.charAt(i);
            if (Character.toUpperCase(c) == Character.toUpperCase(ch)) {
                if (i % 2 == 1){
                    emphasize.setCharAt(i, '+');
                }
                else {
                    emphasize.setCharAt(i, '*');
                }
            }
        }
        return emphasize.toString();
    }
                  
    public void testEmphasize() {
        System.out.println(emphasize("dna ctgaaactga", 'a'));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }
}
