import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.min;

public class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int k = Integer.parseInt(tokenizer.nextToken());
        int[] moneys = new int[n];
        int[] dp = new int[k + 1];

        for (int i = 0; i < n; i++) 
            moneys[i] = Integer.parseInt(br.readLine());
        
        for (int i = 1; i <= k; i++) dp[i] = 987654321;
        
        for (int money : moneys) {
            for (int i = money; i <= k; i++) {
                    dp[i] = min(dp[i], dp[i - money] + 1);
            }
        }
        bw.write(String.valueOf(dp[k] == 987654321 ? -1 : dp[k]));
        bw.flush();
        bw.close();
        br.close();
    }
}