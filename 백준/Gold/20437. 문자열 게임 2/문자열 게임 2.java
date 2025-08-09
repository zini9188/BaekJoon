import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String W = br.readLine();
            int K = Integer.parseInt(br.readLine());

            if (K == 1) {
                sb.append("1 1\n");
                continue;
            }

            List<Integer>[] positions = new ArrayList[26];
            for (int i = 0; i < 26; i++) {
                positions[i] = new ArrayList<>();
            }

            for (int i = 0; i < W.length(); i++) {
                positions[W.charAt(i) - 'a'].add(i);
            }

            int minLength = Integer.MAX_VALUE;
            int maxLength = -1;

            for (int i = 0; i < 26; i++) {
                if (positions[i].size() < K) {
                    continue;
                }

                for (int j = 0; j <= positions[i].size() - K; j++) {
                    int start = positions[i].get(j);
                    int end = positions[i].get(j + K - 1);
                    int length = end - start + 1;

                    minLength = Math.min(minLength, length);
                    maxLength = Math.max(maxLength, length);
                }
            }

            if (maxLength == -1) {
                sb.append("-1\n");
            } else {
                sb.append(minLength).append(" ").append(maxLength).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}