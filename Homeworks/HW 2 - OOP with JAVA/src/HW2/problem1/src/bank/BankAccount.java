package bank;

import bank.event.*;

class BankAccount {
    private Event[] events = new Event[maxEvents];
    final static int maxEvents = 100;
    private int eventNum = 0;

    private String id;
    private String password;
    private int balance;

    BankAccount(String id, String password, int balance) {
        this.id = id;
        this.password = password;
        this.balance = balance;
    }

    boolean authenticate(String password) {
        if(password.equals(this.password))
            return true;
        else return false;
    }

    void deposit(int amount) {
        this.balance += amount;
        events[eventNum] = new DepositEvent();
        eventNum++;
    }

    boolean withdraw(int amount) {
        if(this.balance >= amount){
            this.balance -= amount;
            events[eventNum] = new WithdrawEvent();
            eventNum++;
            return true;
        } else return false;
    }

    void receive(int amount) {
        this.balance += amount;
        events[eventNum] = new ReceiveEvent();
        eventNum++;
    }

    boolean send(int amount) {
        if(this.balance >= amount){
            this.balance -= amount;
            events[eventNum] = new SendEvent();
            eventNum++;
            return true;
        } else return false;
    }

    int getBalance(){
        return this.balance;
    }

    Event[] getEvents(){
        return this.events;
    }


}
