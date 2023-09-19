import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static final int MAX = 1000001;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        
        Segment segment = new Segment(MAX);
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(tokenizer.nextToken());
            int B = Integer.parseInt(tokenizer.nextToken());
            
            if (cmd == 1) {
                sb.append(segment.query(1, 1, MAX, B)).append("\n");
            } else {
                int C = Integer.parseInt(tokenizer.nextToken());
                segment.update(1, 1, MAX, B, C);
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Segment {
        int[] tree;

        public Segment(int treeSize) {
            int size = (int) Math.ceil(Math.log(treeSize) / Math.log(2)) + 1;
            int h = 2 << size;
            tree = new int[h];
        }

        public int query(int node, int start, int end, int idx) {
            if (start == end) {
                update(1, 1, MAX, start, -1);
                return start;
            }

            int mid = (start + end) >> 1;
            if (tree[node << 1] >= idx) {
                return query(node << 1, start, mid, idx);
            }            
            return query((node << 1) + 1, mid + 1, end, idx - tree[node << 1]);
        }

        public int update(int node, int start, int end, int idx, int diff) {
            if (idx < start || idx > end) {
                return tree[node];
            }

            if (start == end) {
                return tree[node] += diff;
            }

            int mid = (start + end) >> 1;
            return tree[node] = update(node << 1, start, mid, idx, diff) +
                    update((node << 1) + 1, mid + 1, end, idx, diff);
        }
    }
}