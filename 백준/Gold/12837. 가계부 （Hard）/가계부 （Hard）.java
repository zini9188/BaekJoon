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
        int Q = Integer.parseInt(tokenizer.nextToken());

        Segment segment = new Segment(N);
        for (int i = 0; i < Q; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(tokenizer.nextToken());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());

            if (cmd == 1) {
                segment.update(1, 1, N, a, b);
            } else {
                sb.append(segment.query(1, 1, N, a, b)).append("\n");
            }
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

        public long update(int node, int start, int end, int idx, int x) {
            if (idx < start || idx > end) {
                return tree[node];
            }

            if (start == end) {
                return tree[node] += x;
            }

            int mid = (start + end) >> 1;
            return tree[node] = update(node << 1, start, mid, idx, x) +
                    update((node << 1) + 1, mid + 1, end, idx, x);
        }

        public long query(int node, int start, int end, int left, int right) {
            if (left > end || right < start) {
                return 0;
            }

            if (left <= start && end <= right) {
                return tree[node];
            }

            int mid = (start + end) >> 1;
            return query(node << 1, start, mid, left, right) +
                    query((node << 1) + 1, mid + 1, end, left, right);
        }
    }
}