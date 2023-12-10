import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        tokenizer = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int top = 1;
        int bot = 1;
        int ans = 1;
        for (int i = 1; i < N; i++) {
            top = arr[i] >= arr[i - 1] ? ++top : 1;
            bot = arr[i] <= arr[i - 1] ? ++bot : 1;
            ans = Math.max(ans, Math.max(top, bot));
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}