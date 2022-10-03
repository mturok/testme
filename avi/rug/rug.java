/*
 * Click `Run` to execute the snippet below!
 */

//import java.io.*;
//import java.util.*;


import java.util.Arrays;

public class Solution
{
  static int _WIDTH = 30;
  static int _HEIGHT = 30;
  static char _SHAPE = '~';

  static char[][] _arr = new char[_WIDTH][_HEIGHT];

  public static void init()
  {
    for (int i = 0; i < _WIDTH; i++)
      Arrays.fill(_arr[i], ' ');
  }

  public static void printme()
  {
    for (int r = 0; r < _HEIGHT; r++)
    {
      for (int c = 0; c < _WIDTH; c++)
        System.out.print( _arr[r][c]);
      System.out.println();
    }
  }

  public static int line(int row)
  {
    for(int c = 0; c < _WIDTH; c++)
      _arr[row][c] = _SHAPE;
    return (row);
  }

  public static int cube(int dim, int row, int col)
  {
    for (int r  = 0; r < dim; r++)
      for (int c = 0; c < dim; c++)
        _arr[r+row][c+col] = _SHAPE;
    return (row + dim - 1);
  }

  public static int checker(int dim, int row)
  {
    int last_row = 0;
    for (int i = 0; i < _WIDTH; i += dim*2)
      last_row = cube(dim, row, i);
    return(last_row);
  }

  public static int triangle(int dim, int row, int col)
  {
    for (int r = 0; r < dim; r++)
    {
      int width = r;
      for (int c = -width; c < width+1; c++)
        _arr[r+row][c+col] = _SHAPE;
    }
    return (row + dim - 1);
   }

   public static int triangle_row(int dim, int row)
  {
    int last_row = 0;
    for (int i = dim; i < _WIDTH; i += dim*2)
      last_row = triangle(dim, row, i);

    return(last_row);
  }

  public static int triangle_inv(int dim, int row, int col)
  {
    for (int r = 0; r < dim; r++)
    {
      int width = dim - (r + 1);
      //==> max width is when r == (dim -1)
      // at  r== dim -1, width = dim - (dim -1) ==> wdith +1
      // then columns go from -1, 0, +1 below
      for (int c = -width; c < width+1; c++)
        _arr[r+row][c+col] = _SHAPE;
    }
    return (row + dim - 1);
  }

  public static int triangle_inv_row(int dim, int row)
  {
    int last_row = 0;
    for (int i = dim; i < _WIDTH; i += dim*2)
      last_row = triangle_inv(dim, row, i);
    return(last_row);
  }

  public static int diamond(int dim, int row, int col)
  {
    int last_row = triangle (dim, row, col);
    last_row = triangle_inv(dim, last_row, col);
    return (last_row);
  }

  public static int diamond_row(int dim, int row)
  {
    int last_row = 0;
    for (int i = dim; i < _WIDTH; i += dim*2)
      last_row = diamond(dim, row, i);

    return(last_row);
  }

  public static void main(String args[])
  {
    init();
    int row = 0;
    row = line(row);
    row = line(row+1);
    row = diamond_row(3, row + 1);
    row = line(row + 1);
    row = checker(2, row + 1);
    row = line(row+1);
    row = triangle_inv_row(3, row+1);
    row = triangle_row(3, row +2);
    row = line(row+1);
    row = checker(2, row + 1);
    row = line(row + 1);
    row = diamond_row(3, row + 1);
    row = line(row + 1);
    row = line(row+1);
   // triangle_inv(5, row, 5);
    //diamond(5, 20, 15);
    //triangle_row(3,20);
    //diamond_row(3, 20);
    printme();
  }
}


// /*
//  * To execute Java, please define "static void main" on a class
//  * named Solution.
//  *
//  * If you need more classes, simply define them inline.
//  */

// class Solution {
//   public static void main(String[] args) {
//     ArrayList<String> strings = new ArrayList<String>();
//     strings.add("Hello, World!");
//     strings.add("Welcome to CoderPad.");
//     strings.add("This pad is running Java " + Runtime.version().feature());

//     for (String string : strings) {
//       System.out.println(string);
//     }
//   }
// }
