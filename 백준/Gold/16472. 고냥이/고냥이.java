import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static String lang;
    private static int[] alpha;
    private static int N, ans;

    public static void main(String[] args) throws IOException {
        input();
        solution();
        output();
    }

    private static void solution() {
        int left = 0, right = 0, cnt = 0, len = lang.length();

        while (left <= right && right < len) {
            if (cnt <= N) {
                if (alpha[lang.charAt(right++) - 'a']++ == 0) {
                    cnt++;
                }

                if (cnt <= N) {
                    ans = Math.max(right - left, ans);
                }
            } else {
                if (--alpha[lang.charAt(left++) - 'a'] == 0) {
                    cnt--;
                }
            }
        }
    }

    private static void output() throws IOException {
        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        lang = br.readLine();

        alpha = new int[27];
    }
}