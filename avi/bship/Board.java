import java.util.*;
import java.io.*;

public class Board {
    static int DIMENSION = 10;
    static int SHIP_SIZE = 4;
    char[][] _data = new char[DIMENSION][DIMENSION];

    public Board() {// initilization funtion (either _data or _guesses)
        for (int r = 0; r < this._data.length; r++)
            for (int c = 0; c < this._data[r].length; c++)
                this._data[r][c] = ' ';
    }

    public void printgrid() {// printing the grid (can print either _data or _guesses)
        String pattern = "+---";
        System.out.print(' ');
        for (char c = 'A'; c < 'A' + DIMENSION; c++)// col coordinates
            System.out.print("  " + c + " ");
        System.out.println();
        for (int r = 0; r < this._data.length; r++) {
            System.out.println(" " + pattern.repeat(DIMENSION) + "+");
            System.out.print(r);
            for (int c = 0; c < this._data[r].length; c++)
                System.out.print("| " + this._data[r][c] + ' ');
            System.out.println('|');
        }

        System.out.println(" " + pattern.repeat(DIMENSION) + "+");
    }

    public boolean check_guess(Guess guess) {
        return (_data[guess._row][guess._col] == 'B');
    }


    public void apply_guess(boolean hit, Guess guess){
        char c = hit ? 'X' : '#';
        _data[guess._row][guess._col] = c;
    }

    public void create_ship() {

        Ship ship = new Ship(DIMENSION, SHIP_SIZE);

        if (ship._orientation == 0)
            for (ijjnt c = 0; c < SHIP_SIZE; c++)
                _data[ship._row][c + ship._col] = 'B';
        else
            for (int r = 0; r < SHIP_SIZE; r++)
                _data[r + ship._row][ship._col] = 'B';
    }
}
