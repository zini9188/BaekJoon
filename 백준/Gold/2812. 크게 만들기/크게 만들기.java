import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = read();
        int K = read();

        String number = br.readLine();

        int len = number.length();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            int current = number.charAt(i) - '0';
            while (!stack.isEmpty() && stack.peek() < current && K > 0) {
                stack.pop();
                K--;
            }
            stack.push(current);
        }

        while (K > 0) {
            stack.pop();
            K--;
        }

        for (Integer element : stack) {
            sb.append(element);
        }
        
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        boolean negative = n == 13;
        if (negative) {
            n = 0;
        }

        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return negative ? -n : n;
    }
}