/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
import java.util.*;



/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  static String _lookup = "0123456789ABCDEF";

  public static int to_decimal(String input, int base) throws Exception {
    int output = 0;
    int input_len = input.length();

    for(int i = 0; i < input_len; i++) {
      int index = input_len - i - 1;
      char input_digit = input.charAt(index);
      int input_num = _lookup.indexOf(input_digit);
      if (input_num >= base)
        throw new Exception(String.format(
          "input[%d] is '%s' and > base: %d", index, input_digit, base));
      output += input_num * Math.pow(base, i);
    }
    return output;
  }

  public static String from_decimal(int input, int base) {
    String output = "";
    int quotient = input;
    while(quotient > 0) {
      int remainder = quotient % base;
      output = _lookup.charAt(remainder) + output;
      quotient = quotient / base;
    }
    return output;
  }

  public static void main(String[] args) {
    try {
      System.out.println(from_decimal(255, 16));
      System.out.println(to_decimal("FF", 16));

    } catch (Exception e) {
      System.out.println(e.toString());
    }
  }
}
