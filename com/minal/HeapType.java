package com.minal;

public enum HeapType {
	MIN(1), MAX(-1);
	
	private int magnitude;
	
	private HeapType(int _magnitude) {
		this.magnitude = _magnitude;
	}
	
	public int getMagnitude() {
		return magnitude;
	}
}