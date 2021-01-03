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
        // TODO sub-problem 1
        return false;
    }

    public void post(Pair<String, String> titleContentPair) {
        // TODO sub-problem 2
    }
    
    public void recommend(){
        // TODO sub-problem 3
    }

    public void search(String command) {
        // TODO sub-problem 4
    }
    
    User getUser(){
        return user;
    }
}
