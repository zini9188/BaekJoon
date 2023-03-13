import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        var kind = new int[]{300, 60, 10};
        var result = new int[]{0, 0, 0};
        int T = Integer.parseInt(br.readLine());

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < kind.length; i++) {
            if (T % kind[i] >= 0) {
                result[i] = T / kind[i];
                builder.append(result[i]).append(" ");
                T %= kind[i];
            }
        }
        if (T != 0) {
            builder = new StringBuilder();
            builder.append("-1");
        }

        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}