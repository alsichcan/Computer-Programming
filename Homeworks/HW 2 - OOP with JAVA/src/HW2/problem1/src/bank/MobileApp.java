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

    BankSymmetricKey bsk;

    public Encrypted<BankSymmetricKey> sendSymKey(BankPublicKey publickey){
        bsk = new BankSymmetricKey(randomUniqueStringGen());
        Encrypted<BankSymmetricKey> eMsg = new Encrypted<BankSymmetricKey>(bsk, publickey);
        return eMsg;
    }

    public Encrypted<Message> deposit(int amount){
        Message msg = new Message("deposit", id, password, amount);
        Encrypted<Message> eMsg = new Encrypted<Message>(msg, bsk);
        return eMsg;
    }

    public Encrypted<Message> withdraw(int amount){
        Message msg = new Message("withdraw", id, password, amount);
        Encrypted<Message> eMsg = new Encrypted<Message>(msg, bsk);
        return eMsg;
    }

    public boolean processResponse(Encrypted<Boolean> obj){
        if(obj == null) return false;

        Object rsp = obj.decrypt(bsk);

        if(rsp == null) return false;
        else return (boolean) rsp;
    }

}

