package RandomizedQueuesAndDeques;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.introcs.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private Item[] items;
	private int size;
	
	public RandomizedQueue() {
		// construct an empty randomized queue
		items=(Item[]) new Object[1];
		size=0;
	}
	
	public void resize(int capacity){
		Item[] newitems=(Item[])new Object[capacity];
		for(int i=0;i<size;i++){
			newitems[i]=items[i];
		}
		items=newitems;
	}

	public boolean isEmpty() {
		// is the queue empty?
		return size<=0;
	}

	public int size() {
		// return the number of items on the queue
		return size;
	}

	public void enqueue(Item item) {
		// add the item
		if(item==null)
			throw new NullPointerException();
		if(items.length==size){
			resize(size*2);		
		}
		items[size++]=item;
			
	}

	public Item dequeue() {
		// delete and return a random item
		if(size==0)
			throw new NoSuchElementException();
		int dequeueIndex=StdRandom.uniform(size);
		Item dequeueItem=items[dequeueIndex];
		if(dequeueIndex==size-1)
			items[--size]=null;
		else{
		items[dequeueIndex]=items[--size];
		items[size]=null;
		}
		if(items.length/4>=size)
			resize(items.length/2);
		return dequeueItem;
	}

	public Item sample() {
		// return (but do not delete) a random item
		if(size==0)
			throw new NoSuchElementException();
		return items[StdRandom.uniform(size)];
	}

	public Iterator<Item> iterator() {
		// return an independent iterator over items in random order
		return new RdQueueIt();
	}
	
	private class RdQueueIt implements Iterator<Item> {
		int n=0;
		int[] index;
		public RdQueueIt() {
			// TODO Auto-generated constructor stub
			index=new int[size];
			for(int i=0;i<size;i++){
				index[i]=i;
			}
			StdRandom.shuffle(index);
		}
		
		public boolean hasNext(){			
			return n<size;
		}
		public void remove(){
				throw new UnsupportedOperationException();
		}
		public Item next(){
			if(!hasNext())
				throw new NoSuchElementException();
			return items[index[n++]];			
		}
	}
	// public static void main(String[] args) // unit testing
}
