
import java.util.*;
import java.io.*;
import java.net.*;
import java.util.function.*;


class Solution{


    static char[][] _buffer = new char[20][20];

    static void draw(char c, int coords[][]) {
      for (int i = 0; i < coords.length; i++) {
          int [] coord = coords[i];
          _buffer[coord[0]][coord[1]] = c;
      }
    }

    static List<Runnable> _parts =
        new ArrayList<>(Arrays.asList(Solution::head,
                                      Solution::body,
                                      Solution::arm_left,
                                      Solution::arm_right,
                                      Solution::leg_left,
                                      Solution::leg_right));

    static void head() {
        draw('_', new int[][] { {4, 14}, {4, 16}, {6,14}, {6, 15}, {6,16} });
        draw('/', new int[][]{ {5, 13}, {6, 17} });
        draw('\\', new int[][]{ {6, 13}, {5, 17} });
    };

    static void body() {
        draw('|', new int[][]{ {7,15}, {8,15}, {9,15}, {10,15}, {11,15} });
    }

    static void arm_left() {
        draw('\\', new int[][]{ {8,12}, {8,13}, {9,13}, {9,14} });
    }

    static void arm_right() {
        draw('/', new int[][]{ {8,17}, {8,18}, {9,16}, {9,17} });
    }

    static void leg_left() {
        draw('/', new int[][]{ {12,13}, {12,14}, {13,12}, {13,13}, {14,11}, {14,12} } );
    }

    static void leg_right() {
        draw('\\', new int[][]{ {12,16}, {12,17}, {13,17}, {13,18}, {14,18}, {14,19} } );
    }


    public static void gallows() {
        // top bar
        for (int c=5; c<=15; c++)
            _buffer[1][c] = '=';

        // post
        for (int r=2; r<=17; r++)
            for(int c=5; c<=6; c++)
                _buffer[r][c] = '|';

        // base
        for (int c=0; c<=19; c++)
            _buffer[18][c] = '=';


        // rope
        for (int r=2; r<=4; r++)
            _buffer[r][15] = '$';

    }

    public static void printme(StringBuilder guessed)
    {
        for (int r = 0; r < _buffer.length; r++) {
            for (int c = 0; c < _buffer[r].length; c++)
                System.out.print( _buffer[r][c]);
            System.out.println();
        }

        System.out.println();
        System.out.println(guessed);
    }

    public static String[] word_list() {
        String words[] = new String[] {
            "alligator",
            "armadillo",
            "bear",
            "beaver",
            "butterfly",
            "camel",
            "chicken",
            "chipmunk",
            "cougar",
            "coyote",
        };
        return words;
    }

    public static String[] word_buffered_reader(BufferedReader reader) throws IOException {
        List<String> lines = new ArrayList<String>();

        String line;
        while((line = reader.readLine()) != null)
            lines.add(line);
        reader.close();
        return (lines.toArray(new String[]{}));
    }


    public static String[] word_file() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./animals.txt"));
            return word_buffered_reader(reader);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println("Using static list of words as fallback");
        return word_list();
    }

    public static String[] word_url() {

        try {
            URL url = new URL("https://cs.nyu.edu/~odeh/resources/python/animals.txt");
            // read text returned by server
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            return word_buffered_reader(reader);
        }
        catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());

        }
        System.out.println("Using static list of words as fallback");
        return word_list();
    }


    public static void init()
    {
        for (int i = 0; i < _buffer.length; i++)
            Arrays.fill(_buffer[i], ' ');

        gallows();
    }


    static String[] get_word_list(String mode)  {

        System.out.println("Mode: " + mode);

        switch(mode) {
        case "list":
            return word_list();
        case "file":
            return word_file();
        case "url":
            return word_url();
        default:
            System.out.println("Unknown mode: " + mode + ".  Falling back to list" );
            return word_list();
        }
    }


    public static void main(String[] args) {

        init();

        Scanner in = new Scanner(System.in);
        System.out.println("Pick source for words: list, file, url");
        String mode = in.nextLine();
        String words[] = get_word_list(mode);
        // for(int i = 0; i < words.length; i++)
        //     System.out.println(words[i]);

        String word = words[(int) (Math.random() * words.length)];

        // Strings are immutable. StringBuilder class lets us change chars.
        // Initialize the guessed word with underscores
        StringBuilder guessed = new StringBuilder("_".repeat(word.length()));
        printme(guessed);

        for(int i = 0; i < _parts.size(); ) {
            System.out.print("\nPick a letter: ");

            char guess = in.next().charAt(0);
            int index = word.indexOf(guess);
            if (index == -1) // guessed wrong
                _parts.get(i++).run(); // add a part to hangman
            else {
                // loop across all occurrences of letter in the word
                while (index >= 0) {
                    guessed.setCharAt(index, guess);
                    index = word.indexOf(guess, index + 1);
                }
            }

            printme(guessed);
            if (guessed.toString().equals(word)) {
                System.out.println( "You win" );
                return;
            };
        }
        System.out.println( "You lose: " + word );
    }
}


