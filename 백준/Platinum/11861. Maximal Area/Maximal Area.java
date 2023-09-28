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
        tokenizer = new StringTokenizer(br.readLine());
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
        arr[0] = Integer.MAX_VALUE;

        Segment segment = new Segment();
        segment.init(1, 1, N);
        sb.append(segment.search(1, N));

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
            return tree[n] = arr[r] > arr[l] ? l : r;
        }

        public int query(int n, int s, int e, int l, int r) {
            if (l > e || r < s) {
                return 0;
            }

            if (s >= l && e <= r) {
                return tree[n];
            }

            int m = (s + e) >> 1;
            int li = query(n << 1, s, m, l, r);
            int ri = query((n << 1) + 1, m + 1, e, l, r);
            return arr[ri] > arr[li] ? li : ri;
        }

        public int search(int l, int r) {
            if (l > r) {
                return 0;
            }

            int idx = query(1, 1, N, l, r);
            int ans = (r - l + 1) * arr[idx];
            ans = Math.max(search(l, idx - 1), ans);
            ans = Math.max(search(idx + 1, r), ans);
            return ans;
        }
    }
}