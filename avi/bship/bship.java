import java.io.*;
import java.util.*;


public class bship{

    static int SHIP_SIZE = 4;
    static int DIMENSION = 10;

    public static void main(String[] args)  {

      Guess guess = new Guess();
      Board guesses = new Board();

      Board board = new Board();
      board.create_ship();

      board.printgrid(); // - for debugging
      guesses.printgrid();// printing board

      int hits = 0;
      int misses = 0;
      while (hits < SHIP_SIZE) {// looping through each turn of player.
        boolean correct = Guess.verify_guess(board, guesses);
        if (correct == true)
          hits += 1;
        else
          misses += 1;
        guesses.printgrid();// after turn, reprint
        board.printgrid(); // - for debugging
        System.out.println("Hits: " + hits + ", misses: " + misses + ", total tries: " + (hits + misses));// counter
      }
      System.out.println("You sunk my battle ship!");// end display
    }
  }
