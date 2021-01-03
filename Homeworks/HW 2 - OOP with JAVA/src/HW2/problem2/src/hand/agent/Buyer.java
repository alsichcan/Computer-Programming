package hand.agent;

public class Buyer extends Agent {

    public Buyer(double maximumPrice) {
        super(maximumPrice);
    }

    @Override
    public boolean willTransact(double price) {
        if(!hadTransaction && price <= expectedPrice){
            return true;
        } else{
            return false;
        }
    }

    @Override
    public void reflect() {
        if(hadTransaction){
            expectedPrice -= adjustment;
        } else{
            expectedPrice += adjustment;
            if(expectedPrice > priceLimit)
                expectedPrice = priceLimit;
        }
        hadTransaction = false;
    }
}
