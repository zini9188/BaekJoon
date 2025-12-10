import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        String str1 = br.readLine();
        String str2 = br.readLine();

        Map<Character, Integer> map = new HashMap<>();

        int[] use = new int[52];
        for (char c : str1.toCharArray()) {
            use[searchIndex(c)]++;
        }

        int[] alphabets = new int[52];
        int cnt = 0;
        for (int left = 0, right = 0; left <= right && right < s; ) {
            if (right - left < w) {
                alphabets[searchIndex(str2.charAt(right))]++;
                right++;
            } else {
                alphabets[searchIndex(str2.charAt(right))]++;
                alphabets[searchIndex(str2.charAt(left))]--;
                left++;
                right++;
            }

            if (check(use, alphabets)) {
                cnt++;
            }
        }

        sb.append(cnt);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int searchIndex(char c) {
        return Character.isUpperCase(c) ? c - 'A' + 26 : c - 'a';
    }

    private static boolean check(int[] a, int[] b) {
        return Arrays.equals(a, b);
    }
}