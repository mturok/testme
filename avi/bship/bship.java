import java.io.*;
import java.util.*;

public class bship {

    static int SHIP_SIZE = 4;
    static int DIMENSION = 10;

    public static void main(String[] args)  {

        Board guesses = new Board();

        Board board = new Board();
        board.create_ship();

        board.printgrid(); // - for debugging
        guesses.printgrid();// printing board

        int hits = 0;
        int misses = 0;
        Scanner in = new Scanner(System.in);

        while (hits < SHIP_SIZE) {// looping through each turn of player.

            Guess guess = read_guess(in);
            boolean hit = board.check_guess(guess);
            guesses.apply_guess(hit, guess);

            if (hit)
                hits++;
            else
                misses++;

            guesses.printgrid();// after turn, reprint
            board.printgrid(); // - for debugging
            System.out.println("Hits: " + hits + ", misses: " + misses + ", total tries: " + (hits + misses));// counter
        }
        System.out.println("You sunk my battle ship!");// end display
    }

    public static Guess read_guess(Scanner in) {
        while(true) { //infinite loop until a valid input is submitted
            System.out.print("Enter coordinate guess: ");
            String guess = in.nextLine();
            try {
                return new Guess(guess);
            }
            catch (Exception e) {
                System.out.println("Bad guess: " + guess);
            }
        }
    }
}
