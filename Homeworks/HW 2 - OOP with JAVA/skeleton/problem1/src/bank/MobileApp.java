package bank;

import security.Encryptor;
import security.Encrypted;
import security.Message;
import security.key.BankPublicKey;
import security.key.BankSymmetricKey;

public class MobileApp {

    private String randomUniqueStringGen(){
        return Encryptor.randomUniqueStringGen();
    }
    private final String AppId = randomUniqueStringGen();
    public String getAppId() {
        return AppId;
    }

    String id, password;
    public MobileApp(String id, String password){
        this.id = id;
        this.password = password;
    }

    public Encrypted<BankSymmetricKey> sendSymKey(BankPublicKey publickey){
        //TODO: Problem 1.3
        return null;
    }

    public Encrypted<Message> deposit(int amount){
        //TODO: Problem 1.3
        return null;
    }

    public Encrypted<Message> withdraw(int amount){
        //TODO: Problem 1.3
        return null;
    }

    public boolean processResponse(Encrypted<Boolean> obj){
        //TODO: Problem 1.3
        return false;
    }

}

