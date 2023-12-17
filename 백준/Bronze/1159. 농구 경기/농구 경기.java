import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] cnt = new int[27];
        for (int i = 0; i < N; i++) {
            String name = br.readLine();
            cnt[name.charAt(0) - 'a']++;
        }

        for (int i = 0; i < 27; i++) {
            if (cnt[i] >= 5) {
                sb.append((char)(i + 'a'));
            }
        }

        if(sb.length() == 0){
            sb.append("PREDAJA");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}