import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int[] arr;
    static int N;

    public static void main(String[] args) throws IOException {
        while (true) {
            tokenizer = new StringTokenizer(br.readLine());
            N = Integer.parseInt(tokenizer.nextToken());
            if (N == 0) {
                break;
            }

            arr = new int[N + 1];
            arr[0] = Integer.MAX_VALUE;
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(tokenizer.nextToken());
            }

            Segment segment = new Segment(N + 1);
            segment.init(1, 1, N);
            sb.append(segment.search(1, N)).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Segment {
        int[] tree;

        public Segment(int size) {
            tree = new int[size * 4];
        }

        public int init(int node, int start, int end) {
            if (start == end) {
                return tree[node] = start;
            }

            int mid = (start + end) >> 1;
            int l = init(node << 1, start, mid);
            int r = init((node << 1) + 1, mid + 1, end);
            return tree[node] = arr[l] > arr[r] ? r : l;
        }

        public int query(int node, int start, int end, int left, int right) {
            if (left > end || right < start) {
                return 0;
            }

            if (left <= start && right >= end) {
                return tree[node];
            }

            int mid = (start + end) >> 1;
            int l = query(node << 1, start, mid, left, right);
            int r = query((node << 1) + 1, mid + 1, end, left, right);
            return arr[l] > arr[r] ? r : l;
        }

        public long search(int left, int right) {
            if (left > right) {
                return 0;
            }
            int index = query(1, 1, N, left, right);
            long ans = (long) (right - left + 1) * arr[index];
            ans = Math.max(ans, search(left, index - 1));
            ans = Math.max(ans, search(index + 1, right));
            return ans;
        }
    }
}