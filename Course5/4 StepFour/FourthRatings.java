
/**
 * Write a description of FourthRatings here.
 * using database of movies and raters instead, not requiring instance variables
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class FourthRatings {
    public double getAverageByID(String id, int minimalRaters) {
        ArrayList<Double> rateList = new ArrayList<Double>();
        RaterDatabase.initialize("ratings_short.csv");
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
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
    
    private double dotProduct(Rater me, Rater r) {
        double result = 0.0;
        for (String movie : me.getItemsRated()) {
            if (r.hasRating(movie)) {
                double rateMe = me.getRating(movie) - 5;
                double rateR = r.getRating(movie) - 5;
                result += rateMe * rateR;
            }
        }
        return result;
    }
    
    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> list = new ArrayList<Rating>();
        ArrayList<Rating> sorted = new ArrayList<Rating>();
        RaterDatabase.initialize("ratings.csv");
        Rater me = RaterDatabase.getRater(id);
        for (Rater r : RaterDatabase.getRaters()) {
            if (!r.getID().equals(id)) {
                double dot = dotProduct(me, r);
                if (dot > 0) {
                    Rating rating = new Rating(r.getID(), dot);
                    list.add(rating);
                }
            }
        }
        while (!list.isEmpty()) {
            double maxDot = 0.0;
            Rating maxRating = new Rating(" ", 0.0);
            for (Rating rating : list) {
                if (rating.getValue() > maxDot) {
                    maxDot = rating.getValue();
                    maxRating = rating;
                }
            }
            sorted.add(maxRating);
            list.remove(maxRating);
        }
        return sorted;
    }
    
    public void testSimilarities() {
        ArrayList<Rating> list = getSimilarities("1");
        for (Rating rating : list) {
            System.out.println(rating.getItem());
        }
    }
        
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> result = new ArrayList<Rating>();
        ArrayList<Rating> sorted = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> raterList = getSimilarities(id);
        if (numSimilarRaters > raterList.size()) {
            return result;
        }
        for (String movie : movies) {
            int numRaters = 0;
            double totalRates = 0.0;
            for (int i = 0; i < numSimilarRaters; i++) {
                Rater r = RaterDatabase.getRater(raterList.get(i).getItem());
                if (r.hasRating(movie)) {
                    double similarity = raterList.get(i).getValue();
                    double rate = r.getRating(movie);
                    totalRates += similarity * rate;
                    numRaters += 1;
                }
            }
            if (numRaters >= minimalRaters) {
                Rating newRate = new Rating(movie, totalRates / numRaters);
                result.add(newRate);
            }
        }
        while (!result.isEmpty()) {
            double maxRate = 0.0;
            Rating maxRating = new Rating(" ", maxRate);
            for (Rating rating : result) {
                if (rating.getValue() > maxRate) {
                    maxRate = rating.getValue();
                    maxRating = rating;
                }
            }
            sorted.add(maxRating);
            result.remove(maxRating);
        }
        return sorted;
    }
    
    public ArrayList<Rating> getSimilarRatingByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> result = new ArrayList<Rating>();
        ArrayList<Rating> sorted = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> raterList = getSimilarities(id);
        if (numSimilarRaters > raterList.size()) {
            return result;
        }
        for (String movie : movies) {
            int numRaters = 0;
            double totalRates = 0.0;
            for (int i = 0; i < numSimilarRaters; i++) {
                Rater r = RaterDatabase.getRater(raterList.get(i).getItem());
                if (r.hasRating(movie)) {
                    double similarity = raterList.get(i).getValue();
                    double rate = r.getRating(movie);
                    totalRates += similarity * rate;
                    numRaters += 1;
                }
            }
            if (numRaters >= minimalRaters) {
                Rating newRate = new Rating(movie, totalRates / numRaters);
                result.add(newRate);
            }
        }
        while (!result.isEmpty()) {
            double maxRate = 0.0;
            Rating maxRating = new Rating(" ", maxRate);
            for (Rating rating : result) {
                if (rating.getValue() > maxRate) {
                    maxRate = rating.getValue();
                    maxRating = rating;
                }
            }
            sorted.add(maxRating);
            result.remove(maxRating);
        }
        return sorted;
    }
}
