package com.triangle.gmn.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TriangleProcessor {

	private TriangleNode triangleHead;
	private List<List<TriangleNode>> levels = new ArrayList<>();
	private Integer cachedMaxSum;
	
	public TriangleNode getRoot() {
		return triangleHead;
	}
	
	/**
	 * This method exposes the levels object.  Although this is an interim object, it is 
	 * exposed primarily to allow JUnit tests to verify the integrity of an in-memory triangle
	 * @return
	 */
	public List<List<TriangleNode>> getLevels() {
		return levels;
	}
	

	/** 
	 * Read a file containing triangle values and construct a structure within memory
	 * @param filename
	 * @throws Exception
	 */
	public void readInTriangle(String filename) throws Exception {

		cachedMaxSum = null;
		Queue<TriangleNode> prevRow = null;
		try (BufferedReader rdr = new BufferedReader(new FileReader(new File(filename)))) {
			
			String line = null;
			while( (line = rdr.readLine()) != null ) {
			
				List<TriangleNode> level = new ArrayList<>();
				
				Queue<TriangleNode> currRow = new LinkedList<>();
				Queue<TriangleNode> currRowTemp = new LinkedList<>();
				
				String[] lineParts = line.split(" ");
				if( triangleHead == null ) {
					if( lineParts.length != 1 ) {
						throw new Exception("Invalid input file structure!");
					}
					triangleHead = new TriangleNode(Integer.parseInt(lineParts[0]));
					currRow.add(triangleHead);
					currRowTemp.add(triangleHead);
					level.add(triangleHead);
				} else {
					
					for( String part : lineParts ) {
						TriangleNode currNode = new TriangleNode(Integer.parseInt(part));
						currRow.add(currNode);
						currRowTemp.add(currNode);
						level.add(currNode);
					}
					
					TriangleNode currRowNode = null;
					while( !prevRow.isEmpty() ) {
						TriangleNode prevNode = prevRow.remove();
						
						if( currRowNode !=  null ) {
							prevNode.setLeft(currRowNode);
						} else if( !currRow.isEmpty() ) {
							currRowNode = currRow.remove();
							prevNode.setLeft(currRowNode);
						}
						
						if( !currRow.isEmpty() ) {
							currRowNode = currRow.remove();
							prevNode.setRight(currRowNode);
						}
					}
				}
				prevRow = currRowTemp;
				levels.add(level);
			}			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

	/**
	 * Find the maximum sum obtained by traversing all nodes from root to leaf.
	 * Each node will contain a list of the sums of it's children.  When moving up a level,
	 * we only consider the maximum from a child node subtotals?
	 * @return
	 */
	public int findMaxSum() {
		if( cachedMaxSum != null ) {
			return cachedMaxSum;
		}
		for( int levelCtr = levels.size()-1; levelCtr >= 0; levelCtr-- ) {
			
			List<TriangleNode> level = levels.get(levelCtr);
			
			for( TriangleNode levelNode : level ) {
				if( levelNode.getLeft() == null && levelNode.getRight() == null ) {
					levelNode.addSubtotal(levelNode.getValue());
				} else {
					if( levelNode.getLeft() != null ) {
						levelNode.addSubtotal(levelNode.getValue() + levelNode.getLeft().getMaxChildSubtotal());
					}
					
					if( levelNode.getRight() != null ) {
						levelNode.addSubtotal(levelNode.getValue() + levelNode.getRight().getMaxChildSubtotal());
					}
				}
			}
		}

		// Finally, the root node contains all of the leaf totals...just pick the largest one!
		List<Integer> childSubtotals = levels.get(0).get(0).getChildSubtotals();
		Integer max = null;
		for( Integer childST : childSubtotals ) {
			if( max == null || childST.compareTo(max) > 0 ) {
				max = childST;
			}
		}
		
		return max;
	}

	
	/**
	 * Returns the node having the highest value within the level
	 * @param level
	 * @return
	 */
	public int findMaximum(List<TriangleNode> level) {
		int max = -1;
		int idx = -1;
		for( int ctr = 0; ctr < level.size(); ctr++ ) {
			if( level.get(ctr).getValue() > max ) {
				idx = ctr;
				max = level.get(ctr).getValue();
			}
		}
		
		return idx;
	}

	/**
	 * The implementation below, while very cool and concise is too inefficient.
	 * @param subTotal
	 * @param currentMax
	 * @param rootNode
	 * @return
	 */
	@Deprecated
	public int findMaxSum2(int subTotal, int currentMax, TriangleNode rootNode) {
		if( rootNode.getLeft() == null && rootNode.getRight() == null ) {
			return Math.max((subTotal + rootNode.getValue()), currentMax);
		}
		else if( rootNode.getLeft() != null ) {
			currentMax = Math.max(findMaxSum2(subTotal+rootNode.getValue(), currentMax, rootNode.getLeft()), currentMax);
		} 
		
		if( rootNode.getRight() != null ) {
			currentMax = Math.max(findMaxSum2(subTotal+rootNode.getValue(), currentMax, rootNode.getRight()), currentMax);
		}
		return currentMax;
	}
	
}
