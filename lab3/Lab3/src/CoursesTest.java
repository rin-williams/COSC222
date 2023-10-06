import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CoursesTest {
    private static Courses c;

    @BeforeAll
    static void initCourses() {
        c = new Courses();
        c.loadCourses();
    }

    @Test
    void testListAll() {
        String expected = "COSC 111 Computer Programming I\n" +
                "COSC 121 Computer Programming II\n" +
                "COSC 222 Data Structures\n";
        String actual = c.listAll();
        assertTrue(expected.equals(actual));
    }

    @Test
    void testlistAllIter() {
        String expected = "COSC 111 Computer Programming I\n" +
                "COSC 121 Computer Programming II\n" +
                "COSC 222 Data Structures\n";
        String actual = c.listAllIter();
        assertTrue(expected.equals(actual));
    }

    @Test
    void testListAllStream() {
        String expected = "COSC 111 Computer Programming I\n" +
                "COSC 121 Computer Programming II\n" +
                "COSC 222 Data Structures\n";
        String actual = c.listAllStream();
        assertTrue(expected.equals(actual));
    }

    @Test
    void testDisplayCourse() {
        String expected111 = "COSC 111 Computer Programming I\n"
                + "Introduction to the design, implementation, and understanding of computer programs. Topics include problem solving, algorithm design, and data and procedural abstraction, with emphasis on the development of working programs. This course should be followed by COSC 121. [3-2-0]\n"
                + "A score of 70% or higher in one of PREC 12, MATH 12, MATH 125, MATH 126.\n";
        String expected121 = "COSC 121 Computer Programming II\n"
                + "Advanced programming in the application of software engineering techniques to the design and implementation of programs manipulating complex data structures. [3-2-0]\n"
                + "Prerequisite: A score of 60% or higher in one of COSC 111, COSC 123.\n";
        String expected211 = "COSC 222 Data Structures\n"
                + "Introduction to the design, implementation and analysis of data structures. Topics will include lists, stacks, queues, trees, and graphs. Credit will only be granted for one of COSC 210 or COSC 222. [3-2-0]\n"
                + "Prerequisite: A score of 60% or higher in COSC 121.\n";
        String actual111 = c.displayCourse("111");
        String actual121 = c.displayCourse("121");
        String actual211 = c.displayCourse("222");
        assertTrue(expected111.equals(actual111));
        assertTrue(expected121.equals(actual121));
        assertTrue(expected211.equals(actual211));
    }

    @Test
    void testListAllContainsDatabase() {
        String expected = "";
        String actual = c.listAllContains("database");
        assertTrue(expected.equals(actual));
    }

    @Test
    void testListAllContainsData() {
        String expected = "COSC 111 Computer Programming I\n"
                + "COSC 121 Computer Programming II\n"
                + "COSC 222 Data Structures\n";
        String actual = c.listAllContains("data");
        assertTrue(expected.equals(actual));
    }

    @Test
    void testListSortedByNumber() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("COSC 111 Computer Programming I");
        expected.add("COSC 121 Computer Programming II");
        expected.add("COSC 222 Data Structures");
        ArrayList<String> actual = c.listSortedByNumber();
        assertTrue(expected.equals(actual));
    }

}
