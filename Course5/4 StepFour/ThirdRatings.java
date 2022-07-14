
/**
 * Write a description of ThirdRatings here.
 * not using movie files, using static method of movieDatabase instead
 * @author (your name) 
 * @version (a version number or a date)
 */ 
import java.util.*;

public class ThirdRatings {
    private ArrayList<EfficientRater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsfile);
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
        for (int j = 0; j < rateList.size(); j++) {
            total += rateList.get(j);
        }
        return total/rateList.size();
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> result = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());;
        for (int i = 0; i < movies.size(); i++) {
            String name = movies.get(i);
            double rate = getAverageByID(name, minimalRaters);
            if (rate != 0) {
                Rating rating = new Rating(name, rate);
                result.add(rating);
            }
        }
        return result;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> result = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for (int i = 0; i < movies.size(); i++) {
            String name = movies.get(i);
            double rate = getAverageByID(name, minimalRaters);
            if (rate != 0) {
                result.add(new Rating(name, rate));
            }
        }
        return result;
    }
}
