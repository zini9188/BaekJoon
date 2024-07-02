import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            int left = 0, right = str.length() - 1;
            int n = palindrome(left, right, 0, str);

            sb.append(n).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int palindrome(int left, int right, int use, String str) {
        if (use >= 2) {
            return 2;
        }

        while (left < right) {
            if (str.charAt(left) == str.charAt(right)) {
                left++;
                right--;
            } else {
                return Math.min(
                        palindrome(left + 1, right, use + 1, str),
                        palindrome(left, right - 1, use + 1, str)
                );
            }
        }

        return use;
    }
}