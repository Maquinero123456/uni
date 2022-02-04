/**
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 *
 * Binary Search trees implementation
 */
 
package dataStructures.searchTree;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.UnaryOperator;

import dataStructures.either.Either;
import dataStructures.either.Left;
import dataStructures.either.Right;
import dataStructures.stack.LinkedStack;
import dataStructures.stack.Stack;
import dataStructures.tuple.Tuple2;

/**
 * Search tree implemented using an unbalanced binary search tree. Note that keys should define an
 * order relation ({@link java.lang.Comparable}).
 * @param <K> Type of keys.
 * @param <V> Type of values.
 */
public class BST<K extends Comparable<? super K>, V> implements SearchTree<K, V> {
	private static class Tree<K, V> {
		K key;
		V value;
		Tree<K, V> left;
		Tree<K, V> right;

		public Tree(K k, V v) {
			key = k;
			value = v;
			left = null;
			right = null;
		}
	}

	private Tree<K, V> root;
	private int size;

	/**
	 * Creates an empty unbalanced binary search tree.
	 * <p>Time complexity: O(1)
	 */
	public BST() {
		root = null;
		size = 0;
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
		return size;
	}	
	
	
	/** 
	 * {@inheritDoc}
	 * <p>Time complexity: from O(log n) to O(n)
	 */
	public int height() {
		return heightRec(root);
	}	
	
	private static int heightRec(Tree<?,?> tree) {
		return tree==null ? 0 : 1 + Math.max(heightRec(tree.left), heightRec(tree.right));
	}	
	
	/** 
	 * {@inheritDoc}
	 * <p>Time complexity: from O(log n) to O(n)
	 */
	public void insert(K k, V v) {
		root = insertRec(root, k, v);
	}

	// returns modified tree
	private Tree<K, V> insertRec(Tree<K, V> node, K key, V value) {
		if (node == null) {
			node = new Tree<>(key, value);
			size++;
		} else if (key.compareTo(node.key) < 0)
			node.left = insertRec(node.left, key, value);
		else if (key.compareTo(node.key) > 0)
			node.right = insertRec(node.right, key, value);
		else
			node.value = value;
		return node;
	}

	/** 
	 * {@inheritDoc}
	 * <p>Time complexity: from O(log n) to O(n)
	 */
	public V search(K key) {
		return searchRec(root, key);
	}

	private static <K extends Comparable<? super K>,V> 
	        V searchRec(Tree<K, V> tree, K key) {
		if (tree == null)
			return null; 
		else if (key.compareTo(tree.key) < 0)
			return searchRec(tree.left, key);
		else if (key.compareTo(tree.key) > 0)
			return searchRec(tree.right, key);
		else 
			return tree.value;
	}

	/** 
	 * {@inheritDoc}
	 * <p>Time complexity: from O(log n) to O(n)
	 */
	public boolean isElem(K key) {
		return search(key) != null;
	}

	// pre: node is a non-empty tree
	// Removes minimum key (and value) from tree rooted at node. Before
	// deletion, key and value are saved into temp node.
	// returns modified tree (without min key and value)
	private static <K extends Comparable<? super K>,V> 
	        Tree<K, V> split(Tree<K, V> node, Tree<K, V> temp) {
		if (node.left == null) {
			// min node found, so copy min key and value
			temp.key = node.key;
			temp.value = node.value;
			return node.right; // remove node
		} else {
			// remove min from left subtree
			node.left = split(node.left, temp);
			return node;
		}
	}

	/** 
	 * {@inheritDoc}
	 * <p>Time complexity: from O(log n) to O(n)
	 */
	public void delete(K key) {
		root = deleteRec(root, key);
	}

	// returns modified tree
	private Tree<K, V> deleteRec(Tree<K, V> node, K key) {
		if (node == null)
			; // key not found; do nothing
		else if (key.compareTo(node.key) < 0)
			node.left = deleteRec(node.left, key);
		else if (key.compareTo(node.key) > 0)
			node.right = deleteRec(node.right, key);
		else {
			if (node.left == null)
				node = node.right;
			else if (node.right == null)
				node = node.left;
			else
				node.right = split(node.right, node);
			size--;
		}		
		return node;
	}

	/**
	 * {@inheritDoc}
	 * <p>Time complexity: from O(log n) to O(n)
	 */
	public V minim() {
		if (root == null)
			throw new EmptySearchTreeException("minim on empty tree");
		else {
			Tree<K,V> node = root;
			while (node.left != null)
				node = node.left;
			return node.value;
		}
	}

	/**
	 * {@inheritDoc}
	 * <p>Time complexity: from O(log n) to O(n)
	 */
	public V maxim() {
		if (root == null)
			throw new EmptySearchTreeException("maxim on empty tree");
		else {
			Tree<K,V> node = root;
			while (node.right != null)
				node = node.right;
			return node.value;
		}
	}

	/**
	 * {@inheritDoc}
	 * <p>Time complexity: from O(log n) to O(n)
	 */
	public void deleteMinim() {
		if (isEmpty())
			throw new EmptySearchTreeException("deleteMinim on empty tree");
		else {
			Tree<K,V> parent = null;
			Tree<K,V> node = root;
			while (node.left != null) {
				parent = node;
				node = node.left;
			}
			if (parent == null)
				root = root.right;
			else
				parent.left = node.right;
			size--;
		}
	}

	/**
	 * {@inheritDoc}
	 * <p>Time complexity: from O(log n) to O(n)
	 */
	public void deleteMaxim() {
		if (isEmpty())
			throw new EmptySearchTreeException("deleteMaxim on empty tree");
		else {
			Tree<K,V> parent = null;
			Tree<K,V> node = root;
			while (node.right != null) {
				parent = node;
				node = node.right;
			}
			if (parent == null)
				root = root.left;
			else
				parent.right = node.left;
			size--;
		}
	}

	/**
	 * {@inheritDoc}
	 * <p>Time complexity: from O(log n) to O(n)
	 */
	public void updateOrInsert(UnaryOperator<V> f, K k, V v) {
		root = updateOrInsertRec(root, f, k, v);
	}

	// returns modified tree
	private Tree<K, V> updateOrInsertRec(Tree<K, V> node, UnaryOperator<V> f, K key, V value) {
		if (node == null) {
			node = new Tree<>(key, value);
			size++;
		} else if (key.compareTo(node.key) < 0)
			node.left = updateOrInsertRec(node.left, f, key, value);
		else if (key.compareTo(node.key) > 0)
			node.right = updateOrInsertRec(node.right, f, key, value);
		else
			node.value = f.apply(node.value);
		return node;
	}

	// Almost an iterator on nodes in tree
	private abstract class Traversal {
		Stack<Either<Tree<K,V>, Tree<K,V>>> stack = new LinkedStack<>();

		abstract void save(Tree<K,V> node);

		public Traversal() {
			if (root != null)
				save(root);
		}

		public boolean hasNext() {
			return !stack.isEmpty();
		}

		public Tree<K,V> nextTree() {
			if (!hasNext())
				throw new NoSuchElementException();

			Either<Tree<K,V>, Tree<K,V>> either = stack.top();
			stack.pop();

			while (either.isRight()) {
				Tree<K,V> node = either.right();
				save(node);
				either = stack.top();
				stack.pop();
			}
			return either.left();
		}
	}

	private class InOrderTraversal extends Traversal {
		void save(Tree<K,V> node) {
			// in reverse order, cause stack is LIFO
			if (node.right != null)
				stack.push(new Right<>(node.right));
			stack.push(new Left<>(node));
			if (node.left != null)
				stack.push(new Right<>(node.left));
		}
	}
	
	private class PreOrderTraversal extends Traversal {
		void save(Tree<K,V> node) {
			// in reverse order, cause stack is LIFO
			if (node.right != null)
				stack.push(new Right<>(node.right));
			if (node.left != null)
				stack.push(new Right<>(node.left));
			stack.push(new Left<>(node));
		}
	}
	
	private class PostOrderTraversal extends Traversal {
		void save(Tree<K,V> node) {
			// in reverse order, cause stack is LIFO
			stack.push(new Left<>(node));
			if (node.right != null)
				stack.push(new Right<>(node.right));
			if (node.left != null)
				stack.push(new Right<>(node.left));
		}
	}
	
	private class InOrderIt extends InOrderTraversal implements Iterator<K> {
		public K next() {
			return super.nextTree().key;
		}
	}

	private class PreOrderIt extends PreOrderTraversal implements Iterator<K> {
		public K next() {
			return super.nextTree().key;
		}
	}

	private class PostOrderIt extends PostOrderTraversal implements Iterator<K> {
		public K next() {
			return super.nextTree().key;
		}
	}
	
	public Iterable<K> inOrder() {
		return new Iterable<K>(){
			public Iterator<K> iterator() {
				return new InOrderIt();
			}
		};
	}
	
	public Iterable<K> preOrder() {
		return new Iterable<K>(){
			public Iterator<K> iterator() {
				return new PreOrderIt();
			}
		};
	}

	public Iterable<K> postOrder() {
		return new Iterable<K>(){
			public Iterator<K> iterator() {
				return new PostOrderIt();
			}
		};
	}	
	
	
	private class ValuesIt extends InOrderTraversal implements Iterator<V> {
		public V next() {
			return super.nextTree().value;
		}
	}
	
	public Iterable<V> values() {
		return new Iterable<V>(){
			public Iterator<V> iterator() {
				return new ValuesIt();
			}
		};
	}
	
	private class KeysValuesIt extends InOrderTraversal implements Iterator<Tuple2<K,V>> {
		public Tuple2<K,V> next() {
			Tree<K,V> node = super.nextTree();
			return new Tuple2<>(node.key, node.value);
		}
	}
	
	public Iterable<Tuple2<K,V>> keysValues() {
		return new Iterable<Tuple2<K,V>>(){
			public Iterator<Tuple2<K,V>> iterator() {
				return new KeysValuesIt();
			}
		};
	}
	
	private static String toStringRec(Tree<?, ?> tree) {
		return tree == null ? "null" : "Node<" + toStringRec(tree.left) + ","
				+ tree.key + "," + tree.value + "," + toStringRec(tree.right) + ">";
	}
	
	/** 
	 * Returns representation of tree as a String.
	 */
  @Override public String toString() {
    String className = getClass().getSimpleName();
  	return className+"("+toStringRec(this.root)+")";
  }	
	
}

