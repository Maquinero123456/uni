/**
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 *
 * Priority queues implemented with Binary Heaps
 */

package dataStructures.priorityQueue;

import dataStructures.heap.BinaryHeap;

/**
 * Priority queue with a Binary Heap. 
 *
 * @param <T>
 */
public class BinaryHeapPriorityQueue<T extends Comparable<? super T>> implements PriorityQueue<T> {

	private BinaryHeap<T> heap;	
	
	/**
	 * Creates an empty queue.
	 * <p>Time complexity: O(1)
	 */	
	public BinaryHeapPriorityQueue() {
		heap = new BinaryHeap<>();
	}
	
	/** 
	 * {@inheritDoc}
	 * <p>Time complexity: O(1)
	 */
	public boolean isEmpty() {
		return heap.isEmpty();
	}

	/** 
	 * {@inheritDoc}
	 * Position of new element in queue depends on its priority.
	 * The less the value of the element, the higher its priority. 
	 * <p>Time complexity: O(log n)
	 */
	public void enqueue(T x) {
		heap.insert(x);
	}

	/** 
	 * {@inheritDoc}
	 * <p>Time complexity: O(1)
	 * @throws <code>EmptyQueueException</code> if queue stores no element.
	 */
	public T first() {
		if(isEmpty())
			throw new EmptyPriorityQueueException("first on empty priority queue");
		else
			return heap.minElem();
	}

	/** 
	 * {@inheritDoc}
	 * Position of new element in queue depends on its priority.
	 * The less the value of the element, the higher its priority. 
	 * <p>Time complexity: O(log n)
	 */
	public void dequeue() {
		if(isEmpty())
			throw new EmptyPriorityQueueException("first on empty priority queue");
		else
			heap.delMin();
	}		
	
	/** 
	 * Returns representation of priority queue as a String.
	 */
	@Override public String toString() {
		BinaryHeap<T> clonedHeap = new BinaryHeap<>(heap);
		String className = getClass().getSimpleName();
		String s = className+"(";
		boolean stop = clonedHeap.isEmpty();
		while(!stop) {
			s += clonedHeap.minElem();
			clonedHeap.delMin();
			stop = clonedHeap.isEmpty();
			if(!stop)
				s += ",";
		}
		s += ")";
		return s;			
	}
}
