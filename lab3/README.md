# 222-3-stream
<h1>Stream</h1>
<h2>Lab: Write Streams</h2>
<p>The objective of the lab is to read and write Java code to manipulate streams. Not the I/O stream but the java.util.Stream. In order to do the lab, read the online tutorials
<ol>
	<li> <a href="http://winterbe.com/posts/2014/03/16/java-8-tutorial/">Java 8 Tutorial</a>
	<li> <a href="http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/">Java 8 Stream Tutorial</a>
</ol>
<p>There are numerous other online tutorials or the official <a href="https://docs.oracle.com/javase/tutorial/collections/streams/">Java Collection Stream tutorial</a>.

Consider the Course and the Courses classes. Write the following code
<ol> 
<li> Use sample data from <a href="http://www.calendar.ubc.ca/okanagan/courses.cfm?go=name&code=COSC">http://www.calendar.ubc.ca/okanagan/courses.cfm?go=name&code=COSC</a> to load more course data into the courses array.
  <li> write <code><pre>void displayCourse(String number){...}</pre></code> to display on the console the following information
<pre>
COSC 222 Data Structures
Introduction to the design, implementation and analysis of data structures. Topics will include lists, stacks, queues, trees, and graphs. [3-2-0]
Prerequisite: A score of 60% or higher in COSC 121.
</pre>
<li> Write the listAllIter method that returns the same information as listAll but uses an iterator
<li> Write the listAllStream method that returns the same information as listAll but uses Stream
<li> Write 1 unit test that verifies all 3 methods return the same result
<li> Write a method using stream that displays how many courses are stored
<li> Write a method to list all the course that contain "database" in their description
<li> Write a method that returns the list of courses sorted by their number
</ol>

Submit your lab through GitHub as usual. Include all your unit tests and required coverage. Enter comments and explanations of your code as comments within the code.
