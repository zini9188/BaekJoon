import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        int mostMax = 0;
        int ans = 0;
        for (int i = 0; i < N; i++) {
            int[] arr = new int[5];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            int max = 0;
            for (int j = 0; j < 5; j++) {
                for (int k = j + 1; k < 5; k++) {
                    for (int l = k + 1; l < 5; l++) {
                        int sum = arr[j] + arr[k] + arr[l];
                        if (sum % 10 > max) {
                            max = sum % 10;
                        }
                    }
                }
            }

            if (max >= mostMax) {
                mostMax = max;
                ans = i + 1;
            }
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}