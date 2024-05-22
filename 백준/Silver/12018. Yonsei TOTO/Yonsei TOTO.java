import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int ans = 0;
        Queue<Point> usePoint = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int Pi = Integer.parseInt(st.nextToken());
            int Li = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            if (Pi < Li) {
                usePoint.add(new Point(1));
            } else {
                Queue<Point> q = new PriorityQueue<>();
                for (int j = 0; j < Pi; j++) {
                    q.add(new Point(Integer.parseInt(st.nextToken())));
                }

                while (q.size() > Li) {
                    q.poll();
                }

                if (!q.isEmpty()) {
                    int greedyPoint = q.poll().cost;
                    usePoint.add(new Point(greedyPoint));
                }
            }
        }

        while (!usePoint.isEmpty()) {
            Point p = usePoint.poll();

            if (m - p.cost >= 0) {
                ans++;
                m -= p.cost;
            }
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Point implements Comparable<Point> {

        int cost;

        public Point(int cost) {
            this.cost = cost;
        }

        @Override
        public int compareTo(Point o) {
            return cost - o.cost;
        }
    }
}