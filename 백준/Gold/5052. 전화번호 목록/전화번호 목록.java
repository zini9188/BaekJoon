import java.io.*;
import java.util.Arrays;

public class Main {
    static int T;
    static int N;
    static String[] tels;
    static StringBuilder builder;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());
        builder = new StringBuilder();
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            tels = new String[N];
            for (int i = 0; i < N; i++) {
                tels[i] = br.readLine();
            }
            Arrays.sort(tels);
            builder.append(solution()).append("\n");
        }
        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static String solution() {
        for(int i = 0; i < N - 1; i++){
            if(tels[i + 1].startsWith(tels[i])){
                return "NO";
            }
        }
        return "YES";
    }
}