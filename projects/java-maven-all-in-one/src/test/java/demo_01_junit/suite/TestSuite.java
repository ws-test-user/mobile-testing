package demo_01_junit.suite;

import demo_01_junit.tests.TestClass1;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({TestClass1.class, TestClass1.class})
public class TestSuite {
    @BeforeClass
    public static void runBeforeClass() {
        System.out.println("JunitSuite Suite @BeforeClass");
    }

    @AfterClass
    public static void runAfterClass() {
        System.out.println("JunitSuite Suite @AfterClass");
    }
}
