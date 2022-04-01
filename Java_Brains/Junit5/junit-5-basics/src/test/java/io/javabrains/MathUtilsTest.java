package io.javabrains;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


// @TestInstance(TestInstance.Lifecycle.PER_CLASS) // change default behavior to create a single instance of MathUtilTest for all test methods
// @TestInstance(TestInstance.Lifecycle.PER_METHOD) // default behavior. Adding this is similar to not adding it
class MathUtilsTest { // unit manages the lifecycle of the class

    private MathUtils mathUtils;

    @BeforeAll // runs before the construction of the object of MathUtilsTest. So needs to be static
    public static void beforeAllInit() {
        // System.out.println("This runs before all");
    }

    @BeforeEach // this will run before each test method runs
    public void init() {
        this.mathUtils = new MathUtils();
    }

    @AfterEach
    public void cleanup() {
        // System.out.println("Cleaning Up");
    }

    @AfterAll // runs before the destruction of the object of MathUtilsTest. So needs to be static
    public static void afterAllInit() {
        // System.out.println("This runs after all");
    }

    @Test
    @DisplayName("Testing add method")
    public void testAdd() { // junit creates a new instance of MathUtilsTest for every method run
        // System.out.println(this);
        int first = 10, second = 20;
        int expected = first + second;
        int actual = mathUtils.add(first, second);
        assertEquals(expected, actual, "The add method should add two numbers");
    }

    @Test
    @DisplayName("Testing divide method")
    @EnabledOnOs(OS.LINUX)
    public void testDivide() {
        // System.out.println(this);
        int first = 10, second = 0;
        assumeTrue(second != 0); // offers programmatic control to run conditional tests
        assertThrows(ArithmeticException.class, () -> mathUtils.divide(first, second), "Divide by zero must Throw an exception");
    }

    @Test
    @DisplayName("Testing compute circle area")
    public void testComputeCircleArea() {
        // System.out.println(this);
        double radius = 10;
        double expected = 314.1592653589793;
        double actual = mathUtils.computeCircleArea(radius);
        assertEquals(expected, actual, "The computeCircleArea method returns the area of the circle for a given radius");
    }

    @Test
    @Disabled
    public void testDisabled() {
        fail("This is intentional");
    }

    @Test
    @DisplayName("Multiply Method")
    public void testMultiply() {
        assertAll(
                () -> assertEquals(4, mathUtils.multiply(2, 2)),
                () -> assertEquals(0, mathUtils.multiply(2, 0)),
                () -> assertEquals(2, mathUtils.multiply(2, 1)),
                () -> assertEquals(-2, mathUtils.multiply(2, -1))
         );  // test will fail if at least one assertEquals fail
    }
}