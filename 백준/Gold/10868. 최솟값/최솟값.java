import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static long[] arr;

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        arr = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        Segment segment = new Segment(N);
        segment.init(1, 1, N);

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            sb.append(segment.query(1, 1, N, a, b)).append("\n");
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
            Arrays.fill(tree, Integer.MAX_VALUE);
        }

        public long init(int node, int start, int end) {
            if (start == end) {
                return tree[node] = arr[start];
            }

            int mid = (start + end) / 2;
            return tree[node] = Math.min(init(node * 2, start, mid), init(node * 2 + 1, mid + 1, end));
        }

        public long query(int node, int start, int end, int left, int right) {
            if (left > end || right < start) {
                return Integer.MAX_VALUE;
            }

            if (left <= start && end <= right) {
                return tree[node];
            }

            int mid = (start + end) / 2;
            return Math.min(query(node * 2, start, mid, left, right), query(node * 2 + 1, mid + 1, end, left, right));
        }
    }
}