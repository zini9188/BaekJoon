import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int[] arr;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        tokenizer = new StringTokenizer(br.readLine());
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }

        Segment segment = new Segment(N);
        segment.init(1, 1, N);

        int M = Integer.parseInt(br.readLine());
        ArrayList<Pair> updates = new ArrayList<>();
        ArrayList<Quadrupole> queries = new ArrayList<>();
        int index = 0;
        for (int m = 0; m < M; m++) {
            tokenizer = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(tokenizer.nextToken());
            if (cmd == 1) {
                int i = Integer.parseInt(tokenizer.nextToken());
                int v = Integer.parseInt(tokenizer.nextToken());
                updates.add(new Pair(i, v));
            } else {
                int k = Integer.parseInt(tokenizer.nextToken());
                int i = Integer.parseInt(tokenizer.nextToken());
                int j = Integer.parseInt(tokenizer.nextToken());
                queries.add(new Quadrupole(index++, k, i, j));
            }
        }
        Collections.sort(queries);

        index = 0;
        long[] ans = new long[queries.size()];
        for (Quadrupole query : queries) {
            while (updates.size() > index && query.k > index) {
                Pair pair = updates.get(index++);
                segment.update(1, 1, N, pair.i, pair.v);
            }
            ans[query.idx] = segment.query(1, 1, N, query.i, query.j);
        }
        for (long an : ans) {
            sb.append(an).append("\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Pair {
        int i, v;

        public Pair(int i, int v) {
            this.i = i;
            this.v = v;
        }
    }

    static class Quadrupole implements Comparable<Quadrupole> {
        int idx, k, i, j;

        public Quadrupole(int idx, int k, int i, int j) {
            this.idx = idx;
            this.k = k;
            this.i = i;
            this.j = j;
        }

        @Override
        public int compareTo(Quadrupole o) {
            return k - o.k;
        }
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
            return tree[node] = init(node << 1, start, mid) +
                    init((node << 1) + 1, mid + 1, end);
        }

        public long update(int node, int start, int end, int idx, int k) {
            if (idx < start || idx > end) {
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

            if (left <= start && end <= right) {
                return tree[node];
            }

            int mid = (start + end) >> 1;
            return query(node << 1, start, mid, left, right) +
                    query((node << 1) + 1, mid + 1, end, left, right);
        }
    }
}