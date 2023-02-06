import java.io.*;

public class Main {
    static int N;
    static int M;
    static String S;
    static int result = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        S = br.readLine();
        StringBuilder ioi = new StringBuilder("I");
        ioi.append("OI".repeat(Math.max(0, N)));

        for (int i = 0; i < M - ioi.length(); i++) {
            if (S.startsWith(ioi.toString(), i)) {
                result++;
            }
        }

        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }
}