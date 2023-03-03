import java.io.*;
import java.util.*;

public class Main {
    static class Pair {
        int abs, x;

        public Pair(int abs, int x) {
            this.abs = abs;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();
        Queue<Pair> queue = new PriorityQueue<>((o1, o2) -> o1.abs == o2.abs ? o1.x - o2.x : o1.abs - o2.abs);
        int N = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());
            if (x == 0) {
                if (queue.isEmpty()) {
                    builder.append("0").append("\n");
                }else{
                    builder.append(queue.poll().x).append("\n");
                }
            } else {
                queue.add(new Pair(Math.abs(x), x));
            }
        }
        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}