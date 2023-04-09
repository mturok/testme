import java.util.*;
import java.io.*;
import java.text.*;


class Solution {


    public static long factorial(long x, int indent) {
        System.out.printf("%sfactorial of %d\n", "  ".repeat(indent), x);
        if (x == 1)
            return 1;

        return x * factorial(x-1, indent+1);
    }

    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        long result = factorial(x, 0);
        System.out.printf("Result: %s\n", NumberFormat.getIntegerInstance().format(result));
    }
}
