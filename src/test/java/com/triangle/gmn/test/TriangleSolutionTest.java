package com.triangle.gmn.test;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.triangle.gmn.main.TriangleNode;
import com.triangle.gmn.main.TriangleProcessor;

public class TriangleSolutionTest {

	private TriangleProcessor tp;
	
	@Before
	public void initializeTests() throws Exception {
		if( tp == null ) {
			tp = new TriangleProcessor();
		}
		tp.readInTriangle( getCleanPath()+"/SmallTest3.txt");
	}
	
	public static String getCleanPath() {
	    ClassLoader classLoader = TriangleSolutionTest.class.getClassLoader();
	    File classpathRoot = new File(classLoader.getResource("").getPath());

	    return classpathRoot.getPath();
	}

	@Test
	public void testTierConstruction() {
		List<List<TriangleNode>> levels = tp.getLevels();
		Assert.assertEquals(6, levels.get(5).size());
	}
	
	@Test
	public void testTotal() {
		int maxSum = tp.findMaxSum();
		Assert.assertEquals("Incorrect Maximum sum was calculated", 328, maxSum);
		System.out.println("Done");
	}
}
