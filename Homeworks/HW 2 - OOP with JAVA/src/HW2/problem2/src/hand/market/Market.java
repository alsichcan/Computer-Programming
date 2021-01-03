package hand.market;

import hand.agent.Agent;
import hand.agent.Buyer;
import hand.agent.Seller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class Pair<K,V> {
    public K key;
    public V value;
    Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

public class Market {
    public ArrayList<Buyer> buyers;
    public ArrayList<Seller> sellers;

    public Market(int nb, ArrayList<Double> fb, int ns, ArrayList<Double> fs) {
        buyers = createBuyers(nb, fb);
        sellers = createSellers(ns, fs);
    }
    
    private ArrayList<Buyer> createBuyers(int n, ArrayList<Double> f) {
        ArrayList<Buyer> bList = new ArrayList<>();
        for(int i = 1; i <= n ; i++){
            double maxPrice = 0;
            double xVal = (double) i / n;
            for(int k = 0; k < f.size(); k++){
                maxPrice += f.get(k) * Math.pow(xVal, f.size()-k-1);
            }
            bList.add(new Buyer(maxPrice));
        }
        return bList;
    }

    private ArrayList<Seller> createSellers(int n, ArrayList<Double> f) {
        ArrayList<Seller> sList = new ArrayList<>();
        for(int i = 1; i <= n; i++){
            double minPrice = 0;
            double xVal = (double) i/n;
            for(int k = 0; k < f.size(); k++){
                minPrice += f.get(k) * Math.pow(xVal, f.size()-k-1);
            }
            sList.add(new Seller(minPrice));
        }
        return sList;
    }

    private ArrayList<Pair<Seller, Buyer>> matchedPairs(int day, int round) {
        ArrayList<Seller> shuffledSellers = new ArrayList<>(sellers);
        ArrayList<Buyer> shuffledBuyers = new ArrayList<>(buyers);
        Collections.shuffle(shuffledSellers, new Random(71 * day + 43 * round + 7));
        Collections.shuffle(shuffledBuyers, new Random(67 * day + 29 * round + 11));
        ArrayList<Pair<Seller, Buyer>> pairs = new ArrayList<>();
        for (int i = 0; i < shuffledBuyers.size(); i++) {
            if (i < shuffledSellers.size()) {
                pairs.add(new Pair<>(shuffledSellers.get(i), shuffledBuyers.get(i)));
            }
        }
        return pairs;
    }


    public double simulate() {
        boolean lastDay = false;
        ArrayList<Double> priceList = new ArrayList<>();
        for (int day = 1; day <= 1000; day++) { // do not change this line
            if(day == 1000) lastDay = true;
            for (int round = 1; round <= 10; round++) { // do not change this line
                ArrayList<Pair<Seller, Buyer>> pairs = matchedPairs(day, round); // do not change this line
                for(Pair<Seller, Buyer> pair : pairs){
                    Seller s = pair.key;
                    Buyer b = pair.value;

                    if(s.isHadTransaction()) continue;

                    if(b.willTransact(s.getExpectedPrice())){
                        s.makeTransaction();
                        b.makeTransaction();

                        if(lastDay)
                            priceList.add(s.getExpectedPrice());
                    }
                }
            }
            for(Buyer b : buyers) b.reflect();
            for(Seller s : sellers) s.reflect();
        }

        double total = 0;
        for(Double price : priceList){
            total += price;
        }
        return total / priceList.size();
    }
}
