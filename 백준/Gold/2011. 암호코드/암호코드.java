import java.io.*;
import java.util.Arrays;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        String cipher = br.readLine();
        final int MOD = 1_000_000;
        long[] dp = new long[cipher.length() + 1];
        dp[cipher.length()] = 1;
        for (int length = cipher.length() - 1; length >= 0; length--) {
            if (isOverTwoDigit(cipher, length)) {
                dp[length] = (dp[length + 1] + dp[length + 2]) % MOD;
            } else if (cipher.charAt(length) == '0') {
                dp[length] = 0;
            } else {
                dp[length] = dp[length + 1] % MOD;
            }
        }

        sb.append(dp[0]);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static boolean isOverTwoDigit(String str, int index) {
        if (index + 1 >= str.length() || str.charAt(index) == '0') {
            return false;
        }
        int num = Integer.parseInt(str.substring(index, index + 2));
        return num >= 10 && num <= 26;
    }
}