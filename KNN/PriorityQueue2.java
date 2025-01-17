import java.util.ArrayList;

public class PriorityQueue2<E extends LabelledPoint> implements PriorityQueueIF<LabelledPoint>{
/* priority queue implemented with an array representing a heap
 * the max element is the root of the heap
 * each insertion in the heap will trigger a upheap operation
 *  each removal in the heap will trigger a downheap operation
 */

    private int capacity;         // the number of neighbors
    private ArrayList<LabelledPoint> heap; // the array containing the elements
    private int size;            // the number of elements in the queue

    // constructor that creates an empty queue
    public PriorityQueue2(int capacity) {
        this.capacity = capacity;
        this.heap = new ArrayList<>(capacity);
        this.size = 0;
    }

    // constructor that creates a queue from a specified ArrayList instance and a capacity
    public PriorityQueue2(ArrayList<LabelledPoint> Arraylist, int capacity) {
        this.capacity = capacity;
        this.heap = new ArrayList<>(capacity);
        this.size = 0;
        for (LabelledPoint p : Arraylist) {
            offer(p);
        }
    
    // inserts the specified element into this queue if it is possible to do so immediately
    // without violating capacity restrictions.
    }
    public boolean offer(LabelledPoint p) {
        if (size < capacity) {
            heap.add(p);
            upHeap(size);
            size++;
        } else {
            if (p.getKey() < heap.get(0).getKey()) {
                heap.set(0, p);
                downHeap(0);
            }
        }
        return true;

    // retrieves and removes the max element (the root), or returns null if this queue is empty.
    }
    public LabelledPoint poll() {
        if (isEmpty()) {
            return null;
        } else {
            LabelledPoint max = heap.get(0);
            heap.set(0, heap.get(size - 1));
            heap.remove(size - 1);
            size--;
            downHeap(0);
            return max;
        }

    }

    // retrieves, but does not remove, the max element (the root), or returns null if this queue is empty.
    public LabelledPoint peek() {
        if (isEmpty()) {
            return null;
        } else {
            return heap.get(0);
        }
    }

    // returns true if this queue contains no elements.
    public boolean isEmpty() {
        return size == 0;
    }

    // returns the number of elements in this queue.
    public int size() {
        return size;
    }

    // upHeap method
    private void upHeap(int index) {
        if (index == 0) {
            return;
        }
        int parentIndex = (index - 1) / 2;
        if (heap.get(index).getKey() > heap.get(parentIndex).getKey()) {
            swap(index, parentIndex);
            upHeap(parentIndex);
        }
    }

    // downHeap method
    private void downHeap(int index) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        int maxIndex = index;

        if (leftChildIndex < size && heap.get(leftChildIndex).getKey() > heap.get(maxIndex).getKey()) {
            maxIndex = leftChildIndex;
        }
        if (rightChildIndex < size && heap.get(rightChildIndex).getKey() > heap.get(maxIndex).getKey()) {
            maxIndex = rightChildIndex;
        }
        if (maxIndex != index) {
            swap(index, maxIndex);
            downHeap(maxIndex);
        }

    }

    // swap two elements in the heap
    private void swap(int index1, int index2) {
        LabelledPoint temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

}
