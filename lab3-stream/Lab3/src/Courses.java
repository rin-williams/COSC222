import java.util.ArrayList;
import java.util.Iterator;

//Write/fix the code as needed to remove warnings/errors and complete the lab 
public class Courses {
	ArrayList<Course> courses = new ArrayList<>();

	void loadCourses() {
		courses.add(new Course("COSC", "111", "Computer Programming I",
				"Introduction to the design, implementation, and understanding of computer programs. Topics include problem solving, algorithm design, and data and procedural abstraction, with emphasis on the development of working programs. This course should be followed by COSC 121. [3-2-0]",
				"A score of 70% or higher in one of PREC 12, MATH 12, MATH 125, MATH 126."));
		courses.add(new Course("COSC", "121", "Computer Programming II",
				"Advanced programming in the application of software engineering techniques to the design and implementation of programs manipulating complex data structures. [3-2-0]",
				"Prerequisite: A score of 60% or higher in one of COSC 111, COSC 123."));
		courses.add(new Course("COSC", "222", "Data Structures",
				"Introduction to the design, implementation and analysis of data structures. Topics will include lists, stacks, queues, trees, and graphs. Credit will only be granted for one of COSC 210 or COSC 222. [3-2-0]",
				"Prerequisite: A score of 60% or higher in COSC 121."));
	}

	String listAll() {
		String s = "";
		Course c;
		for (int i = 0; i < courses.size(); i++) {
			c = courses.get(i);
			s += c.accr + " " + c.number + " " + c.title + "\n";
			System.out.println(c.accr + " " + c.number + " " + c.title);
		}
		return s;
	}

	String listAllIter() {
		Iterator<Course> it = courses.iterator();
		String s = "";
		while (it.hasNext()) {
			Course c = it.next();
			s += c.accr + " " + c.number + " " + c.title + "\n";
		}
		System.out.println(s);
		return s;
	}

	String listAllStream() {
		StringBuilder s = new StringBuilder();
		// use a string builder so we can append to it and return it as a string for
		// testing
		courses.stream()
				.forEach(c -> s.append(c.accr).append(" ").append(c.number).append(" ").append(c.title).append("\n"));
		System.out.println(s.toString());
		return s.toString();
	}

	String displayCourse(String number) {
		String s = "";
		for (Course c : courses) {
			if (c.number.equals(number)) {
				s += c.accr + " " + c.number + " " + c.title + "\n";
				s += c.desc + "\n";
				s += c.prereq + "\n";
				System.out.println(c.accr + " " + c.number + " " + c.title);
				System.out.println(c.desc);
				System.out.println(c.prereq);
			}

		}
		return s;
	}

	int storedCourses() {
		System.out.println("Number of courses: " + courses.size());
		return courses.size();
	}

	String listAllContains(String keyword) {
		String s = "";
		for (Course c : courses) {
			if (c.desc.toLowerCase().contains(keyword.toLowerCase())) {
				s += c.accr + " " + c.number + " " + c.title + "\n";
				System.out.println(c.accr + " " + c.number + " " + c.title);
			}
		}
		if (s.equals("")) {
			System.out.println("No courses found containing " + "\"" + keyword + "\"");
		}
		return s;
	}

	ArrayList<String> listSortedByNumber() {
		ArrayList<Course> sorted = new ArrayList<>();
		ArrayList<String> s = new ArrayList<>();

		for (Course c : courses) {
			sorted.add(c);
		}
		// this lamda expression accepts 2 courses and compares their numbers attribute
		sorted.sort((c1, c2) -> c1.number.compareTo(c2.number));

		for (Course c : sorted) {
			s.add(c.accr + " " + c.number + " " + c.title);
			System.out.println(c.accr + " " + c.number + " " + c.title);
		}
		return s;
	}

	public static void main(String[] args) {
		Courses c = new Courses();
		c.loadCourses();

		System.out.println();
		System.out.println("listAll()");
		c.listAll();

		System.out.println();
		System.out.println("listAllIter()");
		c.listAllIter();

		System.out.println();
		System.out.println("listAllStream()");
		c.listAllStream();

		System.out.println();
		System.out.println("displayCourse() for 111");
		c.displayCourse("111");
		System.out.println();
		System.out.println("displayCourse() for 121");
		c.displayCourse("121");
		System.out.println();
		System.out.println("displayCourse() for 222");
		c.displayCourse("222");

		System.out.println();
		System.out.println("storedCourses()");
		c.storedCourses();

		System.out.println();
		System.out.println("listAllContains() for \"database\"");
		c.listAllContains("database");

		System.out.println();
		System.out.println("listSortedByNumber()");
		c.listSortedByNumber();

	}

}
