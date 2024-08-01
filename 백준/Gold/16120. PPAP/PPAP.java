import java.io.*;
import java.util.Stack;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        String input = br.readLine();
        sb.append(solution(input));

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static String solution(String input) {
        if (input.equals("P")) {
            return "PPAP";
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == 'P') {
                stack.add(i);
            }

            if (c == 'A') {
                if (stack.size() >= 2 && i < input.length() - 1 && input.charAt(i + 1) == 'P') {
                    i++;
                    stack.pop();
                } else {
                    return "NP";
                }
            }
        }

        return stack.size() == 1 ? "PPAP" : "NP";
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}