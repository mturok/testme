import java.util.*;
import java.io.*;

class Solution {

    public static int _recurse(int[] list, int guess, int start, int end) {
        int mid = start + (int)(end - start)/2;

        System.out.printf("Recurse: guess: %d start: %d end: %d mid:%d List[mid]: %d\n", guess, start, end, mid, list[mid]);

        if (guess == list[mid])
            return mid;

        if (guess < list[mid])
            end = mid-1;
        else
            start = mid+1;

        if (start > end)
            return -1;

        return _recurse(list, guess, start, end);
    }


    public static int bsearch_recurse(int[] list, int guess) {
        return _recurse(list, guess, 0, list.length-1);
    }


    public static int bsearch_loop(int [] list, int guess) {

        int start = 0;
        int end = list.length-1;

        while(start <= end) {
            int mid = start + (int)(end - start)/2;

            System.out.printf("Loop: guess: %d start: %d end: %d mid:%d List[mid]: %d\n", guess, start, end, mid, list[mid]);

            if (guess == list[mid])
                return mid;

            if (guess < list[mid])
                end = mid-1;
            else
                start = mid+1;
        }
        return -1;
    }


    public static void main(String[] args) {
        System.out.print("Array Size: ");
        Scanner in = new Scanner(System.in);
        int num = Integer.parseInt(in.nextLine());

        Random r = new Random();

        int list[]  = r.ints(num, -num*100, num*100).distinct().sorted().toArray();
        // List<Integer> list = r.ints(num, -num*10, num*10).distinct().sorted().boxed().toList();

        System.out.println(Arrays.toString(list));

        int index = -1;
        while(index < 0) {
            System.out.print("Guess: ");
            int guess = Integer.parseInt(in.nextLine());
            int index_recurse = bsearch_recurse(list, guess);
            int index_loop = bsearch_loop(list, guess);
            //System.out.printf("Loop: %d\n", index_loop);
            System.out.printf("Recurse: %d, Loop: %d\n", index_recurse, index_loop);
            index = index_loop;
            // int index_loop = bsearch_loop(list, guess);
        }

    }
}
