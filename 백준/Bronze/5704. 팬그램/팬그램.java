import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        String input;
        while (!(input = br.readLine()).equals("*")) {
            boolean[] alpha = new boolean[27];
            int cnt = 0;
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                if (c == ' ') {
                    continue;
                }

                int id = c - 'a';
                if (!alpha[id]) {
                    alpha[id] = true;
                    cnt++;
                }
            }

            if (cnt == 26) {
                sb.append("Y\n");
            } else {
                sb.append("N\n");
            }
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}