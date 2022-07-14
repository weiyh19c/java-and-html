
/**
 * Write a description of SecondRatings here.
 * using methods from firstRatings to calculate ratings of different movies
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<EfficientRater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getMovieSize() {
        return myMovies.size();
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    public double getAverageByID(String id, int minimalRaters) {
        ArrayList<Double> rateList = new ArrayList<Double>();
        double total = 0.0;
        for (int i = 0; i < myRaters.size(); i++) {
            double rate = myRaters.get(i).getRating(id);
            if (rate != -1) {
                rateList.add(rate);
            }
        }
        if (rateList.size() < minimalRaters) {
            return 0.0;
        }
        else {
            for (int j = 0; j < rateList.size(); j++) {
                total += rateList.get(j);
            }
            return total/rateList.size();
        }
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> result = new ArrayList<Rating>();
        for (int i = 0; i < myMovies.size(); i++) {
            String name = myMovies.get(i).getID();
            double rate = getAverageByID(name, minimalRaters);
            if (rate != 0) {
                Rating rating = new Rating(name, rate);
                result.add(rating);
            }
        }
        return result;
    }
    
    public String getTitle(String id) {
        for (int i = 0; i < myMovies.size(); i++) {
            if (myMovies.get(i).getID().equals(id)) {
                return myMovies.get(i).getTitle();
            }
        }
        return "ID does not exist";
    }
    
    public String getID(String title) {
        for (int i = 0; i < myMovies.size(); i++) {
            if (myMovies.get(i).getTitle().equals(title)) {
                return myMovies.get(i).getID();
            }
        }
        return "NO SUCH TITLE";
    }
}
