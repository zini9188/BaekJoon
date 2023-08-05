import java.io.*;
import java.util.Stack;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String operation = br.readLine();
        Stack<Character> stack = new Stack<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < operation.length(); i++) {
            char c = operation.charAt(i);
            if ('A' <= c && c <= 'Z') {
                builder.append(c);
            } else {
                if (c == '(') {
                    stack.add(c);
                    continue;
                } else if (c == ')') {
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        builder.append(stack.pop());
                    }
                    stack.pop();
                    continue;
                }
                while (!stack.isEmpty() && getPriority(stack.peek()) >= getPriority(c)) {
                    builder.append(stack.pop());
                }
                stack.add(c);
            }
        }

        while (!stack.isEmpty()) {
            builder.append(stack.pop());
        }

        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int getPriority(char c) {
        if (c == '/' || c == '*') {
            return 2;
        } else if (c == '+' || c == '-') {
            return 1;
        } else if (c == '(' || c == ')') {
            return 0;
        }
        return -1;
    }
}