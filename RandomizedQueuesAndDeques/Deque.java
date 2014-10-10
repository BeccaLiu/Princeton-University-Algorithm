package RandomizedQueuesAndDeques;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private Node first;
	private Node last;
	private int size;

	private class Node {
		Item item;
		Node next;
		Node pre;
	}

	public Deque() {
		// construct an empty deque
		first = null;
		last = null;
		size = 0;

	}

	public boolean isEmpty() { // is the deque empty?
		if (size == 0)
			return true;
		return false;

	}

	public int size() {
		// return the number of items on the deque
		return size;
	}

	public void addFirst(Item item) {
		// insert the item at the front
		if (item == null)
			throw new NoSuchElementException();
		Node newfirst = new Node();
		if (first == null)
			newfirst.next = null;
		else {
			newfirst.next = first;
			first.pre = newfirst;
		}
		newfirst.pre = null;
		first = newfirst;
		first.item = item;
		size++;
		if (last == null)
			last = first;
	}

	public void addLast(Item item) {
		// insert the item at the end
		if (item == null)
			throw new NoSuchElementException();
		Node newlast = new Node();
		if (last == null)
			newlast.pre = null;
		else {
			last.next = newlast;
			newlast.pre = last;
		}
		newlast.next = null;
		last = newlast;
		last.item = item;
		size++;
		if (first == null)
			first = last;

	}

	public Item removeFirst() {
		// delete and return the item at the front
		if (size == 0)
			throw new UnsupportedOperationException("empty deque");
		Item curfirst = first.item;
		if (first == last) {
			first = null;
			last = null;
		} else {
			first = first.next;
			first.pre = null;
		}
		size--;
		return curfirst;
	}

	public Item removeLast() {
		// delete and return the item at the end
		if (size == 0)
			throw new UnsupportedOperationException("empty deque");
		Item curlast = last.item;
		if (first == last) {
			first = null;
			last = null;
		} else {
			last = last.pre;
			last.next = null;
		}
		size--;
		return curlast;
	}

	public Iterator<Item> iterator() { // this is a method
		// return an iterator over items in order from front to end
		return new DequeIt();
	}

	private class DequeIt implements Iterator<Item> { // this is a private class
														// implements the
														// interface Iterator
		private Node current = first;

		public boolean hasNext() {
			return current != null;
		}

		public Item next() {
			if(!hasNext())
				throw new NoSuchElementException();
			Item curitem = current.item;
			current = current.next;
			return curitem;

		}
		public void remove() {
	        throw new UnsupportedOperationException("remove");
	    }

	}
	// public static void main(String[] args) // unit testing
}
