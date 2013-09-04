package com.triangle.gmn.main;

import java.util.ArrayList;
import java.util.List;

import edu.emory.mathcs.backport.java.util.Collections;

public class TriangleNode {

	private Integer value;
	private Integer origValue;
	private TriangleNode left;
	private TriangleNode right;
	private List<Integer> childSubtotals = new ArrayList<>();
	private int maxTotal;
	
	public TriangleNode(Integer value) {
		this.value = value;
		this.origValue = value;
	}
	
	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public TriangleNode getLeft() {
		return left;
	}
	public void setLeft(TriangleNode left) {
		this.left = left;
	}
	public TriangleNode getRight() {
		return right;
	}
	public void setRight(TriangleNode right) {
		this.right = right;
	}

	public int getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}

	public Integer getOrigValue() {
		return origValue;
	}

	public void setOrigValue(Integer origValue) {
		this.origValue = origValue;
	}
	
	public void addSubtotal(Integer value) {
		childSubtotals.add(value);
	}
	
	public List<Integer> getChildSubtotals() {
		return childSubtotals;
	}
	
	public Integer getMaxChildSubtotal() {
		Collections.sort(childSubtotals);
		return childSubtotals.get(childSubtotals.size()-1);
	}
}
