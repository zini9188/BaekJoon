import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int[] arr;

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int Q = Integer.parseInt(tokenizer.nextToken());

        arr = new int[N + 1];
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
        
        Segment segment = new Segment(N);
        segment.init(1, 1, N);

        for (int i = 0; i < Q; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());

            if (x > y) {
                sb.append(segment.query(1, 1, N, y, x)).append("\n");
            } else {
                sb.append(segment.query(1, 1, N, x, y)).append("\n");
            }
            segment.update(1, 1, N, a, b);
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Segment {
        long[] tree;

        public Segment(int treeSize) {
            int size = (int) (Math.ceil(Math.log(treeSize) / Math.log(2)) + 1);
            int h = 2 << size;
            tree = new long[h];
        }

        public long init(int node, int start, int end) {
            if (start == end) {
                return tree[node] = arr[start];
            }

            int mid = (start + end) >> 1;
            return tree[node] = init(node << 1, start, mid)
                    + init((node << 1) + 1, mid + 1, end);
        }

        public long update(int node, int start, int end, int idx, int k) {
            if (idx > end || idx < start) {
                return tree[node];
            }
            if (start == end) {
                return tree[node] = k;
            }

            int mid = (start + end) >> 1;
            return tree[node] = update(node << 1, start, mid, idx, k) +
                    update((node << 1) + 1, mid + 1, end, idx, k);
        }

        public long query(int node, int start, int end, int left, int right) {
            if (left > end || right < start) {
                return 0;
            }

            if (left <= start && right >= end) {
                return tree[node];
            }

            int mid = (start + end) >> 1;
            return query(node << 1, start, mid, left, right) +
                    query((node << 1) + 1, mid + 1, end, left, right);
        }
    }
}