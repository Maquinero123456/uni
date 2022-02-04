/**
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 *
 * Weigth Biased Leftist Heaps
 */
 
package dataStructures.heap;


/**
 * Heap implemented using weight biased leftist heap-ordered binary trees.
 * @param <T> Type of elements in heap.
 */
public class WBLeftistHeap<T extends Comparable<? super T>> implements Heap<T>{

	private static class Tree<E> {
		E elem;
		int weight; // number of elements on tree
		Tree<E> left;
		Tree<E> right;
	}

  private Tree<T> root; // reference to root of this heap

	private static<T> int weight(Tree<T> t) {
		return t==null ? 0 : t.weight;
	}

  
	// Merges two heap trees along their right spines.
	// Returns merged heap.
	// Reuses nodes during merge
	private static <T extends Comparable<? super T>> Tree<T> merge(Tree<T> h1,	Tree<T> h2) {
		if (h1 == null)
			return h2;
		if (h2 == null)
			return h1;

		// force heap1 to have smaller root
		if (h2.elem.compareTo(h1.elem) < 0) {
			// swap heap1 and heap2
			Tree<T> tmp = h1;
			h1 = h2;
			h2 = tmp;
		}

		// keep merging along right spine
		h1.right = merge(h1.right, h2);
		
		int wL = weight(h1.left);
		int wR = weight(h1.right);
		h1.weight = wL + wR + 1; // set new weight

		// put always heavier heap on left side
		if (wL < wR) {
			Tree<T> aux = h1.left;
			h1.left = h1.right;
			h1.right = aux;
		}

		return h1;
	}	
	
	// copies a tree
	private static <T extends Comparable<? super T>> Tree<T> copy(Tree<T> h) {
		if (h==null)
			return null;
		else {
			Tree<T> h1 = new Tree<>();
			h1.elem = h.elem;
			h1.weight = h.weight;
			h1.left = copy(h.left);
			h1.right = copy(h.right);			
			return h1;		
		}
	}
	
	/**
	 * Creates an empty Weigth Biased Leftist Heap. 
	 * <p>Time complexity: O(1)
	 */
	public WBLeftistHeap() {
		root = null;
	}
	
	/**
	 * Copy constructor for Weigth Biased Leftist Heaps. 
	 * <p>Time complexity: O(n)
	 */	
	public WBLeftistHeap(WBLeftistHeap<T> h) {
		root = copy(h.root);
	}
	
	
	/** 
	 * {@inheritDoc}
	 * <p>Time complexity: O(1)
	 */
	public boolean isEmpty() {
		return root == null;
	}
	
	/** 
	 * {@inheritDoc}
	 * <p>Time complexity: O(1)
	 */
	public int size() {
		return isEmpty() ? 0 : root.weight;
	}

	/** 
	 * {@inheritDoc}
	 * <p>Time complexity: O(1)
	 * @throws <code>EmptyHeapException</code> if heap stores no element.
	 */
	public T minElem() {
		if(isEmpty())
			throw new EmptyHeapException("minElem on empty heap");
		else
			return root.elem;
	}

	/** 
	 * {@inheritDoc}
	 * <p>Time complexity: O(log n)
	 * @throws <code>EmptyHeapException</code> if heap stores no element.
	 */
	public void delMin() {
		if(isEmpty())
			throw new EmptyHeapException("delMin on empty heap");
		else
			root = merge(root.left,root.right);
	}
	
	/** 
	 * {@inheritDoc}
	 * <p>Time complexity: O(log n)
	 */
	public void insert(T x) {
		Tree<T> newHeap = new Tree<>();
		newHeap.elem = x;
		newHeap.weight = 1;
		newHeap.left = null;
		newHeap.right = null;
		
		root = merge(root, newHeap);
	} 

	public void clear() {
		root = null;
	}	
	
	private static String toStringRec(Tree<?> tree) {
		return tree == null ? "null" : "Node<" + toStringRec(tree.left) + ","
				+ tree.elem + "," + toStringRec(tree.right) + ">";
	}
	
	/** 
	 * Returns representation of heap as a String.
	 */
  @Override public String toString() {
    String className = getClass().getSimpleName();

  	return className+"("+toStringRec(this.root)+")";
  }	
	
}


