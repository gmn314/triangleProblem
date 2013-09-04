Overview
========
This is the Triangle test application.  A source file is read, and all paths from the root node to the leaf nodes are evaluated, summing the value of each node along the path.  The maximum total of all paths is then displayed.

Notes:
======
This project was developed in eclipse, and contains all of the relevant eclipse project files.  Additionally, since this is a Maven project, it may be built and executed from the command line.  Instructions for compiling and running from the command line are below.  

Also note that a few JUnit tests are included as part of this project.  The jUnit tests are executed when the project is compiled.

Also, the Maven shade plug-in has been configured, but is actually not used.  Using the shade plug-in creates one large jar file (called an uber jar) which contains all of the dependent classes.  This is handy if you wish to deploy the application, but don't want to worry about directory structure or libs on the target machine.

As a final note, since building and executing is done using Maven, the same procedure may be followed on Windows as well as LInux.

Pre requisites:
==============
* Jdk 1.7
* Maven


Execution instructions
======================
* Unzip the project
* Navigate to the root directory of the project
* Compile: mvn install
* Run: mvn exec:java

This starts a command line interpreter that will display a menu:

Triangle Sum Problem
(d) Demo - use demo files
(c) Custom - provide path to custom file
(x) Done - finish program



The options are: (d) Demo - to use one of the files that are included with this project; (c) Custom - to navigate to a file on your local filesystem; (x) Done - quit the application.

If you select demo, the following menu will be displayed: 

Running Demo.  Please select a file to run:
0:SmallTest1 (4 tiers)
1:SmallTest2 (5 tiers)
2:SmallTest3 (6 tiers)
3:LargeTest (100 tiers)


Select 0, 1, 2 or 3.  0 - is the original "small" test file included with the problem.  1 and 2 I have augmented with a new leaf row for internal testing.  3 is the large file that is included for the test.

