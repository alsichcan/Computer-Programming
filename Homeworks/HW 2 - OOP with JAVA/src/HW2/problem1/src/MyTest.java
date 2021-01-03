import bank.Bank;
import bank.MobileApp;
import bank.event.*;
import security.Protocol;
import security.method.*;
import bank.Session;
import bank.SessionManager;

import java.util.Arrays;
import java.util.Random;

public class MyTest {
    static Random random = new Random(23);

    public static void main(String[] args) {
        testAll();
    }

    static void testAll() {
        Bank bank = new Bank();

        // Create a total of 100 accounts (unique id for each bank account is assumed)
        for (int i = 0; i < 100; i++) {
            String idx = Integer.toString(i);
            bank.createAccount(idx, idx, getNextPositiveInt());
        }

        // Create sessions (multiple sessions for the same person are possible)
        Session[] sessions = new Session[100];
        for (int i = 0; i < 100; i++) {
            String id = getRandomId();
            sessions[i] = SessionManager.generateSession(id, id, bank);
        }

        // Create mobile apps (multiple apps for the same person are possible)
        MobileApp[] apps = new MobileApp[100];
        for (int i = 0; i < 100; i++) {
            String id = getRandomId();
            apps[i] = new MobileApp(id, id);
        }

        // Simulate transactions between accounts
        for (int i = 0; i < 1000; i++) {
            // simulate direct access to bank
            simulate(bank);

            // simulate session access to bank
            Session session = sessions[random.nextInt(100)];
            simulate(session);

            // simulate secure transactions with mobile apps
            MobileApp app = apps[random.nextInt(100)];
            simulate(app, bank);

            if (i % 10 == 0) {
                String id = getRandomId();
                System.out.println(bank.getBalance(id, id));
                System.out.println(stringify(bank.getEvents(id, id)));
            }
        }
    }

    static String stringify(Event[] events) {
        if (events.length == 0) {
            return "None";
        }
        StringBuilder retVal = new StringBuilder();
        for (Event event : events) {
            retVal.append(event.getClass().getSimpleName().toLowerCase().charAt(0));
            retVal.append(",");
        }
        return retVal.toString();
    }

    static String getRandomId() {
        return Integer.toString(random.nextInt(100));
    }

    static int getNextPositiveInt() {
        return random.nextInt(100000);
    }

    static void simulate(Bank bank) {
        String id = getRandomId();
        String pw = id;
        if (random.nextDouble() < 0.3) {
            pw += "mistake";
        }
        double prob = random.nextDouble();
        if (prob < 1.0 / 3) {
            bank.deposit(id, pw, getNextPositiveInt());
        } else if (prob < 2.0 / 3) {
            bank.withdraw(id, pw, getNextPositiveInt());
        } else {
            bank.transfer(id, pw, getRandomId(), getNextPositiveInt());
        }
    }

    static void simulate(Session session) {
        double prob = random.nextDouble();
        if (prob < 1.0 / 3) {
            session.deposit(getNextPositiveInt());
        } else if (prob < 2.0 / 3) {
            session.deposit(getNextPositiveInt());
        } else {
            session.transfer(getRandomId(), getNextPositiveInt());
        }
        if (random.nextDouble() < 0.1) {
            SessionManager.expireSession(session);
        }
    }

    static void simulate(MobileApp app, Bank bank) {
        if (random.nextDouble() < 1) {
            Protocol.handshake(app, bank);
        } // 10% chance of handshake failure

        double prob = random.nextDouble();
        if (prob < 0.5) {
            Protocol.communicate(new Deposit(), app, bank, getNextPositiveInt());
        } else {
            Protocol.communicate(new Withdraw(), app, bank, getNextPositiveInt());
        }
    }
}
