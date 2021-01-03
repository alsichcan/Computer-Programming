

import java.util.*;

public class User {
    private String username;
    private Set<Movie> searchHistory;
    private boolean registered = false;

    public User(String username) {
        this.username = username;
        this.searchHistory = new HashSet<>();
    }

    public User(String username, boolean registered){
        this(username);
        this.registered = true;
    }

    public void addSearchHistory(List<Movie> searchedMovies){
        this.searchHistory.addAll(searchedMovies);
    }

    public Set<Movie> getSearchHistory(){
        return this.searchHistory;
    }

    public String getUsername(){
        return username;
    }

    public boolean isRegistered() { return registered; }

    @Override
    public String toString() {
        return username;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null) return false;

        return (obj instanceof User && this.username.equals(((User)obj).username));
    }

    @Override
    public int hashCode(){
        int hash = 31;
        hash = 89 * hash + (this.username != null ? this.username.hashCode() : 0);
        return hash;
    }
}
