import java.io.*;
import java.util.StringTokenizer;

public class Main {

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
            long c = Long.parseLong(tokenizer.nextToken());

            if (a == 1) {
                segment.update(1, 1, N, b, c - arr[b]);
                arr[b] = c;
            } else {
                sb.append(segment.sum(1, 1, N, b, (int) c)).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Segment {
        long[] tree;

        public Segment(int treeSize) {
            double s = Math.ceil(Math.log(treeSize) / Math.log(2)) + 1;
            long size = (int) Math.pow(2, s);
            tree = new long[Math.toIntExact(size)];
        }

        public long init(long[] arr, int node, int start, int end) {
            if (start == end) {
                return tree[node] = arr[start];
            }

            int mid = (start + end) / 2;
            return tree[node] = init(arr, node * 2, start, mid)
                    + init(arr, node * 2 + 1, mid + 1, end);
        }

        public void update(int node, int start, int end, int idx, long diff) {
            if (idx < start || end < idx) {
                return;
            }

            tree[node] += diff;

            if (start != end) {
                int mid = (start + end) / 2;
                update(node * 2, start, mid, idx, diff);
                update(node * 2 + 1, mid + 1, end, idx, diff);
            }
        }

        public long sum(int node, int start, int end, int left, int right) {
            if (left > end || right < start) {
                return 0;
            }

            if (left <= start && end <= right) {
                return tree[node];
            }

            int mid = (start + end) / 2;
            return sum(node * 2, start, mid, left, right)
                    + sum(node * 2 + 1, mid + 1, end, left, right);
        }
    }
}