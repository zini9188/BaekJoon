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
        int N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
        
        Segment segment = new Segment(N);
        segment.init(1, 1, N);
        int M = Integer.parseInt(br.readLine());
        for (int m = 0; m < M; m++) {
            tokenizer = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(tokenizer.nextToken());
            int i = Integer.parseInt(tokenizer.nextToken());
            if (cmd == 1) {
                int v = Integer.parseInt(tokenizer.nextToken());
                segment.update(1, 1, N, i, v);
            } else {
                int j = Integer.parseInt(tokenizer.nextToken());
                sb.append(segment.query(1, 1, N, i, j)).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Segment {
        int[] tree;

        public Segment(int treeSize) {
            double size = Math.ceil(Math.log(treeSize) / Math.log(2)) + 1;
            int h = (int) Math.pow(2, size);
            tree = new int[h];
            Arrays.fill(tree, Integer.MAX_VALUE);
        }

        public int init(int node, int start, int end) {
            if (start == end) {
                return tree[node] = arr[start];
            }

            int mid = (start + end) >> 1;
            return tree[node] = Math.min(init(node << 1, start, mid), init((node << 1) + 1, mid + 1, end));
        }

        public int update(int node, int start, int end, int idx, int v) {
            if (idx < start || idx > end) {
                return tree[node];
            }

            if (start == end) {
                return tree[node] = v;
            }

            int mid = (start + end) >> 1;
            return tree[node] = Math.min(update(node << 1, start, mid, idx, v),
                                         update((node << 1) + 1, mid + 1, end, idx, v));
        }

        public int query(int node, int start, int end, int left, int right) {
            if (left > end || right < start) {
                return Integer.MAX_VALUE;
            }

            if (left <= start && end <= right) {
                return tree[node];
            }

            int mid = (start + end) >> 1;
            return Math.min(query(node << 1, start, mid, left, right), 
                            query((node << 1) + 1, mid + 1, end, left, right));

        }
    }
}