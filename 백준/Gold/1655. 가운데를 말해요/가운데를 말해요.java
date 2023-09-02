import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> lower = new PriorityQueue<>((o1, o2) -> o2 - o1);
        PriorityQueue<Integer> higher = new PriorityQueue<>();
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());

            if (lower.isEmpty()) {
                lower.add(n);
            } else if (higher.isEmpty()) {
                if (lower.peek() > n) {
                    higher.add(lower.poll());
                    lower.add(n);
                } else {
                    higher.add(n);
                }
            } else {
                if (lower.size() > higher.size()) {
                    if (lower.peek() <= n) {
                        higher.add(n);
                    } else {
                        higher.add(lower.poll());
                        lower.add(n);
                    }
                } else if (lower.size() == higher.size()) {
                    if (higher.peek() <= n) {
                        higher.add(n);
                    } else {
                        lower.add(n);
                    }
                } else {
                    if (higher.peek() >= n) {
                        lower.add(n);
                    } else {
                        higher.add(n);
                        lower.add(higher.poll());
                    }
                }
            }

            if (lower.size() >= higher.size()) {
                sb.append(lower.peek()).append("\n");
            } else {
                sb.append(higher.peek()).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}