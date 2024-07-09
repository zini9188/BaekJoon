import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static char[] korea = {'K', 'O', 'R', 'E', 'A'};

    public static void main(String[] args) throws IOException {
        String input = br.readLine();

        int idx = 0;
        int cnt = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == korea[idx]) {
                idx++;
                idx %= 5;
                cnt++;
            }
        }

        sb.append(cnt);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}