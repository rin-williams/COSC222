# 222-1-unit-test
COSC 222, first lab; learning objectives:

1. Download the lab from GitHub classroom, edit in Eclipse, upload the lab using Git, submit the lab.
2. Learn about unit testing and the JUnit syntax.
3. Learn Git commands: clone, push; and Git clients: CLI (Command Line Interface), Eclipse Git client, TortoiseGit

# Preliminaries:

* Download and install: [Eclipse](https://www.eclipse.org/downloads/), and a [Git client](https://git-scm.com/downloads/guis) like [TortoiseGit](https://tortoisegit.org/download/) (TortoiseGit runs only on Windows).

## Obtaining the lab from GitHub Classroom, pushing to Git, and submitting

Method 1: Do all in Eclipse; simpler. This worked using a brand new install of Eclipse ON 2022-09-13, which comes with JRE 17.

1. in your browser, open the URL for the assignment, click accept, click the URL, click Code (green button),  and copy the URL
2. Go to [https://github.com/settings/tokens](https://github.com/settings/tokens) and generate a token (you only need repo permissions). Copy the token
3. In Eclipse, File - import - git - projects from Git - clone URI then enter the URI; use your username and the token as password. Your lab should be imported in Git. When you wish to save, right click on your project - Team - Commit. Don't forget to push or all your changes will only be saved locally.

Method 2: Use Git client like TortoiseGit; if you know Git

1. Step 1 as above
2. Using your Git client of choice, clone the repository onto your local machine, preferably in your eclipse workspace.
3. Open Eclipse, create a new Java Project. Uncheck "Use Default File Location". Browse locations and find your cloned repository, using it as the project location. Ensure you have at least Java 16.
4. Your project will still likely have errors on everything JUnit related. Hover over one of the broken import statements and click "Fix project setup" where you should be prompted to add Junit 5. This should resolve all compilation errors provided you are on Java 16+
5. After you finish your lab, you need to push the changes, so copy your project files into the Git project folder, then right click and select Git commit, and click on the button Commit & Push.

## Git

For this course, you only need to use basic commands since you are not sharing your repository with anyone.

* Basic: Clone, and push. Understanding pull, commit, delete, and add helps.
* Intermediate: revert, diff, fetch. 
* Advanced: branching, merge, rebase

Unless you are familiar with Git, watch the first 5 videos from [the full playlist](https://www.youtube.com/watch?v=3RjQznt-8kE&list=PL4cUxeGkcC9goXbgTDQ0n_4TBzOO0ocPR). You can also read one of the many tutorials on Git such as https://product.hubspot.com/blog/git-and-github-tutorial-for-beginners. 

## Unit Testing

Unit testing is an important part of quality assurance, the purpose of unit tests is to confirm that a class/method is robust does what you claim it does and does not break under certain circumstances (i.e. wrong input, corner cases etc.). 

For this lab you will learn how to create a test case for yourself and some helpful tips on what makes a useful test. The lab is a simple example of a student, teacher, class, and administration. With corresponding test of varying completion. There are comments within the classes with more in depth description of the methods.

All the tasks you need to complete for this lab are marked by //TODO comments each test that passes non trivially will be 2 marks out of 10.
1. Observe how UniClassTest is complete, this is how most tests are structured

  - @BeforeAll methods are run once before all tests initializing useful variables, and objects that will be used in the test cases. That method has to be static so all properties it uses have to be static. Other annotations are listed at https://junit.org/junit5/docs/current/user-guide/#writing-tests-annotations.

  - @Test methods are where you test the functionality of your classes/methods. This example has one test per method but this does not always have to be the case. A good rule of thumb to follow is that you should gain a better understanding of the method from each test, whether it passes or fails. This usually involves finding corner cases and testing what you expect to happen against the returned result.

  - Assert statements are what is used to validate the correctness of methods, in general they are used to test what a method is expected to return given certain parameters versus what is returned. There are many different type of assert statements see https://www.baeldung.com/junit-assertions#assertions-junit5 for a list. A good way to think of assert statements would be to draw connections to logical statements used in if-elses, if assert is true the test passes else it fails.


  The tests in UniClassTest are trivial, but are a good example of what a test should be.

  Here is some reference material for creating tests:
  * https://howtodoinjava.com/junit-5-tutorial/
  * https://dev.to/saiupadhyayula/junit-5-tutorial-for-beginners-o8a

  Note that there are many tutorials and examples on the web, but some of them are obsolete or refer to JUnit 4. If you see any code, make sure it has a line similar to import org.junit.jupiter.api.* otherwise, it won't work with the latest versions of the libraries.

2. To complete the lab
  1. Fill all the places with //TODO comments in the 2 classes Administration and AdministrationTest
  2. Complete the StudentTest class where indicated to test both the getAge and getClasses method.
  3. BONUS: There are 2 bonus marks if you explain why each test in BonusTest either fails or passes. (hint: look up the Javadoc for arrays.equal, .equal, and assertEquals). Write your answer in the text file you submit to Canvas.

Once the lab is complete, commit, and push your changes.
