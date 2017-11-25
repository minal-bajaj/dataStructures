package com.minal;


public class MinHeap<T extends Comparable<T>> {
	
	private int position;
	private int capacity;
	
	private T[] array;
	
	@SuppressWarnings("unchecked")
	public MinHeap(int _capacity) {
		this.position = 0;
		this.capacity = _capacity;
		this.array = (T[]) new Comparable[this.capacity];
	}
	
	private boolean isFull() {
		return this.position == this.capacity;
	}
	
	private int parent(int i) {
		return (i - 1) / 2;
	}
	
	private int left(int i) {
		return (2 * i + 1) / 2;
	}
	
	private int right(int i) {
		return (2* i + 2) / 2;
	}
	
	private void swap(int a, int b) {
		T temp = this.array[a];
		this.array[a] = this.array[b];
		this.array[b] = temp;
	}
	
	//--------------public APIs------------
	public void insert(T _element) {
		if (this.isFull()) {
			// throw error.
			return;
		}
		
		this.position++;
		int i = this.position - 1;
		this.array[i] = _element;
		
		while (i > 0 && (this.array[parent(i)].compareTo(this.array[i]) > 0)) {
			this.swap(i, parent(i));
			i = parent(i);
		}		
	}
	
	public T getMin() {
		
		if (this.position == 0) {
			return null;
		}
		
		return this.array[0];
	}
	
	public T extractMin() {
		T min = this.getMin();
		
		if (min == null) {
			return min;
		}
		
		this.array[0] = this.array[this.position - 1];
		this.position--;
		
		this.minHeapify(0);
		
		return min;
	}
	
	private void minHeapify(int i) {
		
		int smallest = i;
		
		int l = left(i);
		int r = right(i);
		
		if (l < this.position && this.array[l].compareTo(this.array[i]) < 0) {
			smallest = l;
		}
		
		if (r < this.position && this.array[r].compareTo(this.array[i]) < 0) {
			smallest = r;
		}
		
		if (smallest != i) {
			swap(i, smallest);
			minHeapify(smallest);
		}
	}
	
	public static void main(String[] args) {
		
		MinHeap<Integer> minHeap = new MinHeap<>(20);
		minHeap.insert(1);
		minHeap.insert(3);
		minHeap.insert(2);
		minHeap.insert(5);
		
		System.out.println(minHeap.extractMin());
		System.out.println(minHeap.extractMin());
		System.out.println(minHeap.extractMin());
		System.out.println(minHeap.extractMin());
		System.out.println(minHeap.extractMin());
		
	}
}