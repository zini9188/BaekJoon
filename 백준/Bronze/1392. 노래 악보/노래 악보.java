import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int[] arr = new int[N * 100 + 1];

        int idx = 0;
        for (int i = 0; i < N; i++) {
            int time = Integer.parseInt(br.readLine());
            for (int j = 0; j < time; j++) {
                arr[idx++] = i + 1;
            }
        }

        for (int i = 0; i < Q; i++) {
            sb.append(arr[Integer.parseInt(br.readLine())]).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}