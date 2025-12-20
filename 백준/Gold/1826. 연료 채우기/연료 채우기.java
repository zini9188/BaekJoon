import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = readInt();

        Queue<int[]> supplier = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        for (int i = 0; i < N; i++) {
            int a = readInt();
            int b = readInt();
            supplier.add(new int[]{a, b});
        }

        int L = readInt();
        int P = readInt();
        supplier.add(new int[]{L, 0});

        Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);

        int stop = 0;
        int currentPosition = P;
        while (currentPosition < L) {
            while (!supplier.isEmpty() && supplier.peek()[0] <= currentPosition) {
                queue.add(supplier.poll()[1]);
            }

            if(queue.isEmpty()) {
                break;
            }

            stop++;
            currentPosition += queue.poll();
        }


        if (currentPosition >= L) {
            sb.append(stop).append("\n");
        } else {
            sb.append(-1).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }


    private static int readInt() throws IOException {
        int c, n = System.in.read() & 15;
        boolean negative = n == 13;
        if (negative) {
            n = 0;
        }

        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        if (c == 13) {
            System.in.read();
        }

        return negative ? -n : n;
    }
}