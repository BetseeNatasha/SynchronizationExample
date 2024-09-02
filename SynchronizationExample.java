// Shared resource class with synchronization
class Counter {
    private int count = 0;

    // Synchronized method to ensure thread-safe access
    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}

// Thread class that uses the shared counter
class IncrementThread extends Thread {
    private Counter counter;

    public IncrementThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.increment();
        }
    }
}

public class SynchronizationExample {
    public static void main(String[] args) {
        // Create shared counter
        Counter counter = new Counter();

        // Create and start threads
        Thread t1 = new IncrementThread(counter);
        Thread t2 = new IncrementThread(counter);
        Thread t3 = new IncrementThread(counter);
        Thread t4 = new IncrementThread(counter);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            // Wait for all threads to finish
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Display the final count
        System.out.println("Final count: " + counter.getCount());
    }
}
