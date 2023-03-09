import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] results = new int[N];
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
            for (int j = 0; j < N; j++) {
                if (results[j] == 0) {
                    if (arr[i] == 0) {
                        results[j] = i + 1;
                        break;
                    }
                    arr[i]--;
                }
            }
        }
        
        StringBuilder builder = new StringBuilder();
        for (int result : results) {
            builder.append(result).append(" ");
        }        
        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}