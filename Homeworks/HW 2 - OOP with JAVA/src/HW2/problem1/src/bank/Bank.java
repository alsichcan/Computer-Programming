package bank;

import bank.event.Event;
import security.*;
import security.key.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Bank {
    private int numAccounts = 0;
    final static int maxAccounts = 100;
    private BankAccount[] accounts = new BankAccount[maxAccounts];
    private String[] ids = new String[maxAccounts];

    public void createAccount(String id, String password) {
        createAccount(id, password, 0);
    }

    public void createAccount(String id, String password, int initBalance) {
        int accountId = numAccounts;
        accounts[accountId] = new BankAccount(id, password, initBalance);
        ids[accountId] = id;
        numAccounts+=1;
    }

    public boolean deposit(String id, String password, int amount) {
        BankAccount ba = find(id);
        if(ba == null) return false;

        if(ba.authenticate(password)){
            ba.deposit(amount);
            return true;
        } else return false;
    }

    public boolean withdraw(String id, String password, int amount) {
        BankAccount ba = find(id);
        if(ba == null) return false;

        if(ba.authenticate(password)){
            return ba.withdraw(amount);
        } else return false;
    }

    public boolean transfer(String sourceId, String password, String targetId, int amount) {
        BankAccount Sba = find(sourceId);
        BankAccount Tba = find(targetId);

        if(Sba == null || Tba == null) return false;

        if(Sba.authenticate(password)){
            if(Tba != null && Sba.send(amount) ){
                Tba.receive(amount);
                return true;
            } else return false; // targetId is invalid or amount is larger than balance
        } else return false; // Authentication fail
    }

    public Event[] getEvents(String id, String password) {
        BankAccount ba = find(id);
        if(ba == null) return null;

        if(ba.authenticate(password)){
            ArrayList<Event> eventList = new ArrayList<>();
            for(Event event : ba.getEvents()){
                if(event == null) continue;

                eventList.add(event);
            }

            Event[] recordedEvents = new Event[eventList.size()];
            for(int i = 0; i < eventList.size(); i++){
                recordedEvents[i] = eventList.get(i);
            }
            return recordedEvents;
        } else return null;
    }

    public int getBalance(String id, String password) {
        BankAccount ba = find(id);
        if(ba == null) return -1;

        if(ba.authenticate(password)){
            return ba.getBalance();
        } else return -1;
    }

    private static String randomUniqueStringGen(){
        return Encryptor.randomUniqueStringGen();
    }
    private BankAccount find(String id) {
        for (int i = 0; i < numAccounts; i++) {
            if(ids[i].equals(id)){return accounts[i];};
        }
        return null;
    }
    final static int maxSessionKey = 100000;
    int numSessionKey = 0;
    String[] sessionKeyArr = new String[maxSessionKey];
    BankAccount[] bankAccountmap = new BankAccount[maxSessionKey];
    String generateSessionKey(String id, String password){
        BankAccount account = find(id);
        if(account == null || !account.authenticate(password)){
            return null;
        }
        String sessionkey = randomUniqueStringGen();
        sessionKeyArr[numSessionKey] = sessionkey;
        bankAccountmap[numSessionKey] = account;
        numSessionKey += 1;
        return sessionkey;
    }
    BankAccount getAccount(String sessionkey){
        for(int i = 0 ;i < numSessionKey; i++){
            if(sessionKeyArr[i] != null && sessionKeyArr[i].equals(sessionkey)){
                return bankAccountmap[i];
            }
        }
        return null;
    }

    boolean deposit(String sessionkey, int amount) {
        BankAccount ba = getAccount(sessionkey);
        if(ba == null) return false;

        ba.deposit(amount);
        return true;

    }

    boolean withdraw(String sessionkey, int amount) {
        BankAccount ba = getAccount(sessionkey);
        if(ba == null) return false;

        return ba.withdraw(amount);

    }

    boolean transfer(String sessionkey, String targetId, int amount) {
        BankAccount Sba = getAccount(sessionkey);
        if(Sba == null) return false;

        BankAccount Tba = find(targetId);

        if(Tba != null & Sba.send(amount)){
            Tba.receive(amount);
            return true;
        } else return false;
    }

    private BankSecretKey secretKey;
    public BankPublicKey getPublicKey(){
        BankKeyPair keypair = Encryptor.publicKeyGen();
        secretKey = keypair.deckey;
        return keypair.enckey;
    }

    private HashMap<String, BankSymmetricKey> handShakes = new HashMap<>();
    public void fetchSymKey(Encrypted<BankSymmetricKey> encryptedKey, String AppId){
        if(encryptedKey == null) return;

        BankSymmetricKey bsk = encryptedKey.decrypt(secretKey);
        if(bsk == null) return;

        handShakes.put(AppId, bsk);
    }

    public Encrypted<Boolean> processRequest(Encrypted<Message> messageEnc, String AppId){
        BankSymmetricKey bsk = handShakes.get(AppId);
        if(bsk == null) return null;

        Message msg = messageEnc.decrypt(bsk);
        if(msg == null) return null;

        String  rqt = msg.getRequestType();

        boolean result;
        if(rqt.equals("deposit")){
            result = deposit(msg.getId(), msg.getPassword(), msg.getAmount());
        } else if(rqt.equals("withdraw")){
            result = withdraw(msg.getId(), msg.getPassword(), msg.getAmount());
        } else{
            result = false;
        }

        Encrypted<Boolean> eResult = new Encrypted<Boolean>(result, bsk);
        return eResult;
    }


}