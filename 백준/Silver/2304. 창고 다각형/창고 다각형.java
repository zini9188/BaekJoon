import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = read();

        int[] height = new int[1001];
        for (int i = 0; i < N; i++) {
            height[read()] = read();
        }

        int[] prefixMax = new int[1001];
        for (int i = 1; i <= 1000; i++) {
            prefixMax[i] = Math.max(prefixMax[i], height[i]);
            prefixMax[i] = Math.max(prefixMax[i], prefixMax[i - 1]);
        }

        int[] suffixMax = new int[1002];
        for (int i = 1000; i > 0; i--) {
            suffixMax[i] = Math.max(suffixMax[i], height[i]);
            suffixMax[i] = Math.max(suffixMax[i], suffixMax[i + 1]);
        }

        int ans = 0;
        for (int i = 1; i < 1001; i++) {
            ans += Math.min(prefixMax[i], suffixMax[i]);
        }

        sb.append(ans);
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