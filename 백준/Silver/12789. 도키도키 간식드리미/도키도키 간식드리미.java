import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int index = 1;
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(tokenizer.nextToken());
            if (num == index) {
                index++;
            } else {
                can();
                stack.push(num);
            }
        }
        can();

        sb.append(stack.isEmpty() ? "Nice" : "Sad");
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static boolean can() {
        if (stack.isEmpty()) {
            return true;
        }
        while (!stack.isEmpty()) {
            if (stack.peek() != index) {
                return true;
            }
            stack.pop();
            index++;
        }
        return false;
    }
}