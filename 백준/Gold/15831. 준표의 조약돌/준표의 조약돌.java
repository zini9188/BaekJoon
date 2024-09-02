import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = read();
        int B = read();
        int W = read();

        char[] rock = br.readLine().toCharArray();

        int left = 0, right = 0, b = 0, w = 0, len = 0, ans = 0;
        while (right < N) {
            if (b > B) {
                if (rock[left] == 'W') {
                    w--;
                } else {
                    b--;
                }

                len--;
                left++;
            } else {
                if (rock[right] == 'W') {
                    w++;
                } else {
                    b++;
                }

                len++;
                right++;
            }

            if (b <= B && w >= W) {
                ans = Math.max(ans, len);
            }
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
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
}