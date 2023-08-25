import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());

        if (isError(a, b, c)) {
            sb.append("Error");
        } else {
            if (isEquilateral(a, b, c)) {
                sb.append("Equilateral");
            } else if (isScalene(a, b, c)) {
                sb.append("Scalene");
            } else if (isIsosceles(a, b, c)) {
                sb.append("Isosceles");
            }
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static boolean isIsosceles(int a, int b, int c) {
        return a == b && a != c || b == c && b != a || a == c && a != b;
    }

    private static boolean isScalene(int a, int b, int c) {
        return a != b && b != c && a != c;
    }

    private static boolean isEquilateral(int a, int b, int c) {
        return a == 60 && b == 60 && c == 60;
    }

    private static boolean isError(int a, int b, int c) {
        return a + b + c != 180;
    }
}
