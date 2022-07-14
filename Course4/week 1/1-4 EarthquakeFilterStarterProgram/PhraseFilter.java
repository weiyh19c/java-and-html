
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter {
    private String where;
    private String key;
    
    public PhraseFilter(String phrase1, String phrase2) {
        where = phrase1;
        key = phrase2;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        String title = qe.getInfo();
        if (where.equals("start")) {
            if (title.startsWith(key)) {
                return true;
            }
        }
        if (where.equals("end")) {
            if (title.endsWith(key)) {
                return true;
            }
        }
        if (where.equals("any")) {
            if (title.indexOf(key) != -1) {
                return true;
            }
        }
        return false;
    }
    
    public String getName() {
        return "Phrase";
    }
}
