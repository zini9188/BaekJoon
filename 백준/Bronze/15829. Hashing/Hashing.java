import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int L = Integer.parseInt(br.readLine());
        String str = br.readLine();
        int R = 31;
        int M = 1234567891;
        long res = 0;
        long r = 1;
        for (int i = 0; i < L; i++) {
            res += ((str.charAt(i) - 'a' + 1) * r) % M;
            r = (r * R) % M;
        }
        bw.write(String.valueOf(res));
        bw.flush();
        br.close();
        bw.close();
    }
}