import java.util.Vector;

/**
 * @author Troitskaya Tamara (TT6)
 */

public class Main {

    public static boolean in(String key, String[] array) {
        for (var item : array) {
            if (item.equals(key)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        final boolean isAscending;
        final String output_file;
        Vector<String> inp_files = new Vector<>();
        final int inpstart; // the first index of input files

        try {
            // filenames
            // output file
            if (!args[1].startsWith("-")) {
                output_file = args[1];
                inpstart = 2;
            } else {
                output_file = args[2];
                inpstart = 3;
            }
            // input files
            for (int i = inpstart; i < args.length; i += 1) {
                inp_files.add(args[i]);
            }

            // input sorting parameters and do the sorting
            isAscending = !in("-d", args);

            // rewritten using generics
            if (in("-i", args)) {
                MergeSortGenerics<Integer> tg = new MergeSortGenerics<>();
                tg.go(inp_files, output_file, isAscending);
            } else if (in("-s", args)) {
                MergeSortGenerics<String> tg = new MergeSortGenerics<>();
                tg.go(inp_files, output_file, isAscending);
            } else {
                throw new IncorrInputException("You did not input neither -s nor -i");
            }
        }
        catch (IncorrInputException incorrectinput) {
            System.err.println(incorrectinput.getMessage());
        }
    }
}
