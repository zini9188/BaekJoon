import java.io.*;

public class Main {

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static int N;
    private static int[] fruits;

    public static void main(String[] args) throws IOException {
        input();
        int ans = solution();
        output(ans);
    }

    private static void output(int ans) throws IOException {
        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
    }

    private static int solution() {
        int ans = 0;
        int cnt = 0, left = 0, right = 0;
        int[] use = new int[10];
        while (left <= right && right < N) {
            if (cnt <= 2) {
                if (use[fruits[right++]]++ == 0) {
                    cnt++;
                }
                if (cnt <= 2) {
                    ans = Math.max(ans, right - left);
                }
            } else {
                if (--use[fruits[left++]] == 0) {
                    cnt--;
                }
            }
        }
        return ans;
    }

    private static void input() throws IOException {
        N = read();
        fruits = new int[N];
        for (int i = 0; i < N; i++) {
            fruits[i] = read();
        }
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        if (c == 13) {
            System.in.read();
        }
        return n;
    }
}