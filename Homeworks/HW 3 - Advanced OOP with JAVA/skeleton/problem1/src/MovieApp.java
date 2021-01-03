
import java.util.*;

public class MovieApp {

    public boolean addMovie(String title, String[] tags) {
        // TODO sub-problem 1
        return false;
    }

    public boolean addUser(String name) {
        // TODO sub-problem 1
        return false;
    }

    public Movie findMovie(String title) {
        // TODO sub-problem 1
        return new Movie("Forrest Gump");
    }

    public User findUser(String username) {
        // TODO sub-problem 1
        return new User("Tom Hanks");
    }

    public List<Movie> findMoviesWithTags(String[] tags) {
        // TODO sub-problem 2
        return new LinkedList<>();
    }

    public boolean rateMovie(User user, String title, int rating) {
        // TODO sub-problem 3
            return false;
    }

    public int getUserRating(User user, String title) {
        // TODO sub-problem 3
        return -1;
    }

    public List<Movie> findUserMoviesWithTags(User user, String[] tags) {
        // TODO sub-problem 4
        return new LinkedList<>();
    }

    public List<Movie> recommend(User user) {
        // TODO sub-problem 4
        return new LinkedList<>();
    }
}
