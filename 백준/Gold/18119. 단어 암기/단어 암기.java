import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // arr[0] = init word bitmap
        // arr[1] = change word bitmap
        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            char[] word = br.readLine().toCharArray();
            int n = 0;
            for (char c : word) {
                n |= 1 << (c - 'a');
            }
            arr[i][0] = arr[i][1] = n;
        }

        // init memory (a ~ z)
        int memory = 0;
        for (char a = 'a'; a <= 'z'; a++) {
            memory |= 1 << (a - 'a');
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int o = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);

            int cnt = 0;
            // forgot
            if (o == 1) {
                memory ^= 1 << (c - 'a');
                for (int j = 0; j < N; j++) {
                    arr[j][1] ^= 1 << (c - 'a');
                    if ((arr[j][0] & arr[j][1]) == arr[j][0]) {
                        cnt++;
                    }
                }
            } else {
                // remember
                memory |= 1 << (c - 'a');
                for (int j = 0; j < N; j++) {
                    arr[j][1] |= 1 << (c - 'a');
                    if ((arr[j][0] & arr[j][1]) == arr[j][0]) {
                        cnt++;
                    }
                }
            }

            sb.append(cnt).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}