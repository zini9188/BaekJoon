import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {Reader r = new Reader();
        int N = r.nextInt();
        int L = r.nextInt();

        Deque<Node> deque = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            int a =  r.nextInt();
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

    private static class Reader {
        private final int BUFFER_SIZE = 1 << 16;
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            din.close();
        }
    }
}