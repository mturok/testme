import java.util.*;
public class Guess {
    static int DIMENSION = 10;
    static char[][] _board = new char[DIMENSION][DIMENSION];
    static char[][] _guesses = new char[DIMENSION][DIMENSION];

    public static int guess_check(int guess) throws Exception {//making sure ship is in proper boundries
        if(guess < 0 || guess >= DIMENSION)
          throw new Exception();
        return(guess);
      }

    public static int[] read_guesses() {//taking user input guess, returns 1D array of integer values for the guess
        Scanner in = new Scanner(System.in);
        while(true){//infinate loop until a valid input is submitted
            System.out.print("Enter coordinate guess: ");
            String guess = in.nextLine();

            try{
            int row = guess_check(Integer.parseInt(guess.substring(1)));//converts to int
            int col = guess_check(guess.charAt(0) - 'A');
            int[] spot = {row, col};
            return(spot);
            }
            catch (Exception e) {
            System.out.println("Bad guess: " + guess);
            }
            in.close();
        }
    }

    public static boolean verify_guess() {//checking value against ship location
        int[] val = read_guesses();
        int row = val[0];
        int col = val[1];

        if(_board[row][col] == 'B'){
            _guesses[row][col] = 'X';
            return true;
        }
        else{
            _guesses[row][col] = '#';
            return false;
        }
    }
}
