import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        while (N-- > 0) {
            int x = Integer.parseInt(br.readLine());
            if (x == 0) {
                if (queue.isEmpty()) {
                    builder.append("0").append("\n");
                } else
                    builder.append(queue.poll()).append("\n");
            } else {
                queue.add(x);
            }
        }
        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}