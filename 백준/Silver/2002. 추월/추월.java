import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = read();

        Queue<String> in = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            in.add(br.readLine());
        }
        Queue<String> out = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            out.add(br.readLine());
        }

        int answer = 0;
        while (!out.isEmpty()) {
            String outCar = out.poll();
            if (!in.peek().equals(outCar)) {
                answer++;
                in.remove(outCar);
            } else {
                in.poll();
            }
        }

        sb.append(answer);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}