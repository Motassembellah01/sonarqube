package sonar.newFunction;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FibonacciTest {
  private Fibonacci fibonacciCalculator;

  @BeforeEach
  void setUp() {
    fibonacciCalculator = new Fibonacci(20); // Set a reasonable limit for memoization
  }

  @Test
  void testFibonacciBaseCases() {
    assertEquals(0, fibonacciCalculator.fibonacci(0), "Fibonacci(0) should be 0");
    assertEquals(1, fibonacciCalculator.fibonacci(1), "Fibonacci(1) should be 1");
  }

  @Test
  void testFibonacciRecursiveCases() {
    assertEquals(1, fibonacciCalculator.fibonacci(2), "Fibonacci(2) should be 1");
    assertEquals(2, fibonacciCalculator.fibonacci(3), "Fibonacci(3) should be 2");
    assertEquals(3, fibonacciCalculator.fibonacci(4), "Fibonacci(4) should be 3");
    assertEquals(5, fibonacciCalculator.fibonacci(5), "Fibonacci(5) should be 5");
    assertEquals(8, fibonacciCalculator.fibonacci(6), "Fibonacci(6) should be 8");
    assertEquals(13, fibonacciCalculator.fibonacci(7), "Fibonacci(7) should be 13");
  }

  @Test
  void testMemoizationPerformance() {
    long startTime = System.nanoTime();
    int result = fibonacciCalculator.fibonacci(15); // Should be 610
    long endTime = System.nanoTime();

    assertEquals(610, result, "Fibonacci(15) should be 610");
    assertTrue((endTime - startTime) < 1_000_000, "Memoized computation should be fast");
  }

  @Test
  void testHigherValue() {
    assertEquals(6765, fibonacciCalculator.fibonacci(20), "Fibonacci(20) should be 6765");
  }
}
