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
        tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        arr = new int[N + 1];
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i + 1] = Integer.parseInt(tokenizer.nextToken());
        }

        Segment segment = new Segment();
        segment.init(1, 1, N);

        for (int i = M; i <= N - M + 1; i++) {
            sb.append(segment.query(1, 1, N, i - M + 1, i + M - 1)).append(" ");
        }

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
                return tree[n] = arr[s];
            }

            int m = (s + e) >> 1;
            return tree[n] = Math.max(init(n << 1, s, m),
                    init((n << 1) + 1, m + 1, e));
        }

        public int query(int n, int s, int e, int l, int r) {
            if (l > e || r < s) {
                return 0;
            }

            if (l <= s && r >= e) {
                return tree[n];
            }

            int m = (s + e) >> 1;
            return Math.max(query(n << 1, s, m, l, r),
                    query((n << 1) + 1, m + 1, e, l, r));
        }
    }
}