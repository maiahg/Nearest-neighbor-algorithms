import java.util.ArrayList;
import java.util.PriorityQueue;

public class PriorityQueue3 implements PriorityQueueIF<LabelledPoint> {
    // using the java.PriorityQueue class as an instance variable
    private PriorityQueue<LabelledPoint> queue;
    private int capacity;

    // constructor that creates an empty queue
    public PriorityQueue3(int capacity) {
        this.capacity = capacity;
        this.queue = new PriorityQueue<LabelledPoint>((a, b) -> Double.compare(b.getKey(), a.getKey()));
        };

    // constructor that creates a queue from a specified ArrayList instance and a capacity
    public PriorityQueue3(ArrayList<LabelledPoint> Arraylist, int capacity) {
        this.capacity = capacity;
        this.queue = new PriorityQueue<LabelledPoint>((a, b) -> Double.compare(b.getKey(), a.getKey()));
        
        for (LabelledPoint p : Arraylist) {
            offer(p);
        }
    }

    // inserts the specified element into this queue if it is possible to do so immediately
    // without violating capacity restrictions.
    public boolean offer(LabelledPoint p) {
        queue.offer(p);
        if (queue.size() > capacity) {
            queue.poll();
        }
        return true;
    }

    // retrieves and removes the max element, or returns null if this queue is empty.
    public LabelledPoint poll() {
        return queue.poll();
    }

    // retrieves, but does not remove, the max element, or returns null if this queue is empty.
    public LabelledPoint peek() {
        return queue.peek();
    }

    // returns the number of elements in this queue.
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    // returns true if this queue contains no elements.
    public int size() {
        return queue.size();
    }
}
