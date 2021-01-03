import java.util.Queue;

public abstract class Worker {
    private static int numWorkers = 0;
    private final int id;
    Queue<Work> workQueue;

    public abstract void run();

    Worker(Queue<Work> workQueue) {
        this.workQueue = workQueue;
        id = numWorkers++;
    }

    void report(String msg){
        System.out.print("\b".repeat(300) +
                "[" + ">".repeat(workQueue.size()) + "] " + id + "-th Worker(" + getClass().getName() + ") " + msg);
    }
}

class Producer extends Worker {
    Producer(Queue<Work> workQueue) {
        super(workQueue);
    }

    public void run() {
        Work wk = new Work();
        workQueue.add(wk);
        report("produced work" + wk.getId());
    }
}

class Consumer extends Worker {
    Consumer(Queue<Work> workQueue) {
        super(workQueue);
    }

    public void run() {
        double prob = Math.random();
        if(3 * prob <= 1) {
            Work wk = workQueue.poll();
            if (wk != null) {
                report("consumed work" + wk.getId());
                return;
            }
        }
        report("failed to consume work");
    }
}
