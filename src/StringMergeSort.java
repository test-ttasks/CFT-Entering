import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;

/**
 * @author Troitskaya Tamara (TT6)
 */

public class StringMergeSort {

    // вспомогательная функция для отладки и быстрой проверки результатов.
    private void print(LinkedList<String> array) {
        for (var i : array) {
            System.out.println(i);
        }
    }

    // по возрастанию
    private void isSorted(LinkedList<String> array, boolean isAsc) throws NotSortedArrayException {
        int n = array.size();
        if (isAsc) {
            for (int i = 1; i < n; i += 1) {
                if ((array.get(i)).compareTo(array.get(i - 1)) <= -1) {
                    System.out.println("error: " + array.get(i) + " < " + array.get(i - 1));
                    throw new NotSortedArrayException("Incorrect input: the input array is not sorted.");
                }
            }
        } else {
            for (int i = 1; i < n; i += 1) {
                if ((array.get(i)).compareTo(array.get(i - 1)) >= 1) {
                    System.out.println("error: " + array.get(i) + " > " + array.get(i - 1));
                    throw new NotSortedArrayException("Incorrect input: the input array is not sorted.");
                }
            }
        }
    }

    /* The most important method */
    public void go(Vector<String> paths, String outp, boolean isAsc) {
        LinkedList<LinkedList<String>> array = new LinkedList<>();

        try {
            int j = 0;
            for (var path : paths) {
                int i = 0;
                File inp = new File(path);
                Scanner sc = new Scanner(inp);

                LinkedList<String> tmp = new LinkedList<>();
                while (sc.hasNext()) {
                    tmp.add(sc.nextLine());
                    i += 1;
                }
                array.add(tmp);
                // небольшая проблема: если массив не отсортирован изначально, то ввод данных прекратится раньше конца
                isSorted(array.get(j), isAsc);
                j += 1;
            }
        }
        catch (FileNotFoundException fe) {
            System.err.println("File not found");
        }
        catch (NotSortedArrayException nsae) {
            System.err.println(nsae.getMessage());
        }

        // making the merge
        var tmp = twoArrMerge(array.get(0), array.get(1), isAsc);
        int n = array.size();

        for (int i = 2; i < n; i += 1) {
            tmp = twoArrMerge(tmp, array.get(i), isAsc);
        }

        // output final data
        output(tmp, outp);
    }

    // печатает в файл конечный массив
    private void output(LinkedList<String> a, String out) {
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(out));
            for (var i : a) {
                output.write(i + '\n');
            }
            output.close();
            print(a);
        }
        catch (IOException e) {
            System.err.println("Exception while output");
        }
    }

    // по возрастанию
    public LinkedList<String> twoArrMerge(LinkedList<String> A, LinkedList<String> B, boolean isAsc) {
        LinkedList<String> outp_arr = new LinkedList<>();
        int ia = 0, ib = 0;
        if (isAsc) {
            for (int i = 0; i < A.size() + B.size(); i += 1) {
                // кто меньше, тот и идёт
                if (ia < A.size() && ib < B.size() && A.get(ia).compareTo(B.get(ib)) <= -1) {
                    outp_arr.add(A.get(ia));
                    ia += 1;
                } else if (ib >= B.size()) {
                    outp_arr.add(A.get(ia));
                    ia += 1;
                } else {
                    outp_arr.add(B.get(ib));
                    ib += 1;
                }
            }
        } else {
            for (int i = 0; i < A.size() + B.size(); i += 1) {
                // кто больше, тот и идёт
                if (ib < B.size() && ia < A.size() && A.get(ia).compareTo(B.get(ib)) <= -1) {
                    outp_arr.add(B.get(ib));
                    ib += 1;
                } else if (ia >= A.size()) {
                    outp_arr.add(B.get(ib));
                    ib += 1;
                } else {
                    outp_arr.add(A.get(ia));
                    ia += 1;
                }
            }
        }
        return outp_arr;
    }
}
