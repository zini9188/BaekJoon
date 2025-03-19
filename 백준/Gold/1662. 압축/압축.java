import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static final int MAX = 51;
    private static int[] len = new int[MAX];
    private static int[] K = new int[MAX];

    public static void main(String[] args) throws IOException {
        String S = br.readLine();

        int i, cur = 0, num = 0;
        int size = S.length();

        for (i = 0; i < size; i++) {
            char ch = S.charAt(i);

            if (ch == '(') {
                len[cur]--;
                cur++;
                K[cur] = num;
            }
            else if (ch == ')') {
                int temp = len[cur] * K[cur];
                len[cur] = 0;
                cur--;
                len[cur] += temp;
            }
            else {
                len[cur]++;
                num = ch - '0';
            }
        }

        sb.append(len[0]);
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