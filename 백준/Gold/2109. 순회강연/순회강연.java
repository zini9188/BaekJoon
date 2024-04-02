import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        Queue<College> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            pq.add(new College(d, p));
        }

        int ans = 0;
        int[] cost = new int[10001];
        while (!pq.isEmpty()) {
            College cur = pq.poll();

            for (int d = cur.d; d > 0; d--) {
                if (cost[d] < cur.p) {
                    cost[d] = cur.p;
                    break;
                }
            }
        }

        for (int i = 10000; i > 0; i--) {
            ans += cost[i];
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class College implements Comparable<College> {

        int d, p;

        public College(int d, int p) {
            this.d = d;
            this.p = p;
        }

        @Override
        public int compareTo(College o) {
            return o.p - this.p;
        }
    }
}