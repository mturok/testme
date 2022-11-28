import java.util.*;
import java.io.*;

class Solution {

    static final int SHIP_SIZE = 4; // constant for the size of the ship
    static final int DIMENSION = 10; // constant for the size of the board (square)

    static boolean DEBUG = true;

    static char[][] _board = new char[DIMENSION][DIMENSION];
    static char[][] _guesses = new char[DIMENSION][DIMENSION];

    static String _columns = "ABCDEFGHIJ";

    public static void init(char[][] buffer) {
        for (int i = 0; i < buffer.length; i++)
            Arrays.fill(buffer[i], ' ');
    }

    static int check_bounds(int guess) throws Exception {
        if (guess < 0 || guess >= DIMENSION)
            throw new Exception();
        return guess;
    }

    static int[] get_guess(Scanner in) {

        while (true) {
            System.out.print("Enter guess: ");
            String guess = in.nextLine();

            try {
                // https://www.geeksforgeeks.org/java-program-to-convert-char-to-int/
                int col = check_bounds(guess.charAt(0) - 'A');
                int row = check_bounds(Integer.parseInt(guess.substring(1)));

                return new int[] { row, col };

            } catch (Exception e) {
                System.out.println("Bad guess: " + guess);
            }
        }
    }

    static void print_board(char[][] buffer) {

        String delim  = "+---";
        System.out.print(" ");
        for (char c = 'A'; c < 'A' + DIMENSION; c++)
            System.out.print("  " + c + " ");
        System.out.println("\n " + delim.repeat(DIMENSION) + "+");

        for(int row = 0; row < DIMENSION; row++) {
            System.out.print(row);
            for(int col = 0; col < DIMENSION; col++ )
                System.out.format("| %c ", buffer[row][col]);
            System.out.print("|");
            System.out.println("\n " + delim.repeat(DIMENSION) + "+");
        }
    }

    // create the board
    // randomly place the ship. Use Math.random() to return an int random numbers //
    // display the board

    static void create_battleship() {
        // first, pick orientation
        // then, depending on orientation, we can constrain as follows:
        //
        // if horizontal, then cols from 0 -> DIMENSION - SHIP_SIZE
        // if vertical , then rows from 0 -> DIMENSION - SHIP_SIZE

        int orientation = (int) (Math.random() * 2);

        int row_max = DIMENSION;
        int col_max = DIMENSION;

        if (orientation == 0) // horizontal
            col_max -= SHIP_SIZE;

        if (orientation == 1) // vertical
            row_max -= SHIP_SIZE;

        int row = (int) (Math.random() * row_max);
        int col = (int) (Math.random() * col_max);

        if(DEBUG) {
            System.out.println("Orientation: " + (orientation == 0 ? "Horizontal" : "Vertical"));
            System.out.println("Row max: " + row_max);
            System.out.println("Col max: " + col_max);
            System.out.format("Row: %d, Col: %d\n", row, col);
        }

        if (orientation == 0) // horizontal
            for (int c = 0; c < SHIP_SIZE; c++)
                _board[row][c+col] = 'X';

        if (orientation == 1) // vertical
            for (int r = 0; r < SHIP_SIZE; r++)
                _board[row+r][col] = 'X';
    }

    static boolean guess_hit(int [] guess) {
        int row = guess[0];
        int col = guess[1];

        if (_board[row][col] == 'X') {
            _guesses[row][col] = 'X';
            return true;
        };

        _guesses[row][col] = '#';
        return false;
    }

    public static void main(String[] args) {
        init(_board);
        init(_guesses);

        create_battleship();

        if(DEBUG)
            print_board(_board);

        int hits = 0;
        int misses = 0;

        Scanner in = new Scanner(System.in);
        while(hits < SHIP_SIZE) {
            print_board(_guesses);

            int[] guess = get_guess(in);
            System.out.println(Arrays.toString(guess));

            if(guess_hit(guess))
                hits++;
            else
                misses++;

        }
        System.out.format("\n\n======>YOU WON: %d guesses\n", hits + misses);
        print_board(_guesses);
    }
}
