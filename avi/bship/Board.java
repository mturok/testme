public class Board {
    static int DIMENSION = 10;
    static int SHIP_SIZE = 4;
    char[][] _board = new char[DIMENSION][DIMENSION];

    public Board() {//initilization funtion (either _board or _guesses)
        for(int r = 0; r < this._board.length; r++)
          for(int c = 0; c < this._board[r].length; c++)
          this._board[r][c] = ' ';
    }

    public void printgrid() {//printing the grid (can print either _board or _guesses)
      String pattern = "+---";
      System.out.print(' ');
      for (char c = 'A'; c < 'A' + DIMENSION; c++)//col coordinates
        System.out.print("  " + c + " ");
      System.out.println();
      for(int r = 0; r < this._board.length; r++)
      {
        System.out.println(" " + pattern.repeat(DIMENSION) + "+");
        System.out.print(r);
        for(int c = 0; c < this._board[r].length; c++)
          System.out.print("| " + this._board[r][c] + ' ');
        System.out.println('|');
      }
      System.out.println(" " + pattern.repeat(DIMENSION) + "+");
    }

    public void ship() {//randomply placing ship
        int col_max = DIMENSION;
        int row_max = DIMENSION;
        int orientation = (int)(Math.random()*2);//random orientation

        if(orientation == 0) //if horizontal, reduce number of cols
          col_max -= SHIP_SIZE;
        else //if vertical, reduce number of rows
          row_max -= SHIP_SIZE;

        int row = (int)(Math.random()*row_max);
        int col = (int)(Math.random()*col_max);

        if(orientation == 0)
          for(int c = 0; c < SHIP_SIZE; c++)
            this._board[row][c + col] = 'B';
        else
          for(int r = 0; r < SHIP_SIZE; r++)
            this._board[r + row][col] = 'B';
      }
}
