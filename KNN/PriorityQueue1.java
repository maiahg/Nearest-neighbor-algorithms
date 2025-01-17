import java.util.ArrayList;

public class PriorityQueue1<E extends LabelledPoint> implements PriorityQueueIF<LabelledPoint> {
    /* priority queue implemented with a simple array
    the max element is the last element in the array */

    private ArrayList<LabelledPoint> queue; // the array containing the elements
    public int size;              // the number of elements in the queue
    private int capacity;          // the number of neighbors

    // constructor that creates an empty queue
    public PriorityQueue1(int capacity) {
        this.capacity = capacity;
        this.queue = new ArrayList<>(capacity);
        this.size = 0;
    }

    // constructor that creates a queue from a specified ArrayList instance and a capacity
    public PriorityQueue1(ArrayList<LabelledPoint> Arraylist, int capacity) {
        this.capacity = capacity;
        this.queue = new ArrayList<>(capacity);
        this.size = 0;
        for (LabelledPoint p : Arraylist) {
            offer(p);
        }
    }

    // inserts the specified element into this queue if it is possible to do so immediately 
	// without violating capacity restrictions.
    public boolean offer(LabelledPoint p) {
        if (size < capacity) {
            queue.add(p);
            size++;
            return true;
        } else {
            if (p.getKey() < queue.get(size - 1).getKey()) {
                queue.set(size - 1, p);
                return true;
            } 
        }
        queue.sort((a, b) -> Double.compare(a.getKey(), b.getKey())); // sort the queue so that the max element is the last element 
        return false;
    }

    // retrieves and removes the max element (the last element), or returns null if this queue is empty.
    public LabelledPoint poll() {
        if (isEmpty()) {
            return null;
        } else {
            LabelledPoint max = queue.get(size - 1);
            queue.remove(size - 1);
            size--;
            return max;
        }
    }

    // retrieves, but does not remove, the max element (the last element), or returns null if this queue is empty.
    public LabelledPoint peek() {
        if (isEmpty()) {
            return null;
        } else {
            return queue.get(size - 1);
        }
    }

    // returns the number of elements in this queue.
    public int size() {
        return size;
    }

    // returns true if this queue contains no elements.
    public boolean isEmpty() {
        return size == 0;
    }
}



