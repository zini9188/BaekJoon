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
            if (cmd == 1) {
                int i = Integer.parseInt(tokenizer.nextToken());
                int v = Integer.parseInt(tokenizer.nextToken());
                segment.update(1, 1, N, i, v);
            } else {
                sb.append(segment.query(1, 1, N, 1, N).idx).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static Pair getMin(Pair a, Pair b) {
        if (a.num > b.num) {
            return b;
        }
        if (b.num > a.num) {
            return a;
        }
        if (b.idx > a.idx) {
            return a;
        }
        return b;
    }

    static class Segment {
        Pair[] tree;

        public Segment(int treeSize) {
            double size = Math.ceil(Math.log(treeSize) / Math.log(2)) + 1;
            int h = (int) Math.pow(2, size);
            tree = new Pair[h];
            for (int i = 1; i < h; i++) {
                tree[i] = new Pair(i, Integer.MAX_VALUE);
            }
        }

        public Pair init(int node, int start, int end) {
            if (start == end) {
                tree[node].num = arr[start];
                tree[node].idx = start;
                return tree[node];
            }

            int mid = (start + end) >> 1;
            return tree[node] = getMin(init(node << 1, start, mid), init((node << 1) + 1, mid + 1, end));
        }

        public Pair update(int node, int start, int end, int idx, int v) {
            if (idx < start || idx > end) {
                return tree[node];
            }

            if (start == end) {
                tree[node].num = v;
                return tree[node];
            }

            int mid = (start + end) >> 1;
            return tree[node] = getMin(update(node << 1, start, mid, idx, v),
                    update((node << 1) + 1, mid + 1, end, idx, v));
        }

        public Pair query(int node, int start, int end, int left, int right) {
            if (left > end || right < start) {
                return new Pair(-1, Integer.MAX_VALUE);
            }

            if (left <= start && end <= right) {
                return tree[node];
            }

            int mid = (start + end) >> 1;
            return getMin(query(node << 1, start, mid, left, right), query((node << 1) + 1, mid + 1, end, left, right));
        }
    }

    static class Pair {
        int idx, num;

        public Pair(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
    }
}