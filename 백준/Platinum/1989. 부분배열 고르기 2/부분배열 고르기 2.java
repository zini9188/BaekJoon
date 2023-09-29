import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[] arr;
    static int i = 1, j = 1;
    static long ans;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i + 1] = Integer.parseInt(tokenizer.nextToken());
        }
        arr[0] = Integer.MAX_VALUE;

        Segment segment = new Segment();
        segment.initSum(1, 1, N);
        segment.initMin(1, 1, N);
        sb.append(segment.query(1, N)).append("\n");
        sb.append(i).append(" ").append(j);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Segment {
        long[] sumTree;
        int[] minTree;

        public Segment() {
            sumTree = new long[N * 4];
            minTree = new int[N * 4];
        }

        public long initSum(int n, int s, int e) {
            if (s == e) {
                return sumTree[n] = arr[s];
            }

            int m = (s + e) >> 1;
            return sumTree[n] = initSum(n << 1, s, m) +
                    initSum((n << 1) + 1, m + 1, e);
        }

        public int initMin(int n, int s, int e) {
            if (s == e) {
                return minTree[n] = s;
            }

            int m = (s + e) >> 1;
            int l = initMin(n << 1, s, m);
            int r = initMin((n << 1) + 1, m + 1, e);
            return minTree[n] = arr[l] > arr[r] ? r : l;
        }

        public long querySum(int n, int s, int e, int l, int r) {
            if (l > e || r < s) {
                return 0;
            }

            if (l <= s && r >= e) {
                return sumTree[n];
            }

            int m = (s + e) >> 1;
            return querySum(n << 1, s, m, l, r) +
                    querySum((n << 1) + 1, m + 1, e, l, r);
        }

        public int queryMin(int n, int s, int e, int l, int r) {
            if (l > e || r < s) {
                return 0;
            }

            if (l <= s && r >= e) {
                return minTree[n];
            }

            int m = (s + e) >> 1;
            int li = queryMin(n << 1, s, m, l, r);
            int ri = queryMin((n << 1) + 1, m + 1, e, l, r);
            return arr[li] > arr[ri] ? ri : li;
        }

        public long query(int l, int r) {
            if (l > r) {
                return 0;
            }

            int index = queryMin(1, 1, N, l, r);
            long sum = querySum(1, 1, N, l, r);
            long t = (sum * arr[index]);
            long ll = query(l, index - 1);
            long rr = query(index + 1, r);

            if (t > ans) {
                ans = t;
                i = l;
                j = r;
            }

            if (ll > ans) {
                i = l;
                j = index - 1;
                ans = ll;
            }

            if (rr > ans) {
                i = index + 1;
                j = r;
                ans = rr;
            }
            return ans;
        }
    }
}