import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        while (true) {
            tokenizer = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            if (EndOfLine(a, b, c)) {
                break;
            }
            if (isInvalid(a, b, c)) {
                sb.append("Invalid\n");
            } else if (isEquilateral(a, b, c)) {
                sb.append("Equilateral\n");
            } else if (isScalene(a, b, c)) {
                sb.append("Scalene\n");
            } else if (isIsosceles(a, b, c)) {
                sb.append("Isosceles\n");
            } else {
                sb.append("Invalid\n");
            }
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static boolean EndOfLine(int a, int b, int c) {
        return a == 0 && b == 0 && c == 0;
    }

    private static boolean isIsosceles(int a, int b, int c) {
        return a == b && a != c || b == c && b != a || a == c && a != b;
    }

    private static boolean isScalene(int a, int b, int c) {
        return a != b && b != c && a != c;
    }

    private static boolean isEquilateral(int a, int b, int c) {
        return a == b && b == c;
    }

    private static boolean isInvalid(int a, int b, int c) {
        return a + b <= c || a + c <= b || b + c <= a;
    }
}
