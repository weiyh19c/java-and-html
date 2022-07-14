
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    private ArrayList<Movie> movies;
    private ArrayList<EfficientRater> raters;
    
    public FirstRatings() {
    
    }
    
    public ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> list = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename.trim());
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
            String id = record.get(0);
            String title = record.get(1);
            String year = record.get(2);
            String genre = record.get(4).trim();
            String director = record.get(5).trim();
            String country =  record.get(3).trim();
            String poster = record.get(7).trim();
            int minutes = Integer.parseInt(record.get(6).trim());
            Movie newMovie = new Movie(id, title, year, genre, director, country, poster, minutes);
            list.add(newMovie);
        }
        return list;
    }
    
    public ArrayList<EfficientRater> loadRaters(String filename) {
        ArrayList<EfficientRater> result = new ArrayList<EfficientRater>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
            String name = record.get(0).trim();
            String movieID = record.get(1).trim();
            double rating = Double.parseDouble(record.get(2).trim());
            if (result.size() != 0) {
                int index = -1;
                for (int i = 0; i < result.size(); i ++) {
                    if (result.get(i).getID().equals(name)) {
                        index = i;
                    }
                }
                if (index == -1) {
                    EfficientRater newRater = new EfficientRater(name);
                    newRater.addRating(movieID, rating);
                    result.add(newRater);
                }
                else {
                    EfficientRater oldRater = result.get(index);
                    oldRater.addRating(movieID, rating);
                    result.set(index, oldRater);
                }
            }
            else {
                EfficientRater newRater = new EfficientRater(name);
                newRater.addRating(movieID, rating);
                result.add(newRater);
            }
        }
        return result;
    }
    
    public void testLoadMovies() {
        String filename = "data/ratedmoviesfull.csv";
        movies = loadMovies(filename);
        System.out.println(movies.size() + " movies in total");
        int numComedy = 0;
        int numMinute = 0;
        HashMap<String, ArrayList<Movie>> directors = new HashMap<String,ArrayList<Movie>>();
        for (int i = 0; i < movies.size(); i++) {
            //System.out.println(movies.get(i));
            if (movies.get(i).getGenres().contains("Comedy")) {
                numComedy += 1;
            }
            if (movies.get(i).getMinutes() > 150) {
                numMinute += 1;
            }
            String[] list = movies.get(i).getDirector().split(",");
            for (int j = 0; j < list.length; j++)  {
                if (directors.containsKey(list[j])) {
                    ArrayList<Movie> movieList = directors.get(list[j]);
                    movieList.add(movies.get(i));
                    directors.put(list[j], movieList);
                }
                else {
                    ArrayList<Movie> movieList = new ArrayList<Movie>();
                    movieList.add(movies.get(i));
                    directors.put(list[j], movieList);
                }
            }
        }
        int numMaxDirector = 0;
        ArrayList<String> maxDirector = new ArrayList<String>();
        for (String name : directors.keySet()) {
            if (directors.get(name).size() > numMaxDirector) {
                numMaxDirector = directors.get(name).size();
            }
        }
        for (String name : directors.keySet()) {
            if (directors.get(name).size() == numMaxDirector) {
                maxDirector.add(name);
                System.out.println(name);
            }
        }
        System.out.println(maxDirector.size() + " directors that directed " + numMaxDirector + " movies");
        System.out.println(numComedy + " comedy movies");
        System.out.println(numMinute + " movies longer than 150m");
    }
    
    public void testLoadRaters() {
        String filename = "data/ratings.csv";
        raters = loadRaters(filename);
        System.out.println(raters.size() + " raters in total");
        String raterID = "193";
        String movie = "1798709";
        int numMovie = 0;
        ArrayList<String> maxRaters = new ArrayList<String>();
        ArrayList<String> ratedMovies = new ArrayList<String>();
        int numMaxRaters = 0;
        for (int i = 0; i < raters.size(); i++) {
            //System.out.println("Rater " + raters.get(i).getID() + " has " + raters.get(i).numRatings() + " ratings");
            ArrayList<String> movieList = raters.get(i).getItemsRated();
            for (int j = 0; j < movieList.size(); j++) {
                String movieID = movieList.get(j);
                double rating = raters.get(i).getRating(movieID);
                //System.out.println(movieID + " rating " + rating);
                if (!ratedMovies.contains(movieID)) {
                    ratedMovies.add(movieID);
                }
            }
            if (raters.get(i).getID().equals(raterID)) {
                System.out.println(raterID + " has " + raters.get(i).numRatings() + " ratings");
            }
            if (raters.get(i).numRatings() > numMaxRaters) {
                numMaxRaters = raters.get(i).numRatings();
            }
            if (raters.get(i).hasRating(movie)) {
                numMovie += 1;
                //System.out.println(raters.get(i).getID());
            }
        }
        System.out.println("movie " + movie + " has " + numMovie + " ratings");
        for (int i  = 0; i < raters.size(); i++) {
            if (raters.get(i).numRatings() == numMaxRaters) {
                System.out.println(raters.get(i).getID() + " rated most number of movies");
                maxRaters.add(raters.get(i).getID());
            }
        }
        System.out.println(ratedMovies.size() + " movies rated");
        System.out.println("maximum number of ratings is " + numMaxRaters);
        //System.out.println("number of raters is " + maxRaters.size());
    }
}
