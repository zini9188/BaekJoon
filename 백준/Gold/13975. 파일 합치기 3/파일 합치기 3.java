import java.io.*;
import java.util.*;

public class Main {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static FastReader fr = new FastReader();
    
    public static void main(String[] args) throws IOException {
        int T = fr.nextInt();
        for (int tc = 0; tc < T; tc++) {
            int K = fr.nextInt();

            Queue<Long> pq = new PriorityQueue<>();
            for (int page = 0; page < K; page++) {
                pq.add(fr.nextLong());
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
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}