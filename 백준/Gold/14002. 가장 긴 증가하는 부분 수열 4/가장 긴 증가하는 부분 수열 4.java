import java.io.*;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int N;
    static Pair[] arr;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        arr = new Pair[N + 1];
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = new Pair(i, Integer.parseInt(tokenizer.nextToken()));
        }
        arr[0] = new Pair(-1000000, -1000000001);
        Arrays.sort(arr);
        Segment segment = new Segment();

        int ans = 0;
        int[] c = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            int v = segment.query(1, 1, N, 1, arr[i].idx) + 1;
            c[arr[i].idx] = v;
            if (v > ans) {
                ans = v;
            }
            segment.update(1, 1, N, arr[i].idx, v);
        }

        sb.append(ans).append("\n");
        Stack<Integer> stack = new Stack<>();
        for (int i = N; i > 0; i--) {
            if (c[arr[i].idx] == ans) {
                stack.add(arr[i].value);
                ans--;
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
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

        public int query(int node, int start, int end, int left, int right) {
            if (left > end || right < start) {
                return 0;
            }

            if (left <= start && right >= end) {
                return tree[node];
            }

            int mid = (start + end) >> 1;
            return Math.max(query(node << 1, start, mid, left, right),
                    query((node << 1) + 1, mid + 1, end, left, right));
        }

        public int update(int node, int start, int end, int idx, int k) {
            if (idx > end || idx < start) {
                return tree[node];
            }

            if (start == end) {
                return tree[node] = k;
            }

            int mid = (start + end) >> 1;
            return tree[node] = Math.max(update(node << 1, start, mid, idx, k),
                    update((node << 1) + 1, mid + 1, end, idx, k));
        }
    }

    static class Pair implements Comparable<Pair> {
        int idx;
        int value;

        public Pair(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }

        @Override
        public int compareTo(Pair o) {
            if (o.value == this.value) {
                return o.idx - this.idx;
            }
            return this.value - o.value;
        }
    }
}