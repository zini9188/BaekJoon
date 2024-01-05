import java.util.*;

class Solution {
    public int N;
    public int[][] dp;
    public HashSet<Integer> topSet;

    public int solution(int n, int[] tops) {
        int answer = 0;

        N = 2 * n + 1;
        dp = new int[2][N];
        topSet = new HashSet<>();

        init(n, tops);

        for(int i = 2; i < N; i++){
            if(i % 2 == 0){
//                dp[1][i] = dp[1][i-2] + Math.max(dp[0][i-1], dp[1][i-1]);
//                dp[1][i] = dp[1][i-2] + Math.max(dp[0][i-1], dp[1][i-1]);
                if(topSet.contains(i-1)) {
                      dp[1][i] = dp[1][i-2] + dp[0][i-1];
                }
                else {
                   
                        dp[1][i] = dp[1][i-2] + dp[1][i-1];
                
                }
            }
            else{
//                dp[1][i] = dp[1][i-1] + Math.max(dp[0][i-2], dp[1][i-2]);
                if(topSet.contains(i-2)) {
                    dp[1][i] = dp[1][i-1] + dp[0][i-2];
              }
              else {
                 
                      dp[1][i] = dp[1][i-1] + dp[1][i-2];
              
              }
                if(topSet.contains(i)){
                    dp[0][i] = dp[1][i] + dp[1][i-1];
                }
            }
            dp[0][i] %= 10007;
            dp[1][i] %= 10007;
//            System.out.println(dp[0][i]);
//            System.out.println(dp[1][i]);
//            System.out.println("----");
        }

//         print();

        return dp[1][N-1] % 10007;
    }

    public void init(int n, int[] tops){
        for(int i = 0; i < n; i++){
            if(tops[i] != 0){
                topSet.add(2 * i + 1);
            }
        }

        for(int i = 0; i < 2; i++){
            Arrays.fill(dp[i], 0);
        }

        dp[1][0] = 1;
        dp[1][1] = 2;

        if(topSet.contains(1)){
            dp[0][1] = 3;
        }
    }

    public void print(){
        for(int i = 0; i < N; i++){
            System.out.print(dp[0][i] + "\t");
        }
        System.out.println();
        System.out.println();
        for(int i = 0; i < N; i++){
            System.out.print(dp[1][i] + "\t");
        }
        System.out.println();
    }
}
