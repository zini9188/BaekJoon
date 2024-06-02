import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static Queue<Integer> rq;
    static Queue<Integer> lq;
    static int M;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            lq = new PriorityQueue<>((o1, o2) -> o2 - o1);
            rq = new PriorityQueue<>(Comparator.comparingInt(o -> o));

            M = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            Queue<Integer> aq = new ArrayDeque<>();
            for (int j = 0; j < M; j++) {
                if (j >= 10 && j % 10 == 0) {
                    st = new StringTokenizer(br.readLine());
                }

                int num = Integer.parseInt(st.nextToken());

                if (lq.size() == rq.size()) {
                    lq.add(num);
                } else {
                    rq.add(num);
                }

                if (!rq.isEmpty()) {
                    if (!lq.isEmpty() && lq.peek() > rq.peek()) {
                        lq.add(rq.poll());
                        rq.add(lq.poll());
                    }
                }

                if ((j + 1) % 2 == 1) {
                    aq.add(lq.peek());
                }
            }

            int size = aq.size();
            sb.append(size).append("\n");
            for (int j = 0; j < size; j++) {
                if (j != 0 && j % 10 == 0) {
                    sb.append("\n");
                }
                sb.append(aq.poll()).append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}