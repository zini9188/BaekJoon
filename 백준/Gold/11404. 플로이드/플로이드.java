import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[][] cities = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                cities[i][j] = i == j ? 0 : 987654321;
            }
        }

        StringTokenizer tokenizer;
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            cities[a][b] = Math.min(cities[a][b], c);
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    cities[i][j] = Math.min(cities[i][j], cities[i][k] + cities[k][j]);
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int result = cities[i][j] == 987654321 ? 0 : cities[i][j];
                builder.append(result).append(" ");
            }
            builder.append("\n");
        }

        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}