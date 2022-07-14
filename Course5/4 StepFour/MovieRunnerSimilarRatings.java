
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerSimilarRatings {
    private ArrayList<Rating> sortByRate(ArrayList<Rating> input) {
        ArrayList<Rating> output = new ArrayList<Rating>();
        ArrayList<Rating> original = input;
        while (!original.isEmpty()) {
            int indexLowest = -1;
            double lowestRate = 10.0;
            for (int i = 0; i < original.size(); i++) {
                if (original.get(i).getValue() <= lowestRate) {
                    lowestRate = original.get(i).getValue();
                    indexLowest = i;
                }
            }
            if (indexLowest == -1) {
                break;
            }
            Rating lowest = original.get(indexLowest);
            output.add(lowest);
            original.remove(lowest);
        }
        return output;
    }
    
    public void printAvageRatings() {
        FourthRatings fr = new FourthRatings();
        //System.out.println("number of movies is " + sr.getMovieSize());
        //System.out.println("read data for " + fr.getRaterSize() + " raters");
        int thres = 1;
        ArrayList<Rating> ratingList = fr.getAverageRatings(thres);
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println(ratingList.size() + " movies have " + thres + " or more ratings");
        ArrayList<Rating> sortedList = sortByRate(ratingList);
        for (int j = 0; j < sortedList.size(); j++) {
            String id = sortedList.get(j).getItem();
            System.out.println(sortedList.get(j).getValue() + " " + MovieDatabase.getTitle(id));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        AllFilters filters = new AllFilters();
        filters.addFilter(new YearAfterFilter(1990));
        filters.addFilter(new GenreFilter("Drama"));
        FourthRatings fr = new FourthRatings();
        //System.out.println("read data for " + fr.getRaterSize() + " raters");
        ArrayList<Rating> ratingList = fr.getAverageRatingsByFilter(1, filters);
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println("found " + ratingList.size() + " movies");
        ArrayList<Rating> sortedList = sortByRate(ratingList);
        for (int i = 0; i < sortedList.size(); i++) {
            String id = sortedList.get(i).getItem();
            double rate = sortedList.get(i).getValue();
            System.out.println(rate + " " + MovieDatabase.getYear(id) + " " + MovieDatabase.getTitle(id));
            System.out.println("    " + MovieDatabase.getGenres(id));
        }
    }
    
    public void printSimilarRatings() {
        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> list = fr.getSimilarRatings("71", 20, 5);
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        for (int i = 0; i < 1; i++) {
            System.out.println(MovieDatabase.getTitle(list.get(i).getItem()));
        }
    }
    
    public void printSimilarRatingsByGenre() {
        FourthRatings fr = new FourthRatings();
        GenreFilter filter = new GenreFilter("Mystery");
        ArrayList<Rating> list = fr.getSimilarRatingByFilter("964", 20, 5, filter);
        for (int i = 0; i < 1; i++) {
            System.out.println(MovieDatabase.getTitle(list.get(i).getItem()));
        }
    }
    
    public void printSimilarRatingsByDirector() {
        FourthRatings fr = new FourthRatings();
        DirectorsFilter filter = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        ArrayList<Rating> list = fr.getSimilarRatingByFilter("120", 10, 2, filter);
        for (int i = 0; i < 1; i++) {
            System.out.println(MovieDatabase.getTitle(list.get(i).getItem()));
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes() {
        FourthRatings fr = new FourthRatings();
        AllFilters filter = new AllFilters();
        filter.addFilter(new GenreFilter("Drama"));
        filter.addFilter(new MinutesFilter(80, 160));
        ArrayList<Rating> list = fr.getSimilarRatingByFilter("168", 10, 3, filter);
        for (int i = 0; i < 1; i++) {
            System.out.println(MovieDatabase.getTitle(list.get(i).getItem()));
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes() {
        FourthRatings fr = new FourthRatings();
        AllFilters filter = new AllFilters();
        filter.addFilter(new YearAfterFilter(1975));
        filter.addFilter(new MinutesFilter(70, 200));
        ArrayList<Rating> list = fr.getSimilarRatingByFilter("314", 10, 5, filter);
        for (int i = 0; i < 1; i++) {
            System.out.println(MovieDatabase.getTitle(list.get(i).getItem()));
        }
    }
}
