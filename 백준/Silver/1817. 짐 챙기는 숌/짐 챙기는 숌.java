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
        int M = Integer.parseInt(st.nextToken());
        int answer = solution(N, M);
        sb.append(answer);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int solution(int N, int M) throws IOException {
        if (N == 0) {
            return 0;
        }

        int ans = 1;
        int box = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int weight = Integer.parseInt(st.nextToken());

            if (box + weight > M) {
                box = weight;
                ans++;
            } else {
                box += weight;
            }
        }

        return ans;
    }
}