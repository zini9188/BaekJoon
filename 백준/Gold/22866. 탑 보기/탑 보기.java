import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static final int MAX = 100001;
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = read();
        int[] shortest = new int[N + 1];
        Arrays.fill(shortest, MAX);
        int[] height = new int[N + 1];
        int[] count = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            height[i] = read();
        }

        Stack<Node> stack = new Stack<>();
        for (int i = 1; i <= N; i++) {
            run(shortest, height, count, stack, i);
        }

        stack.clear();
        for (int i = N; i >= 1; i--) {
            run(shortest, height, count, stack, i);
        }

        for (int i = 1; i <= N; i++) {
            if (shortest[i] == MAX) {
                sb.append(0);
            } else {
                sb.append(count[i]).append(" ").append(shortest[i]);
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void run(int[] shortest, int[] height, int[] count, Stack<Node> stack, int id) {
        while (!stack.isEmpty() && stack.peek().h <= height[id]) {
            stack.pop();
        }
        int t = stack.size();
        count[id] += t;

        if (t > 0) {
            if (shortest[id] == MAX) {
                shortest[id] = stack.peek().id;
            } else {

                int dist1 = Math.abs(id - shortest[id]);
                int dist2 = Math.abs(id - stack.peek().id);

                if (dist1 == dist2) {
                    shortest[id] = Math.min(shortest[id], stack.peek().id);
                } else if(dist1 > dist2) {
                    shortest[id] = stack.peek().id;
                }
            }
        }

        if (!stack.isEmpty()) {
            shortest[id] = Math.min(shortest[id], stack.peek().id);
        }

        stack.push(new Node(id, height[id]));
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        boolean negative = n == 13;
        if (negative) {
            n = 0;
        }

        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return negative ? -n : n;
    }

    private static class Node {

        int id, h;

        public Node(int id, int h) {
            this.id = id;
            this.h = h;
        }
    }
}