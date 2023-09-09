import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static final Pair empty = new Pair(0, 0);
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
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(tokenizer.nextToken());
            int l = Integer.parseInt(tokenizer.nextToken());
            int r = Integer.parseInt(tokenizer.nextToken());
            if (cmd == 1) {
                segment.update(1, 1, N, l, r);
            } else if (cmd == 2) {
                sb.append(segment.query(1, 1, N, l, r).even).append("\n");
            } else {
                sb.append(segment.query(1, 1, N, l, r).odd).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static Pair plus(Pair a, Pair b) {
        return new Pair(a.odd + b.odd, a.even + b.even);
    }

    static class Segment {
        Pair[] tree;

        public Segment(int treeSize) {
            int size = (int) (Math.ceil(Math.log(treeSize) / Math.log(2)) + 1);
            int h = 2 << size;
            tree = new Pair[h];
        }

        public Pair init(int node, int start, int end) {
            if (start == end) {
                int odd = arr[start] % 2 == 0 ? 0 : 1;
                int even = arr[start] % 2 == 0 ? 1 : 0;
                return tree[node] = new Pair(odd, even);
            }

            int mid = (start + end) >> 1;
            return tree[node] = plus(init(node << 1, start, mid), init((node << 1) + 1, mid + 1, end));
        }

        public Pair update(int node, int start, int end, int idx, int x) {
            if (idx < start || idx > end) {
                return tree[node];
            }

            if (start == end) {
                if (x % 2 == 0) {
                    tree[node].even = 1;
                    tree[node].odd = 0;
                } else {
                    tree[node].even = 0;
                    tree[node].odd = 1;
                }
                return tree[node];
            }

            int mid = (start + end) >> 1;
            return tree[node] = plus(update(node << 1, start, mid, idx, x),
                    update((node << 1) + 1, mid + 1, end, idx, x));
        }

        public Pair query(int node, int start, int end, int left, int right) {
            if (left > end || right < start) {
                return empty;
            }

            if (left <= start && end <= right) {
                return tree[node];
            }

            int mid = (start + end) >> 1;
            return plus(query(node << 1, start, mid, left, right),
                    query((node << 1) + 1, mid + 1, end, left, right));
        }
    }

    static class Pair {
        int odd, even;

        public Pair(int odd, int even) {
            this.odd = odd;
            this.even = even;
        }
    }
}