import java.io.*;
import java.util.ArrayList;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static boolean[] visited;
    static int[] result;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        visited = new boolean[51];
        result = new int[51];
        flag = false;
        String input = br.readLine();
        solution(input, 0);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static void solution(String input, int depth) {
        if (flag) {
            return;
        }

        if (input.isEmpty()) {
            for (int i = 1; i <= depth; i++) {
                if (!visited[i]) {
                    return;
                }
            }

            for (int i = 0; i < depth; i++) {
                sb.append(result[i]).append(" ");
            }

            flag = true;
            return;
        }

        for (int i = 1; i <= 2; i++) {
            if (input.length() < i) {
                continue;
            }

            int front = Integer.parseInt(input.substring(0, i));
            String back = input.substring(i);

            if (front >= 1 && front <= 50 && !visited[front]) {
                visited[front] = true;
                result[depth] = front;
                solution(back, depth + 1);
                visited[front] = false;
                result[depth] = 0;
            }
        }
    }
}