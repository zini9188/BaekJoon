import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        String input = br.readLine();

        Stack<Integer> num = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i))) {
                num.push(input.charAt(i) - '0');
            } else {
                int a = num.pop();
                int b = num.pop();
                if(input.charAt(i) == '+') {
                    num.push(a + b);
                } else if (input.charAt(i) == '-') {
                    num.push(b - a);
                } else if (input.charAt(i) == '*') {
                    num.push(a * b);
                } else {
                    num.push(b / a);
                }
            }
        }

        sb.append(num.pop());
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int readInt() throws IOException {
        int c, n = System.in.read() & 15;
        boolean negative = n == 13;
        if (negative) {
            n = 0;
        }

        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        if (c == 13) {
            System.in.read();
        }

        return negative ? -n : n;
    }
}