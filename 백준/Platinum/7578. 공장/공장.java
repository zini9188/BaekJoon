import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static Map<Integer, Integer> machines = new TreeMap<>();
    static Pair[] arr;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] arrr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arrr[i] = Integer.parseInt(st.nextToken());
        }

        arr = new Pair[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());
            machines.put(num, i);
            arr[i] = new Pair(i, num);
        }

        long ans = 0;
        Segment sg = new Segment(N);
        for (int i = 1; i <= N; i++) {
            int cnt = sg.query(1, N, machines.get(arrr[i]), N, 1);
            ans += cnt;
            sg.update(1, N, 1, machines.get(arrr[i]), 1);
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Segment {
        int[] tree;

        public Segment(int size) {
            tree = new int[size * 4 + 1];
        }

        public int query(int s, int e, int l, int r, int n) {
            if (l > e || s > r) {
                return 0;
            }

            if (l <= s && e <= r) {
                return tree[n];
            }

            int mid = (s + e) >> 1;
            return query(s, mid, l, r, n << 1) +
                    query(mid + 1, e, l, r, (n << 1) + 1);
        }

        public int update(int s, int e, int n, int idx, int k) {
            if (idx < s || idx > e) {
                return tree[n];
            }

            if (s == e) {
                return tree[n] += k;
            }

            int mid = (s + e) >> 1;
            return tree[n] = update(s, mid, n << 1, idx, k)
                    + update(mid + 1, e, (n << 1) + 1, idx, k);
        }
    }

    static class Pair implements Comparable<Pair> {
        int idx, num;

        public Pair(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }

        @Override
        public int compareTo(Pair o) {
            return idx - o.idx;
        }
    }
}