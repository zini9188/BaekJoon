import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static final int MOD = 1_000_000_007;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        long[] arr = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        Segment segment = new Segment(N);
        segment.init(arr, 1, 1, N);

        for (int i = 0; i < M + K; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());

            if (a == 1) {
                segment.update(1, 1, N, b, c);
            } else {
                sb.append(segment.mul(1, 1, N, b, c) % MOD).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
    static class Segment {
        long[] tree;

        public Segment(int treeSize) {
            double size = Math.ceil(Math.log(treeSize) / Math.log(2)) + 1;
            int h = (int) Math.pow(2, size);
            tree = new long[h];
            Arrays.fill(tree, 1);
        }

        public long init(long[] arr, int node, int start, int end) {
            if (start == end) {
                return tree[node] = arr[start];
            }

            return tree[node] = (init(arr, node * 2, start, (start + end) / 2) *
                    init(arr, node * 2 + 1, (start + end) / 2 + 1, end)) % MOD;
        }

        public long update(int node, int start, int end, int idx, int value) {
            if (idx < start || end < idx) {
                return tree[node];
            }

            if (start == end) {
                return tree[node] = value;
            }

            return tree[node] = (update(node * 2, start, (start + end) / 2, idx, value) *
                    update(node * 2 + 1, (start + end) / 2 + 1, end, idx, value)) % MOD;
        }

        public long mul(int node, int start, int end, int left, int right) {
            if (left > end || right < start) {
                return 1;
            }

            if (left <= start && right >= end) {
                return tree[node];
            }

            return (mul(node * 2, start, (start + end) / 2, left, right) *
                    mul(node * 2 + 1, (start + end) / 2 + 1, end, left, right)) % MOD;
        }
    }
}