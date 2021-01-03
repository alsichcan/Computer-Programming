
import java.util.*;

public class MovieApp {

    Map<String, Movie> movieList = new HashMap<>();
    Map<String, User> userList = new HashMap<>();
    Map<String, Map<User, Integer>> rateList = new HashMap<>();

    public boolean addMovie(String title, String[] tags) {
        if(movieList.containsKey(title)){
            return false;
        } else{
            movieList.put(title, new Movie(title, tags));
            return true;
        }
    }

    public boolean addUser(String name) {
        if(userList.containsKey(name)){
            return false;
        } else{
            userList.put(name, new User(name, true));
            return true;
        }
    }

    public Movie findMovie(String title) {
        if(movieList.containsKey(title)){
            return movieList.get(title);
        } else{
            return null;
        }
    }

    public User findUser(String username) {
        if(userList.containsKey(username)){
            return userList.get(username);
        } else{
            return null;
        }
    }

    public List<Movie> findMoviesWithTags(String[] tags) {
        List<Movie> resultList = new LinkedList<>();

        if(tags.length == 0) return resultList;

        for(Movie mv : movieList.values()){
            if(mv.checkTags(tags)){
                resultList.add(mv);
            }
        }
        Collections.sort(resultList);
        return resultList;
    }

    public boolean rateMovie(User user, String title, int rating) {
        if(!userIsValid(user) || !movieIsValid(title) || rating < 1 || rating > 10 )
            return false;

        if(rateList.containsKey(title)){
            rateList.get(title).put(user, rating);
        } else{
            HashMap<User, Integer> hm = new HashMap<>();
            hm.put(user, rating);
            rateList.put(title, hm);
        }
        return true;
    }

    public int getUserRating(User user, String title) {
        if(!userIsValid(user) || !movieIsValid(title)) {
            return -1;
        }

        if(rateList.containsKey(title) && rateList.get(title).containsKey(user)){
            return rateList.get(title).get(user);
        } else{
            return 0;
        }
    }

    public List<Movie> findUserMoviesWithTags(User user, String[] tags) {
        if(!userIsValid(user)) return new LinkedList<>();

        List<Movie> searchedMovies = findMoviesWithTags(tags);
        user.addSearchHistory(searchedMovies);

        return searchedMovies;
    }

    public List<Movie> recommend(User user) {
        if(!userIsValid(user)) return new LinkedList<>();

        List<Movie> recommendMovies = new LinkedList<>();
        recommendMovies.addAll(user.getSearchHistory());

        for(Movie movie : recommendMovies){
            float avgRate = 0f;
            int count = 0;

            if(!rateList.containsKey(movie.toString())) continue;

            for(int rate : rateList.get(movie.toString()).values()){
                avgRate += rate;
                count++;
            }
            movie.setAvgRate(avgRate/count);
        }

        Collections.sort(recommendMovies);

        List<Movie> returnMovies = new LinkedList<>();
        ListIterator<Movie> itr = recommendMovies.listIterator();

        for(int i = 0; i < 3; i++){
            if(itr.hasNext()){
                returnMovies.add(itr.next());
            }
        }

        return returnMovies;
    }

    public boolean userIsValid(User user){
        if(user == null) return false;

        if(user.isRegistered() == false) return false;

        if(!userList.containsKey(user.getUsername())) return false;

        if((userList.get(user.getUsername())).equals(user)) return true;
        else return false;
    }

    public boolean movieIsValid(String title){
        if(title == null) return false;

        if(!movieList.containsKey(title)) return false;

        return true;
    }
}