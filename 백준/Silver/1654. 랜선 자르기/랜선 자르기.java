import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(tokenizer.nextToken());
        int N = Integer.parseInt(tokenizer.nextToken());        
        int[] cables = new int[K];
        
        for (int i = 0; i < K; i++) 
            cables[i] = Integer.parseInt(br.readLine());        
        Arrays.sort(cables);
        
        long left = 1, right = cables[K - 1], mid, res = 0;
        while (left <= right) {
            long sum = 0;
            mid = (left + right) / 2;
            for (int cable : cables)
                sum += cable / mid;
            if (sum >= N) {
                res = Math.max(res, mid);
                left = mid + 1;
            } else right = mid - 1;
        }

        bw.write(String.valueOf(res));
        bw.flush();
        bw.close();
        br.close();
    }
}
