import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int L = Integer.parseInt(tokenizer.nextToken());

        Deque<Node> deque = new ArrayDeque<>();
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(tokenizer.nextToken());
            while (!deque.isEmpty() && deque.getLast().val > a) {
                deque.removeLast();
            }
            deque.addLast(new Node(i, a));
            while (!deque.isEmpty() && deque.getFirst().idx <= i - L) {
                deque.removeFirst();
            }
            sb.append(deque.getFirst().val).append(" ");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Node {
        int idx;
        int val;

        public Node(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }
}