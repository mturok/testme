import java.util.*;
import java.io.*;
import java.net.*;

public class Solution
{
    static char[][] _buffer = new char[20][20];

    public static void init()
    {
        for(int i = 0; i < _buffer.length; i ++)
            Arrays.fill(_buffer[i], ' ');
        gallows();
    }

    public static void printman(StringBuilder guessed)
    {
        for(int r = 0; r < _buffer.length; r++) {
            for(int c = 0; c < _buffer[r].length; c++)
                System.out.print(_buffer[r][c]);
            System.out.println();
        }
        System.out.println("\nMy guess so far: " + guessed.toString());
    }

    public static void gallows()
    {
        for(int r = 1; r < _buffer.length; r++)
            _buffer[r][4] = '|';

        for(int c = 4; c < 16; c++)
            _buffer[0][c] = '_';

        for(int r = 1; r < 4; r++)
            _buffer[r][15] = '|';

        for(int c = 0; c < _buffer[19].length; c++)
            _buffer[19][c] = '_';
    }

    public static void draw(char c, int coords[][])
    {
        for (int i = 0; i < coords.length; i++) {
            int [] coord = coords[i];
            _buffer[coord[0]][coord[1]] = c;
        }
    }

    public static void head()
    {
        draw('_', new int[][]{ {4, 14}, {4, 15}, {4, 16}, {7, 14}, {7, 15}, {7, 16} });
        draw('|', new int[][]{ {5, 13}, {6, 13}, {7, 13}, {5, 17}, {6, 17}, {7, 17} });
        draw('~', new int[][]{ {6, 15} });
        draw('*', new int[][]{ {5, 14}, {5, 16} });
    }

    public static void body()
    {
        draw('|', new int[][]{ {8, 15}, {9, 15}, {10, 15}, {11, 15}, {12, 15}, {12, 15}, {13, 15}, {14, 15} });
    }

    public static void l_arm()
    {
        draw('/', new int[][]{ {10, 14}, {11, 13}, {12, 12}, {13, 11} });
    }

    public static void r_arm()
    {
        draw('\\', new int[][]{ {10, 16}, {11, 17}, {12, 18}, {13, 19} });
    }

    public static void l_leg()
    {
        draw('/', new int[][]{ {15, 14}, {16, 13}, {17, 12}, {18, 11} });
    }

    public static void r_leg()
    {
        draw('\\', new int[][]{ {15, 16}, {16, 17}, {17, 18}, {18, 19} });
    }


    public static void add_part(int count)
    {
        if(count == 0)
            head();
        else if(count == 1)
            body();
        else if(count == 2)
            l_arm();
        else if(count == 3)
            r_arm();
        else if(count == 4)
            l_leg();
        else if(count == 5)
            r_leg();
    }

    public static String[] filtered_list(Scanner in, String[] words) {

        System.out.print("Difficulty: Max Number of Letters: ");
        int max_letters = in.nextInt();

        // find the minimum length of the strings in the list
        int min_word_size = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++ )
            min_word_size = Math.min(words[i].length(), min_word_size);

        if (max_letters < min_word_size) {
            System.out.println("==> List has min word size: " + min_word_size + ".  Using that for filter.");
            max_letters = min_word_size;
        }

        List<String> filtered = new ArrayList<String>();
        for (int i = 0; i < words.length; i++ )
            if (words[i].length() <= max_letters)
                filtered.add(words[i]);

        return filtered.toArray(new String[0]);
    }


    public static String[] words_list() {
        String[] words = {
            "cat",
            "giraffe",
            "moose",
            "blobfish",
            "platypus",
            "whale",
            "lion",
            "monkey",
            "wolf",
            "zebra"};
        return words;
    }

    public static String[] words_reader(BufferedReader reader) throws Exception{
        try {
            List<String> lines = new ArrayList<String>();

            String line;
            while((line = reader.readLine()) != null)
                lines.add(line);
            String[] words = lines.toArray(new String[0]);
            return words;
        }
        finally {
            reader.close();
        }
    }

    public static String[] words_file() throws Exception {
        // String filename = "/home/coderpad/data/animals.txt";
        String filename = "./animals.txt";
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        return words_reader(reader);
    }

    public static String[] words_url() throws Exception {
        URL url = new URL("https://cs.nyu.edu/~odeh/resources/python/animals.txt");
        // read text returned by server
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        return words_reader(reader);
    };

    public static String get_word(Scanner scanner) throws Exception {
        String[] words = words_url();
        // String[] words = words_list();
        // String[] words = words_file();

        words = filtered_list(scanner, words);
        int word_index = (int)(Math.random()*words.length);
        String word = words[word_index];

        // for debugging
        for(int i = 0; i < words.length; i++)
            System.out.println(words[i]);
        System.out.println("Word we randomly picked: " + word);

        return word;
    }

    public static void main(String[] args) throws Exception
    {
        Scanner scanner = new Scanner(System.in);
        init();

        // set up word list, word and guessed
        String word = get_word(scanner);
        StringBuilder guessed = new StringBuilder("_".repeat(word.length()));
        printman(guessed);

        for(int i = 0; i < 6; ) {
            System.out.print("Enter single letter guess: ");
            char guess = scanner.next().charAt(0);
            int index = word.indexOf(guess);
            if(index == -1)
                add_part(i++);
            else {
                while(index >= 0) {
                    guessed.setCharAt(index, guess);
                    index = word.indexOf(guess, index + 1);
                }
            }
            printman(guessed);

            if(guessed.toString().equals(word)) {
                System.out.println("You win!");
                return;
            }
        }
        System.out.println("You lose! The word was: " + word);
    }
}
