import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();
        Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            if (x == 0) {
                if (queue.isEmpty()) {
                    builder.append("-1").append("\n");
                } else {
                    builder.append(queue.poll()).append("\n");
                }
            } else {
                for (int j = 0; j < x; j++) {
                    queue.add(Integer.valueOf(tokenizer.nextToken()));
                }
            }
        }
        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}