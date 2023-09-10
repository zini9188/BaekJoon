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
        int M = Integer.parseInt(tokenizer.nextToken());
        arr = new int[N + 1];
        Segment segment = new Segment(N);

        for (int m = 0; m < M; m++) {
            tokenizer = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(tokenizer.nextToken());
            int i = Integer.parseInt(tokenizer.nextToken());
            int j = Integer.parseInt(tokenizer.nextToken());
            if (cmd == 0) {
                if (i > j) {
                    int temp = j;
                    j = i;
                    i = temp;
                }
                sb.append(segment.sum(1, 1, N, i, j)).append("\n");
            } else {
                segment.modify(1, 1, N, i, j);
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


        public long modify(int node, int start, int end, int idx, int v) {
            if (idx < start || idx > end) {
                return tree[node];
            }

            if (start == end) {
                return tree[node] = v;
            }

            int mid = (start + end) >> 1;
            return tree[node] = modify(node << 1, start, mid, idx, v) +
                    modify((node << 1) + 1, mid + 1, end, idx, v);
        }

        public long sum(int node, int start, int end, int left, int right) {
            if (end < left || start > right) {
                return 0;
            }

            if (left <= start && end <= right) {
                return tree[node];
            }

            int mid = (start + end) >> 1;
            return sum(node << 1, start, mid, left, right) +
                    sum((node << 1) + 1, mid + 1, end, left, right);
        }
    }
}