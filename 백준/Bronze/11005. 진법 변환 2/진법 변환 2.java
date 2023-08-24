import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int B = Integer.parseInt(tokenizer.nextToken());
        while (N > 0) {
            int digit = N % B;
            N /= B;
            if(digit - 10 >= 0){
                sb.append((char) ((digit - 10) + 'A'));
            }else{
                sb.append(digit);
            }
        }

        bw.write(sb.reverse().toString());
        bw.close();
        br.close();
    }
}
