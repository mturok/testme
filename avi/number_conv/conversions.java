import java.util.Scanner;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

import java.util.Scanner;

class Solution
{
  static String _lookup = "0123456789ABCDEF";

  public static int to_decimal(String input, int base)
  {
    int len = input.length();
    int output = 0;
    for(int i = 0; i <len; i++)
    {
      int d = _lookup.indexOf(input.charAt(len-i-1));
      output += d*Math.pow(base, i);
    }
    return output;
  }

  public static String from_decimal(int input, int base)
  {
    String output = "";
    int remainder = 0;
    int quotient = input;
    while(quotient > 0)
    {
      remainder = quotient % base;
      quotient = quotient / base;
      output = _lookup.charAt(remainder) + output;
    }
    return output;
  }

  public static int get_base(String input)
  {
    if(input.equals("hex"))
      return(16);

    if (input.equals("dec"))
      return(10);

    if (input.equals("oct"))
      return(8);

    if (input.equals("bin"))
      return(2);

    return(-1);
  }

  public static void main(String[] args)
  {
    Scanner in = new Scanner(System.in);
    System.out.print("Enter the name of the number system to convert from: bin, or dec, or oct or hex: \n");
    String base_in_str = in.nextLine();

    System.out.print("Enter number as a string: \n");
    String num_str = in.nextLine();

    System.out.print("Enter the name of the number system to convert to: bin, or dec, or oct or hex: \n");
    String base_out_str = in.nextLine();

    int base_in = get_base(base_in_str);
    int base_out = get_base(base_out_str);

    if (base_in > 0 && base_out > 0)
    {
      // System.out.printf("In: %s: %d\n", base_in_str, base_in);
      // System.out.printf("Out: %s: %d\n", base_out_str, base_out);

      int decimal = to_decimal(num_str, base_in);
      String output = from_decimal(decimal, base_out);
      //System.out.println(decimal);
      System.out.println("The result is: " + output);
    }
    else
    {
      System.out.printf("Bad inputs: base in: %s, base out: %s\n", base_in_str, base_out_str);
    }
  }
}




// class Solution {
//   static String _lookup = "0123456789ABCDEF";

//   public static int to_decimal(String input, int base) throws Exception {
//     int output = 0;
//     int input_len = input.length();

//     for(int i = 0; i < input_len; i++) {
//       int index = input_len - i - 1;
//       char input_digit = input.charAt(index);
//       int input_num = _lookup.indexOf(input_digit);
//       if (input_num >= base)
//         throw new Exception(String.format(
//           "input[%d] is '%s' and > base: %d", index, input_digit, base));
//       output += input_num * Math.pow(base, i);
//     }
//     return output;
//   }

//   public static String from_decimal(int input, int base) {
//     String output = "";
//     int quotient = input;
//     while(quotient > 0) {
//       int remainder = quotient % base;
//       output = _lookup.charAt(remainder) + output;
//       quotient = quotient / base;
//     }
//     return output;
//   }

//   public static int base_str2int(String input) throws Exception{
//     int base = 0;
//     switch(input) {
//       case "hex": base = 16; break;
//       case "dec": base = 10; break;
//       case "oct": base =  8; break;
//       case "bin": base =  2; break;
//       default:  throw new Exception( "Unknown base" + input);
//     };
//     return base;
//   }

//   public static void main(String[] args) {
//     try {

//       // Using Scanner for Getting Input from User
//       Scanner in = new Scanner(System.in);

//       System.out.println("Enter the name of the number system to convert from: bin, or dec, or oct or hex" );
//       int input_base = base_str2int(in.nextLine());

//       System.out.println("Enter number as a String:");
//       String input_str = in.nextLine();

//       System.out.println("Enter the name of the number system to convert to: bin, or dec, or oct or hex" );
//       int output_base = base_str2int(in.nextLine());

//       int decimal = to_decimal(input_str, input_base);
//       String output = from_decimal(decimal, output_base);
//       System.out.println(output);

//       // System.out.println(from_decimal(255, 16));
//       // System.out.println(to_decimal("FF", 16));

//     } catch (Exception e) {
//       System.out.println(e.toString());
//     }
//   }
// }
