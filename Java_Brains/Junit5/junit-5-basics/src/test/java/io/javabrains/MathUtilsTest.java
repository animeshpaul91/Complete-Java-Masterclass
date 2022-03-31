package io.javabrains;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


// @TestInstance(TestInstance.Lifecycle.PER_CLASS) // change default behavior to create a single instance of MathUtilTest for all test methods
// @TestInstance(TestInstance.Lifecycle.PER_METHOD) // default behavior. Adding this is similar to not adding it
class MathUtilsTest { // unit manages the lifecycle of the class

    private MathUtils mathUtils;

    @BeforeAll // runs before the construction of the object of MathUtilsTest. So needs to be static
    public static void beforeAllInit() {
        System.out.println("This runs before all");
    }

    @BeforeEach // this will run before each test method runs
    public void init() {
        this.mathUtils = new MathUtils();
    }

    @AfterEach
    public void cleanup() {
        System.out.println("Cleaning Up");
    }

    @AfterAll // runs before the destruction of the object of MathUtilsTest. So needs to be static
    public static void afterAllInit() {
        System.out.println("This runs after all");
    }

    @Test
    public void testAdd() { // junit creates a new instance of MathUtilsTest for every method run
        // System.out.println(this);
        int first = 10, second = 20;
        int expected = first + second;
        int actual = mathUtils.add(first, second);
        assertEquals(expected, actual, "The add method should add two numbers");
    }

    @Test
    public void testDivide() {
        // System.out.println(this);
        int first = 10, second = 0;
        assertThrows(ArithmeticException.class, () -> mathUtils.divide(first, second), "Divide by zero must Throw an exception");
    }

    @Test
    public void testComputeCircleArea() {
        // System.out.println(this);
        double radius = 10;
        double expected = 314.1592653589793;
        double actual = mathUtils.computeCircleArea(radius);
        assertEquals(expected, actual, "The computeCircleArea method returns the area of the circle for a given radius");
    }
}