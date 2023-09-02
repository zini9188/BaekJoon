import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        Queue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.add(Integer.valueOf(br.readLine()));
        }

        int compare = 0;
        while (pq.size() > 1) {
            int A = pq.poll();
            int B = pq.poll();
            compare += A + B;
            pq.add(A + B);
        }
        sb.append(compare);

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}