import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int K = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            Queue<Long> pq = new PriorityQueue<>();
            for (int page = 0; page < K; page++) {
                pq.add(Long.valueOf(st.nextToken()));
            }

            long sum = 0;
            while (pq.size() > 1) {
                long temp = pq.poll() + pq.poll();
                pq.add(temp);
                sum += temp;
            }

            sb.append(sum).append("\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}