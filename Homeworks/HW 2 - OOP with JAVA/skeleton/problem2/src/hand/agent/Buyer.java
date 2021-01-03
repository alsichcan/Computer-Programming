package hand.agent;

public class Buyer extends Agent {

    public Buyer(double maximumPrice) {
        super(maximumPrice);
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
