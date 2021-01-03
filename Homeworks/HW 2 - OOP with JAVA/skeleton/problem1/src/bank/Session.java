package bank;

public class Session {

    private String sessionKey;
    private Bank bank;
    private boolean valid;
    Session(String sessionKey,Bank bank){
        this.sessionKey = sessionKey;
        this.bank = bank;
        valid = true;
    }

    public boolean deposit(int amount) {
        //TODO: Problem 1.2
        return false;
    }

    public boolean withdraw(int amount) {
        //TODO: Problem 1.2
        return false;
    }

    public boolean transfer(String targetId, int amount) {
        //TODO: Problem 1.2
        return false;
    }

}
