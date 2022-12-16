public class Ship {

    int _row = -1;
    int _col = -1;
    int _orientation = -1;

    public Ship(int dim, int size) {// randomply placing ship

        int col_max = dim;
        int row_max = dim;
        _orientation = (int) (Math.random() * 2);// random orientation

        if (_orientation == 0) // if horizontal, reduce number of cols
            col_max -= size;
        else // if vertical, reduce number of rows
            row_max -= size;

        _row = (int) (Math.random() * row_max);
        _col = (int) (Math.random() * col_max);
    }
}
