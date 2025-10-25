import java.util.*;
class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int answer = 0;
        int maxAlp = 0;
        int maxCop = 0;
        for(int[] pb : problems) {
            maxAlp = Math.max(pb[0], maxAlp);
            maxCop = Math.max(pb[1], maxCop);            
        }
        
        maxAlp = Math.max(alp, maxAlp);
        maxCop = Math.max(cop, maxCop);
        if(alp >= maxAlp && cop >= maxCop) {
            return 0;
        }        
        
        int[][] dp = new int[maxAlp + 2][maxCop + 2];
        for(int i = 0; i <= maxAlp; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE - 1);
        }        
        dp[alp][cop] = 0;
        
        for(int i = alp; i <= maxAlp; i++) {
            for(int j = cop; j <= maxCop; j++) {
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                
                for(int[] pb : problems) {
                    if(pb[0] <= i && pb[1] <= j) {
                        int nAlp = Math.min(maxAlp, i + pb[2]); 
                        int nCop = Math.min(maxCop, j + pb[3]);
                        dp[nAlp][nCop] = Math.min(dp[nAlp][nCop], dp[i][j] + pb[4]);
                    }
                }
            }
        }       
        
        return dp[maxAlp][maxCop];
    }
}