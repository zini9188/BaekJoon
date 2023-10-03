import java.io.*;
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        boolean[] prime = new boolean[1000001];
        prime[0] = prime[1] = true;
        for (int i = 2; i * i <= 1000000; i++) {
            if (!prime[i]) {
                for (int j = i * i; j <= 1000000; j += i) {
                    prime[j] = true;
                }
            }
        }

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            
            int cnt = 0;
            for (int j = 2; j <= N / 2; j++) {
                if (!prime[j] && !prime[N - j]) {
                    cnt++;
                }
            }
            sb.append(cnt).append("\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}