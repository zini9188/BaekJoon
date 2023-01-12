import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        long a = Integer.parseInt(tokenizer.nextToken());
        long b = Integer.parseInt(tokenizer.nextToken());
        int cnt = 1;
        while (b != a) {
            if (b < a) {
                cnt = -1;
                break;
            }
            if (b % 10 == 1) b /= 10;
            else if(b % 2 == 0) b /= 2;
            else {
                cnt = -1;
                break;
            }
            cnt++;
        }
        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }
}