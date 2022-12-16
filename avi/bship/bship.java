public class bship{
    static Board _board = new Board();
    static Board _guesses = new Board();
    Guess guess = new Guess();

    static int SHIP_SIZE = 4;
    static int DIMENSION = 10;
    public static void main(String[] args)  {

        _board.ship();//creation of ship
        _board.printgrid(); //- for debugging
        _guesses.printgrid();//printing board


        int hits = 0;
        int misses = 0;
        while(hits < SHIP_SIZE){//looping through each turn of player.
          boolean correct = Guess.verify_guess();
          if(correct == true)
            hits += 1;
          else
            misses += 1;
          _guesses.printgrid();//after turn, reprint
          System.out.println("Hits: " + hits + ", misses: " + misses + ", total tries: " + (hits + misses));//counter
        }
        System.out.println("You sunk my battle ship!");//end display
      }
}




