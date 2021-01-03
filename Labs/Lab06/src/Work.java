public class Work {
    private static int numWorks = 0;

    public int getId() {
        return id;
    }

    private final int id;

    Work() {
        id = numWorks++;
    }


}
