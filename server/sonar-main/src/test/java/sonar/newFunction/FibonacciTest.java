package sonar.newFunction;

import org.junit.Test;
import org.junit.Before;
import static org.assertj.core.api.Assertions.assertThat;

public class FibonacciTest {
  private Fibonacci fibonacciCalculator;

  @Before
  public void setUp() {
    fibonacciCalculator = new Fibonacci(20); // Set a reasonable limit for memorization
  }

  @Test
  public void test_fibonacci_base_cases() {
    assertThat(0 == fibonacciCalculator.fibonacci(0));
    assertThat(1 == fibonacciCalculator.fibonacci(1));
  }

  @Test
  public void testFibonacciRecursiveCases() {
    assertThat(1  == fibonacciCalculator.fibonacci(2));
    assertThat(2  == fibonacciCalculator.fibonacci(3));
    assertThat(3  == fibonacciCalculator.fibonacci(4));
    assertThat(5  == fibonacciCalculator.fibonacci(5));
    assertThat(8  == fibonacciCalculator.fibonacci(6));
    assertThat(13 == fibonacciCalculator.fibonacci(7));
  }

  @Test
  public void testMemoizationPerformance() {
    long startTime = System.nanoTime();
    int result = fibonacciCalculator.fibonacci(15); // Should be 610
    long endTime = System.nanoTime();

    assertThat(610 == result);
    assertThat((endTime - startTime) < 1_000_000);
  }

  @Test
  public void testHigherValue() {
    assertThat(6765 == fibonacciCalculator.fibonacci(20));
  }
}
