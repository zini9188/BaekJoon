import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] favorite = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                favorite[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = 0;
        for (int i = 0; i < M - 2; i++) {
            for (int j = i + 1; j < M - 1; j++) {
                for (int k = j + 1; k < M; k++) {
                    if (i == j || j == k) {
                        continue;
                    }

                    int sum = 0;
                    for (int l = 0; l < N; l++) {
                        sum += Math.max(Math.max(favorite[l][i], favorite[l][j]), favorite[l][k]);
                    }

                    max = Math.max(sum, max);
                }
            }
        }

        sb.append(max);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}