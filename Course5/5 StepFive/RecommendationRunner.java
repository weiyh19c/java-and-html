
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class RecommendationRunner implements Recommender {
    public ArrayList<String> getItemsToRate() {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<String> sorted = new ArrayList<String>();
        ArrayList<String> movies = MovieDatabase.filterBy(new YearAfterFilter(1950));
        for (int i = 0; i < 15; i++) {
            Random random = new Random();
            int index = random.nextInt(movies.size());
            result.add(movies.get(index));
        }
        while (!result.isEmpty()) {
            int recentYear = 1000;
            String recent = "";
            for (String id : result) {
                if (MovieDatabase.getYear(id) > recentYear) {
                    recentYear = MovieDatabase.getYear(id);
                    recent = id;
                }
            }
            sorted.add(recent);
            result.remove(recent);
        }
        return sorted;
    }
    
    public void printRecommendationsFor(String webRaterID){
        FourthRatings fr = new FourthRatings();
        Filter filter = new GenreFilter("Drama");
        ArrayList<Rating> ratingList = fr.getSimilarRatingByFilter(webRaterID, 20, 3, filter);
        ArrayList<Rating> sortedList = new ArrayList<Rating>();
        /*while (!ratingList.isEmpty()) {
            int recentYear = 1000;
            Rating recent = new Rating(" ", 0.0);
            for (Rating rate : ratingList) {
                if (MovieDatabase.getYear(rate.getItem()) > recentYear) {
                    recentYear = MovieDatabase.getYear(rate.getItem());
                    recent = rate;
                }
            }
            String id = recent.getItem();
            //if (!RaterDatabase.getRater(webRaterID).hasRating(id)) {
                sortedList.add(recent);
            //}
            ratingList.remove(recent);
        }*/
        while (!ratingList.isEmpty()) {
            double maxRate = 0.0;
            Rating recent = new Rating(" ", 0.0);
            for (Rating rate : ratingList) {
                if (rate.getValue() > maxRate) {
                    maxRate = rate.getValue();
                    recent = rate;
                }
            }
            String id = recent.getItem();
            //if (!RaterDatabase.getRater(webRaterID).hasRating(id)) {
                sortedList.add(recent);
            //}
            ratingList.remove(recent);
        }
        
        
        int min = 10;
        if (sortedList.size() < min) {
            min = sortedList.size();
        }
        System.out.println("<html><head><style>h2{font-family:Arial; font-size:18pt;}");
        System.out.println("table{border: 1px solid black;border-collapse: collapse;margin: 0 auto;}");
        System.out.println("table,th{border: 1px solid black;padding: 5px;}");
        System.out.println("td{border: 1px solid black;padding: 5px;vertical-align: middle;}</style></head>");
        System.out.println("<h2>Movies for you</h2><body><table><tr><th></th><th>Movie</th><th>Year</th><th>Director</th><th>Country</th><th>Genre</th></tr>");
        for (int i = 0; i < min; i++) {
            String id = sortedList.get(i).getItem();
            System.out.println("<tr><td><img src=\"" + MovieDatabase.getPoster(id) + "\" height=\"50\" align=\"right\"></td>");
            System.out.println("<td><a href=\"http://www.imdb.com/title/tt" + id + "/\">" + MovieDatabase.getTitle(id) + "</a></br>");
            System.out.println("Rating "+ sortedList.get(i).getValue() +"</td>");
            System.out.println("<td>" + MovieDatabase.getYear(id) + "</td>");
            System.out.println("<td>" + MovieDatabase.getDirector(id) + "</td>");
            System.out.println("<td>" + MovieDatabase.getCountry(id) + "</td>");
            System.out.println("<td>" + MovieDatabase.getGenres(id) + "</td></tr>");
        }
        System.out.println("</table></body></html>");
    }
}
