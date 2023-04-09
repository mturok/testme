import java.util.*;
import java.io.*;

class Solution {


    public static int fibonacci_cache(int n, int indent, int[] cache) {

        if (0 != cache[n]) {
            System.out.printf("%sfibonacci_cache of %d - cached result\n", " ".repeat(indent), n);
            return cache[n];
        }

        System.out.printf("%sfibonacci_cache of %d\n", "  ".repeat(indent), n);

        if (n == 0 || n == 1)
            cache[n] = 1;
        else
            cache[n] = fibonacci_cache(n-1, indent+1, cache) + fibonacci_cache(n-2, indent+1, cache);

        return cache[n];
    }


    public static int fibonacci(int n, int indent) {
        System.out.printf("%sfibonacci of %d\n", "  ".repeat(indent), n);
        if (n == 0 || n == 1)
            return 1;

        return fibonacci(n-1, indent+1) + fibonacci(n-2, indent+1);
    }


    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        System.out.printf("Result: %d\n", fibonacci(n, 0));

        int[] cache = new int[n+1];

        System.out.printf("Result: %d\n", fibonacci_cache(n, 0, cache));
    }
}
