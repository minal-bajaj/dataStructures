package com.minal;

public class Heap<T extends Comparable<T>> {
	
	private HeapType type;
	private int position;
	private int capacity;
	private T[] array;
	
	public Heap(HeapType _type, int _capacity) {
		this.type = _type;
		this.position = 0;
		this.capacity = _capacity;
		this.array = (T[]) new Comparable[this.capacity];
	}
	
	private int parent(int i) {
		return (i - 1) / 2;
	}
	
	private int left(int i) {
		return (2 * i + 1);
	}
	
	private int right(int i) {
		return (2* i + 2);
	}

	private boolean isFull() {
		return this.position == this.capacity;
	}
	
	private void swap(int a, int b) {
		T temp = this.array[a];
		this.array[a] = this.array[b];
		this.array[b] = temp;
	}

	private int compare(T _thisElement, T _thatElement) {
		int difference = _thisElement.compareTo(_thatElement);
		
		return this.type.getMagnitude() * difference;
	}
	
	public void insert(T _element) {
		if (this.isFull()) {
			// throw error.
			return;
		}
		
		this.position++;
		int i = this.position - 1;
		this.array[i] = _element;
		
		while (i > 0 && this.compare(this.array[parent(i)],this.array[i]) > 0) {
			this.swap(i, parent(i));
			i = parent(i);
		}	
	}
	
	public T getRoot() {
		
		if (this.position == 0) {
			return null;
		}
		
		return this.array[0];
	}
	
	public T extractRoot() {
		T min = this.getRoot();
		
		if (min == null) {
			return min;
		}
		
		this.array[0] = this.array[this.position - 1];
		this.position--;
		
		this.heapify(0);
		
		return min;
	}
	
	private void heapify(int i) {
		
		int smallest = i;
		
		int l = left(i);
		int r = right(i);
		
		if (l < this.position && this.compare(this.array[l], this.array[i]) < 0) {
			smallest = l;
		}
		
		if (r < this.position && this.compare(array[r], this.array[i]) < 0) {
			smallest = r;
		}
		
		if (smallest != i) {
			swap(i, smallest);
			heapify(smallest);
		}
	}
	
	public static void main(String[] args) {
		
		Heap<Integer> minHeap = new Heap<>(HeapType.MIN, 10);
		minHeap.insert(1);
		minHeap.insert(3);
		minHeap.insert(2);
		minHeap.insert(5);

		minHeap.insert(9);

		minHeap.insert(0);
		
		System.out.println(minHeap.extractRoot());
		System.out.println(minHeap.extractRoot());
		System.out.println(minHeap.extractRoot());
		System.out.println(minHeap.extractRoot());
		System.out.println(minHeap.extractRoot());
		
	}
}