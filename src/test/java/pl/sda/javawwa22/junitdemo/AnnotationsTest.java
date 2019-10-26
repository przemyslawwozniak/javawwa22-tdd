package pl.sda.javawwa22.junitdemo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class AnnotationsTest {

    public AnnotationsTest() {
        System.err.println("* default constructor *");
    }

    @BeforeClass
    public static void beforeClass() {
        System.err.println("@BeforeClass");
    }

    @Before
    public void before() {
        System.err.println("@Before");
    }

    @Test
    public void testMethod() {
        System.err.println("@Test - successful");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMethodWithException() {
        System.err.println("@Test - throwing an expected exception");
        throw new IllegalArgumentException();
    }

    @Test
    @Ignore
    public void failedTestMethod() {
        System.err.println("@Test - throwing an unexpected exception");
        throw new IllegalArgumentException();
    }

    @After
    public void after() {
        System.err.println("@After");
    }

    @AfterClass
    public static void afterClass() {
        System.err.println("@AfterClass");
    }

}


