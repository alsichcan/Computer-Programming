import java.util.*;
import java.time.LocalDateTime;

public class FrontEnd {
    private UserInterface ui;
    private BackEnd backend;
    private User user;

    public FrontEnd(UserInterface ui, BackEnd backend) {
        this.ui = ui;
        this.backend = backend;
    }
    
    public boolean auth(String authInfo){
        String[] info = authInfo.split("\n");
        String id = info[0];
        String passwd = info[1];
        this.user = new User(id, passwd);
        return backend.authCheck(id, passwd);
    }

    public void post(Pair<String, String> titleContentPair) {
        Post pst = new Post(titleContentPair.key, titleContentPair.value);
        backend.savePost(user, pst);
    }
    
    public void recommend(){
        List<Post> recList = backend.recommendPost(user);
        for(Post post : recList){
            ui.println(post.toString());
        }
    }

    public void search(String command) {
        String[] keywords = command.split("\\s+");
        keywords = Arrays.copyOfRange(keywords, 1, keywords.length);
        Set<String> keySet = new HashSet<>(Arrays.asList(keywords));

        List<Post> searchList = backend.searchPost(keySet);
        for(Post post : searchList){
            ui.println(post.getSummary());
        }
    }
    
    User getUser(){
        return user;
    }
}
