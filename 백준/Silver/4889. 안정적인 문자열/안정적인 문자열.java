import java.io.*;
import java.util.Stack;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringBuilder builder = new StringBuilder();
        int index = 0;
        while (true) {
            String input = br.readLine();
            if (input.startsWith("-")) {
                break;
            }

            index++;
            Stack<Character> stack = new Stack<>();
            stack.add(input.charAt(0));

            for (int i = 1; i < input.length(); i++) {
                char cur = input.charAt(i);
                if (!stack.isEmpty()) {
                    if (stack.peek() == '{' && cur == '}') {
                        stack.pop();
                    } else {
                        stack.add(cur);
                    }
                } else {
                    stack.add(cur);
                }
            }

            int count = 0;
            while (stack.size() >= 2) {
                char cur = stack.pop();
                if (cur == '{') {
                    if (stack.peek() == '}') {
                        count += 2;
                    } else {
                        count++;
                    }
                } else if (stack.peek() == '}') {
                    count++;
                }
                stack.pop();
            }
            builder.append(index).append(". ").append(count).append("\n");
        }

        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
