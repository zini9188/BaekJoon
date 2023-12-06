import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int dasom = Integer.parseInt(br.readLine());
        Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 1; i < N; i++) {
            queue.add(Integer.valueOf(br.readLine()));
        }

        int count = 0;
        while (!queue.isEmpty() && dasom <= queue.peek()) {
            int vote = queue.poll();
            if (dasom <= vote) {
                dasom++;
                queue.add(--vote);
                count++;
            }
        }

        sb.append(count);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}