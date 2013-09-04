package com.triangle.gmn.main;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Scanner;

public class TriangleProcessorCommandline {
	
	
	public static String getCleanPath() {
	    ClassLoader classLoader = TriangleProcessorCommandline.class.getClassLoader();
	    File classpathRoot = new File(classLoader.getResource("").getPath());

	    return classpathRoot.getPath();
	}

	public static void executeFile(String fileName) {
		
		TriangleProcessor tp = new TriangleProcessor();
		try {
			tp.readInTriangle(fileName);
		} catch (Exception e) {
			System.out.println("Unable to read selected file");
			e.printStackTrace();
			return;
		}
		
		System.out.println("\nCalculating maximum path sum...\n"); 
		long sumStart = System.currentTimeMillis();
		int maxTraversalSum = tp.findMaxSum();
		System.out.println(String.format("The max sum is: %d Total time to execute: %dms", 
				maxTraversalSum, (System.currentTimeMillis()-sumStart)));

	}
	

	public static void main(String[] args) throws URISyntaxException {
		
		boolean continuerunning = true;
		try (Scanner in1 = new Scanner(System.in) ) {
		while( continuerunning ) {
		
			System.out.println("\n\n\n\nTriangle Sum Problem ");
			System.out.println("(d) Demo - use demo files");
			System.out.println("(c) Custom - provide path to custom file");
			System.out.println("(x) Done - finish program");

			String command = in1.next();
			
			switch( command ) {
			case "x":
			case "X":
				continuerunning = false;
				break;
			case "d":
			case "D":
				boolean validInput = false;
				int fileSel = -1;
				while( !validInput ) {
					System.out.println("\n\n\nRunning Demo.  Please select a file to run:");
					System.out.println("0:SmallTest1 (4 tiers)");
					System.out.println("1:SmallTest2 (5 tiers)");
					System.out.println("2:SmallTest3 (6 tiers)");
					System.out.println("3:LargeTest (100 tiers)");
					
					int value = in1.nextInt();
					if ( value >= 0 && value <= 3 ) {
						validInput = true;
						fileSel = value;
					}
				}
				
				String fileName = null;
				switch( fileSel ) {
				case 0:
					fileName = "SmallTest1.txt";
					break;
				case 1:
					fileName = "SmallTest2.txt";
					break;
				case 2:
					fileName = "SmallTest3.txt";
					break;
				case 3:
					fileName = "LargeTest.txt";
					break;
				}
				String fqFn = TriangleProcessorCommandline.getCleanPath()+"/"+fileName;
				TriangleProcessorCommandline.executeFile(fqFn);
				break;
			case "c":
			case "C":
				System.out.println("\n\n\nCustom File Processing.");
				System.out.println("Please enter a fully qualified filename: ");
				String customFile = in1.next();
				TriangleProcessorCommandline.executeFile(customFile);
				break;
			}
			
		}
		}
		
		System.out.println("Thanks for playing");
	}
}
