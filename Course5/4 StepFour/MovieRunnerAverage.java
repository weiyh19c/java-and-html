
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerAverage {
    public MovieRunnerAverage() {
        
    }
    
    public void printAvageRatings() {
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        SecondRatings sr = new SecondRatings(moviefile, ratingsfile);
        //System.out.println("number of movies is " + sr.getMovieSize());
        //System.out.println("number of raters is " + sr.getRaterSize());
        int thres = 12;
        ArrayList<Rating> ratingList = sr.getAverageRatings(thres);
        ArrayList<Rating> sortedList = new ArrayList<Rating>();
        System.out.println(ratingList.size() + " movies have " + thres + " or more ratings");
        while (!ratingList.isEmpty()) {
            int indexLowest = -1;
            double lowestRate = 10.0;
            for (int i = 0; i < ratingList.size(); i++) {
                if (ratingList.get(i).getValue() < lowestRate) {
                    lowestRate = ratingList.get(i).getValue();
                    indexLowest = i;
                }
            }
            if (indexLowest == -1) {
                break;
            }
            Rating lowest = ratingList.get(indexLowest);
            sortedList.add(lowest);
            ratingList.remove(lowest);
        }
        for (int j = 0; j < 1; j++) {
            String id = sortedList.get(j).getItem();
            System.out.println(sortedList.get(j).getValue() + " " + sr.getTitle(id));
        }
    }
    
    public void getAverageRatingOneMovie() {
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        SecondRatings sr = new SecondRatings(moviefile, ratingsfile);
        ArrayList<Rating> ratingList = sr.getAverageRatings(0);
        String movieTitle = "The Maze Runner";
        movieTitle = "Moneyball";
        movieTitle = "Vacation";
        String id = sr.getID(movieTitle);
        for (int i = 0; i < ratingList.size(); i++) {
            if (ratingList.get(i).getItem().equals(id)) {
                System.out.println(movieTitle + " " + ratingList.get(i).getValue());
            }
        }
    }
}
