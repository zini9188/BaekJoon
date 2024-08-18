import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int[] arr = new int[5];
        for (int i = 0; i < 5; i++) {
            arr[i] = read();
        }

        for (int i = 1; i < 100 * 100 * 100; i++) {
            int cnt = 0;
            for (int j = 0; j < 5; j++) {
                if (i % arr[j] == 0) {
                    cnt++;
                }
            }

            if(cnt >= 3){
                sb.append(i);
                break;
            }
        }

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