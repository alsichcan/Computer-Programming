package bank;

import bank.event.*;

class BankAccount {
    private Event[] events = new Event[maxEvents];
    final static int maxEvents = 100;

    BankAccount(String id, String password, int balance) {
        //TODO: Problem 1.1
    }

    boolean authenticate(String password) {
        //TODO: Problem 1.1
        return false;
    }

    void deposit(int amount) {
        //TODO: Problem 1.1
    }

    boolean withdraw(int amount) {
        //TODO: Problem 1.1
        return false;
    }

    void receive(int amount) {
        //TODO: Problem 1.1
    }

    boolean send(int amount) {
        //TODO: Problem 1.1
        return false;
    }

}
