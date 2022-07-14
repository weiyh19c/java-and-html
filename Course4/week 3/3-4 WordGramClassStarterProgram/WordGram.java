
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        // TODO: Complete this method
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        // TODO: Complete this method
        if (myWords.length == 0) {
            return "";
        }
        for (int i = 0; i < myWords.length; i++) {
            ret += myWords[i];
            ret += " ";
        }
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        // TODO: Complete this method
        if (this.length() != other.length()) {
            return false;
        }
        for (int i = 0; i < length(); i++) {
            if (!wordAt(i).equals(other.wordAt(i))) {
                return false;
            }
        }
        return true;
    }

    public WordGram shiftAdd(String word) { 
        WordGram out = new WordGram(myWords, 0, myWords.length);
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        // TODO: Complete this method
        String[] list = new String[out.length()];
        for (int i = 0; i < list.length - 1; i++) {
            list[i] = out.wordAt(i+1);
        }
        list[list.length-1] = word;
        out = new WordGram(list, 0, out.length());
        return out;
    }
    
    public int hashCode() {
        String link = toString();
        return link.hashCode();
    }
}