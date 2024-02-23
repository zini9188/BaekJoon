import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();
        int M = reader.nextInt();
        int N = reader.nextInt();

        int[] arr = new int[2 * M - 1];
        for (int i = 0; i < N; i++) {
            int zero = reader.nextInt();
            int one = reader.nextInt();
            int two = reader.nextInt();

            int idx = zero;
            int sum = 1;
            if (one > 0) {
                arr[idx]++;
                idx += one;
                sum--;
            }

            if (two > 0) {
                arr[idx] += 1 + sum;
            }
        }

        arr[0]++;
        for (int i = 1; i < 2 * M - 1; i++) {
            arr[i] += arr[i - 1];
        }

        StringBuilder repeat = new StringBuilder();
        for (int j = M; j < 2 * M - 1; j++) {
            repeat.append(arr[j]).append(" ");
        }
        for (int i = M - 1; i >= 0; i--) {
            sb.append(arr[i]).append(" ").append(repeat).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
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
    }
}