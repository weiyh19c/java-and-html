
/**
 * Write a description of DirectorFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter {
    private String[] myDirectors;
    
    public DirectorsFilter(String directors) {
        myDirectors = directors.split(",");
    }
    
    public boolean satisfies(String id) {
        for (int i = 0; i < myDirectors.length; i++) {
            String director = myDirectors[i];
            if (MovieDatabase.getDirector(id).contains(director)) {
                return true;
            }
        }
        return false;
    }
}
