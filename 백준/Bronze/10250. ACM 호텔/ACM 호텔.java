import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int T;
    static int H, W, N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer;
        StringBuilder builder = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            tokenizer = new StringTokenizer(br.readLine());
            H = Integer.parseInt(tokenizer.nextToken());
            W = Integer.parseInt(tokenizer.nextToken());
            N = Integer.parseInt(tokenizer.nextToken());

            if(N % H == 0){
                builder.append(H * 100 + N / H).append("\n");
            }else{
                builder.append((N % H) * 100 + N / H + 1).append("\n");
            }
        }
        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}