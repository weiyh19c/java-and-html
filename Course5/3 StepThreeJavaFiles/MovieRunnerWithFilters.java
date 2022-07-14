
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerWithFilters {
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
        String ratingsfile = "data/ratings.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        //System.out.println("number of movies is " + sr.getMovieSize());
        System.out.println("read data for " + tr.getRaterSize() + " raters");
        System.out.println("read data for " + movies.size() + " movies");
        int thres = 35;
        ArrayList<Rating> ratingList = tr.getAverageRatings(thres);
        System.out.println(ratingList.size() + " movies have " + thres + " or more ratings");
        ArrayList<Rating> sortedList = sortByRate(ratingList);
        /*for (int j = 0; j < sortedList.size(); j++) {
            String id = sortedList.get(j).getItem();
            System.out.println(sortedList.get(j).getValue() + " " + MovieDatabase.getTitle(id));
        }*/
    }
    
    public void printAverageRatingByYear() {
        YearAfterFilter filter = new YearAfterFilter(2000);
        String ratingsfile = "data/ratings.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        System.out.println("read data for " + tr.getRaterSize() + " raters");
        System.out.println("read data for " + movies.size() + " movies");
        ArrayList<Rating> ratingList = tr.getAverageRatingsByFilter(20, filter);
        System.out.println("found " + ratingList.size() + " movies");
        ArrayList<Rating> sortedList = sortByRate(ratingList);
        /*for (int i = 0; i < sortedList.size(); i++) {
            String id = sortedList.get(i).getItem();
            double rate = sortedList.get(i).getValue();
            System.out.println(rate + " " + MovieDatabase.getYear(id) + " " + MovieDatabase.getTitle(id));
        }*/
    }
    
    public void printAverageRatingByGenre() {
        GenreFilter filter = new GenreFilter("Comedy");
        String ratingsfile = "data/ratings.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        System.out.println("read data for " + tr.getRaterSize() + " raters");
        System.out.println("read data for " + movies.size() + " movies");
        ArrayList<Rating> ratingList = tr.getAverageRatingsByFilter(20, filter);
        System.out.println("found " + ratingList.size() + " movies");
        ArrayList<Rating> sortedList = sortByRate(ratingList);
        /*for (int i = 0; i < sortedList.size(); i++) {
            String id = sortedList.get(i).getItem();
            double rate = sortedList.get(i).getValue();
            System.out.println(rate + " " + MovieDatabase.getTitle(id));
            System.out.println("   " + MovieDatabase.getGenres(id));
        }*/
    }
    
    public void printAverageRatingByMinutes() {
        MinutesFilter filter = new MinutesFilter(105, 135);
        String ratingsfile = "data/ratings.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        System.out.println("read data for " + tr.getRaterSize() + " raters");
        System.out.println("read data for " + movies.size() + " movies");
        ArrayList<Rating> ratingList = tr.getAverageRatingsByFilter(5, filter);
        System.out.println("found " + ratingList.size() + " movies");
        ArrayList<Rating> sortedList = sortByRate(ratingList);
        /*for (int i = 0; i < sortedList.size(); i++) {
            String id = sortedList.get(i).getItem();
            double rate = sortedList.get(i).getValue();
            System.out.println(rate + " Time: " + MovieDatabase.getMinutes(id) + " " + MovieDatabase.getTitle(id));
        }*/
    }
    
    public void printAverageRatingByDirectors() {
        DirectorsFilter filter = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        String ratingsfile = "data/ratings.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        System.out.println("read data for " + tr.getRaterSize() + " raters");
        System.out.println("read data for " + movies.size() + " movies");
        ArrayList<Rating> ratingList = tr.getAverageRatingsByFilter(4, filter);
        System.out.println("found " + ratingList.size() + " movies");
        ArrayList<Rating> sortedList = sortByRate(ratingList);
        /*for (int i = 0; i < sortedList.size(); i++) {
            String id = sortedList.get(i).getItem();
            double rate = sortedList.get(i).getValue();
            System.out.println(rate + " " + MovieDatabase.getTitle(id));
            System.out.println("    " + MovieDatabase.getDirector(id));
        }*/
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        AllFilters filters = new AllFilters();
        filters.addFilter(new YearAfterFilter(1990));
        filters.addFilter(new GenreFilter("Drama"));
        String ratingsfile = "data/ratings.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        System.out.println("read data for " + tr.getRaterSize() + " raters");
        System.out.println("read data for " + movies.size() + " movies");
        ArrayList<Rating> ratingList = tr.getAverageRatingsByFilter(8, filters);
        System.out.println("found " + ratingList.size() + " movies");
        ArrayList<Rating> sortedList = sortByRate(ratingList);
        /*for (int i = 0; i < sortedList.size(); i++) {
            String id = sortedList.get(i).getItem();
            double rate = sortedList.get(i).getValue();
            System.out.println(rate + " " + MovieDatabase.getYear(id) + " " + MovieDatabase.getTitle(id));
            System.out.println("    " + MovieDatabase.getGenres(id));
        }*/
    }
    
    public void printAverageRatingsByDirectorAndMinutes() {
        AllFilters filters = new AllFilters();
        filters.addFilter(new MinutesFilter(90, 180));
        filters.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
        String ratingsfile = "data/ratings.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        System.out.println("read data for " + tr.getRaterSize() + " raters");
        System.out.println("read data for " + movies.size() + " movies");
        ArrayList<Rating> ratingList = tr.getAverageRatingsByFilter(3, filters);
        System.out.println("found " + ratingList.size() + " movies");
        ArrayList<Rating> sortedList = sortByRate(ratingList);
        /*for (int i = 0; i < sortedList.size(); i++) {
            String id = sortedList.get(i).getItem();
            double rate = sortedList.get(i).getValue();
            System.out.println(rate + " Time: " + MovieDatabase.getMinutes(id) + " " + MovieDatabase.getTitle(id));
            System.out.println("    " + MovieDatabase.getDirector(id));
        }*/
    }
}
