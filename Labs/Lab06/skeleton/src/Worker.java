import java.util.Queue;

public abstract class Worker {
    private int id;
    Queue<Work> workQueue;

    public abstract void run();

    Worker(Queue<Work> workQueue) {
        // TODO: problem1
        this.workQueue = workQueue;
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
        // TODO: problem2
    }
}

class Consumer extends Worker {
    Consumer(Queue<Work> workQueue) {
        super(workQueue);
    }

    public void run() {
        // TODO: problem2
    }
}
