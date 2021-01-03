
import java.util.*;

public class Movie implements Comparable<Movie>{
    private String title;
    private Set<String> tags;
    private float avgRate;

    public Movie(String title) {
        this.title = title;
        this.tags = new HashSet<>();
        this.avgRate = 0f;
    }

    public Movie(String title, String[] tags){
        this.title = title;
        this.tags = new HashSet<>(Arrays.asList(tags));
        this.avgRate = 0f;
    }

    public boolean checkTags(String[] cTags){
        Set<String> tagSet = new HashSet<>(Arrays.asList(cTags));

        if(tags.containsAll(tagSet)) return true;
        else return false;
    }

    public void setAvgRate(float avgRate){
        this.avgRate = avgRate;
    }

    public float getAvgRate(){
        return this.avgRate;
    }


    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null) return false;

        return (obj instanceof Movie && this.title.equals(((Movie)obj).title));
    }

    @Override
    public int hashCode(){
        int hash = 31;
        hash = 89 * hash + (this.title != null ? this.title.hashCode() : 0);
        return hash;
    }

    @Override
    public int compareTo(Movie otherMovie){
        if(this.getAvgRate() < otherMovie.getAvgRate()){
            return 1;
        } else if(this.getAvgRate() > otherMovie.getAvgRate()) {
            return -1;
        } else{
            return this.title.compareTo(otherMovie.title);
        }
    }

}
