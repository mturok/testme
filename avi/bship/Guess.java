import java.util.*;
import java.io.*;


public class Guess {
    static int DIMENSION = 10;

    int _row;
    int _col;

    public int guess_check(int guess) throws Exception {//making sure ship is in proper boundries
        if(guess < 0 || guess >= DIMENSION)
            throw new Exception();
        return(guess);
    }

    public Guess(String guess) throws Exception {
        _row = guess_check(Integer.parseInt(guess.substring(1)));//converts to int
        _col = guess_check(guess.charAt(0) - 'A');
    }

}
