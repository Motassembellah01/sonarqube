package sonar.newFunction;

public class Fibonacci {
    private int[] memo;

    public Fibonacci(int n) {
        memo = new int[n + 1]; // Initialize memoization array
        for (int i = 0; i <= n; i++) {
            memo[i] = -1; // Mark uncalculated values
        }
    }

    public int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        if (memo[n] != -1) {
            return memo[n]; // Return cached value if already computed
        }
        memo[n] = fibonacci(n - 1) + fibonacci(n - 2);
        return memo[n];
    }
}