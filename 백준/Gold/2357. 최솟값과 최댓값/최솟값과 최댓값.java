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
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Segment segment = new Segment(N);
        segment.initMax(1, 1, N);
        segment.initMin(1, 1, N);

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());

            sb.append(segment.queryMin(1, 1, N, a, b)).append(" ").append(segment.queryMax(1, 1, N, a, b)).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Segment {
        int[] max;
        int[] min;

        public Segment(int treeSize) {
            double size = Math.ceil(Math.log(treeSize) / Math.log(2)) + 1;
            int h = (int) Math.pow(2, size);
            max = new int[h];
            min = new int[h];
            Arrays.fill(min, Integer.MAX_VALUE);
        }

        public int initMax(int node, int start, int end) {
            if (start == end) {
                return max[node] = arr[start];
            }

            int mid = (start + end) / 2;
            return max[node] = Math.max(initMax(node * 2, start, mid), initMax(node * 2 + 1, mid + 1, end));
        }

        public int initMin(int node, int start, int end) {
            if (start == end) {
                return min[node] = arr[start];
            }

            int mid = (start + end) / 2;
            return min[node] = Math.min(initMin(node * 2, start, mid), initMin(node * 2 + 1, mid + 1, end));
        }

        public int queryMin(int node, int start, int end, int left, int right) {
            if (left > end || start > right) {
                return Integer.MAX_VALUE;
            }

            if (left <= start && right >= end) {
                return min[node];
            }

            int mid = (start + end) / 2;
            return Math.min(queryMin(node * 2, start, mid, left, right),
                    queryMin(node * 2 + 1, mid + 1, end, left, right));
        }

        public int queryMax(int node, int start, int end, int left, int right) {
            if (left > end || start > right) {
                return 0;
            }

            if (left <= start && right >= end) {
                return max[node];
            }

            int mid = (start + end) / 2;
            return Math.max(queryMax(node * 2, start, mid, left, right),
                    queryMax(node * 2 + 1, mid + 1, end, left, right));
        }
    }
}