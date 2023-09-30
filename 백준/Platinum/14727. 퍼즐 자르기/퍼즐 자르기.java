import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        for (int i = 0; i < N; i++) {
            arr[i + 1] = Integer.parseInt(br.readLine());
        }
        arr[0] = Integer.MAX_VALUE;

        Segment segment = new Segment();
        segment.init(1, 1, N);
        sb.append(segment.query(1, N));

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Segment {
        int[] tree;

        public Segment() {
            tree = new int[N * 4];
        }

        public int init(int n, int s, int e) {
            if (s == e) {
                return tree[n] = s;
            }

            int m = (s + e) >> 1;
            int l = init(n << 1, s, m);
            int r = init((n << 1) + 1, m + 1, e);
            return tree[n] = arr[l] > arr[r] ? r : l;
        }

        public int minQuery(int n, int s, int e, int l, int r) {
            if (l > e || r < s) {
                return 0;
            }

            if (l <= s && r >= e) {
                return tree[n];
            }

            int m = (s + e) >> 1;
            int ll = minQuery(n << 1, s, m, l, r);
            int rr = minQuery((n << 1) + 1, m + 1, e, l, r);
            return arr[ll] > arr[rr] ? rr : ll;
        }

        public long query(int l, int r) {
            if (l > r) {
                return 0;
            }

            int idx = minQuery(1, 1, N, l, r);
            long ans = (long) (r - l + 1) * arr[idx];
            ans = Math.max(ans, query(l, idx - 1));
            ans = Math.max(ans, query(idx + 1, r));
            return ans;
        }
    }
}