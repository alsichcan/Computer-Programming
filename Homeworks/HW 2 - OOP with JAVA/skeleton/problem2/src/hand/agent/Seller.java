package hand.agent;

public class Seller extends Agent {

    public Seller(double minimumPrice) {
        super(minimumPrice);
    }

    @Override
    public boolean willTransact(double price) {
        // TODO sub-problem 1
        return false;
    }

    @Override
    public void reflect() {
        // TODO sub-problem 1
        return;
    }
}
