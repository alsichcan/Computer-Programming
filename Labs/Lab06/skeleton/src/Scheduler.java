import java.util.List;

public class Scheduler<T> {
    private static final int waitms = 400;

    T schedule(List<T> workers) {
        // TODO: problem3
        return workers.size() == 0 ? null : workers.get(0);
    }

    static void delay() {
        try {
            Thread.sleep(waitms);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
