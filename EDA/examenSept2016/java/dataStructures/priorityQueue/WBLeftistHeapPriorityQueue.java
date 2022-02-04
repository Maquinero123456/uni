/**
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 *
 * Priority queues implemented with Weight Biased Leftist Heaps
 */

package dataStructures.priorityQueue;

import dataStructures.heap.WBLeftistHeap;

/**
 * Priority queue with a Weight Biased Leftist Heap. 
 *
 * @param <T>
 */
public class WBLeftistHeapPriorityQueue<T extends Comparable<? super T>> implements PriorityQueue<T> {

	private WBLeftistHeap<T> heap;	
	
	/**
	 * Creates an empty queue.
	 */	
	public WBLeftistHeapPriorityQueue() {
		heap = new WBLeftistHeap<>();
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
			throw new EmptyPriorityQueueException("dequeue on empty priority queue");
		else
			heap.delMin();
	}		
	
	/** 
	 * Returns representation of priority queue as a String.
	 */
	@Override public String toString() {
		WBLeftistHeap<T> clonedHeap = new WBLeftistHeap<>(heap);
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
