import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String line = br.readLine();
        Stack<Character> beforeBracket = new Stack<>();
        int result = 0;
        int tmp = 1;
        for (int i = 0; i < line.length(); i++) {
            char bracket = line.charAt(i);
            if (bracket == '[') {
                beforeBracket.add(bracket);
                tmp *= 3;
            } else if (bracket == '(') {
                beforeBracket.add(bracket);
                tmp *= 2;
            } else if (bracket == ')') {
                if (beforeBracket.isEmpty() || beforeBracket.peek() == '[') {
                    result = 0;
                    break;
                }
                if (line.charAt(i - 1) == '(') {
                    result += tmp;
                }
                tmp /= 2;
                beforeBracket.pop();
            } else {
                if (beforeBracket.isEmpty() || beforeBracket.peek() == '(') {
                    result = 0;
                    break;
                }
                if (line.charAt(i - 1) == '[') {
                    result += tmp;
                }
                tmp /= 3;
                beforeBracket.pop();
            }
        }
        if (!beforeBracket.isEmpty()) bw.write(0 + "");
        else bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }
}