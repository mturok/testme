import java.util.*;
import java.io.*;

class Solution {

    static final int SHIP_SIZE = 4; // constant for the size of the ship
    static final int DIMENSION = 10; // constant for the size of the board (square)

    static boolean DEBUG = true;

    static char[][] _board = new char[DIMENSION][DIMENSION];
    static char[][] _guesses = new char[DIMENSION][DIMENSION];


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

    static void create_battleship_retry(char ship, int ship_size)  {
        int MAX_RETRIES = 1000;
        for(int i = 0; i < MAX_RETRIES; i++) {
            if( DEBUG )
                System.out.format("Create ship: %c, length, %d, try #: %d\n", ship, ship_size, i);
            if (create_battleship(ship, ship_size))
                return;
        }

        System.err.format( "Tried %d times - failed to create ship: %c of size: %d\n", MAX_RETRIES, ship, ship_size);
        print_board(_board);
        System.exit(-1);
    }


    static void create_battleships(int [][] ships) {
        for(int i = 0; i < ships.length; i++)
            create_battleship_retry((char)ships[i][0], ships[i][1]);
    }

    static boolean create_battleship(char ship, int ship_size)  {
        // first, pick orientation
        // then, depending on orientation, we can constrain as follows:
        //
        // if horizontal, then cols from 0 -> (DIMENSION - SHIP_SIZE)
        // if vertical , then rows from 0 -> (DIMENSION - SHIP_SIZE)

        int orientation = (int) (Math.random() * 2);

        int row_max = orientation == 0 ? DIMENSION : DIMENSION - ship_size;
        int col_max = orientation == 1 ? DIMENSION : DIMENSION - ship_size;

        int row = (int) (Math.random() * row_max);
        int col = (int) (Math.random() * col_max);

        int row_end = orientation == 0 ? row + 1 : row + ship_size;
        int col_end = orientation == 1 ? col + 1 : col + ship_size;

        if (DEBUG) {
            System.out.println("Orientation: " + (orientation == 0 ? "Horizontal" : "Vertical"));
            System.out.println("Row max: " + row_max);
            System.out.println("Col max: " + col_max);
            System.out.format("Row: %d, Col: %d\n\n", row, col);
        }

        // check for no other boats
        for (int r = row; r < row_end; r++)
            for(int c = col; c < col_end; c++)
                if (_board[r][c] != ' ')
                    return false;

        // fill in the character
        for (int r = row; r < row_end; r++)
            for(int c = col; c < col_end; c++)
                _board[r][c] = ship;

        return true;
    }

    static boolean guess_hit(int [] guess) {
        int row = guess[0];
        int col = guess[1];


        if (_board[row][col] == ' ') {
            _guesses[row][col] = '#';
            return false;
        }

        _guesses[row][col] = 'X';
        return true;
    }


    public static void main(String[] args) {
        init(_board);
        init(_guesses);

        int [][] ships = {
            { 'B', SHIP_SIZE }, { 'C', SHIP_SIZE + 1 }, { 'S', SHIP_SIZE - 1 }, { 'D', SHIP_SIZE - 2 }
            // { 'B', SHIP_SIZE }
        };

        create_battleships(ships);

        if(DEBUG)
            print_board(_board);

        int hits = 0;
        int misses = 0;

        int num_hits = 0;
        for(int i = 0; i < ships.length; i++)
            num_hits += ships[i][1];

        Scanner in = new Scanner(System.in);
        while(hits < num_hits) {
            print_board(_guesses);

            int[] guess = get_guess(in);

            if (DEBUG)
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
