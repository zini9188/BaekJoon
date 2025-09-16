import java.util.*;
class Solution {
    
    static int N, M;
    static int[][] INFO;
    static int ANS = Integer.MAX_VALUE;
    static Set<List<Integer>> set;
    
    public int solution(int[][] info, int n, int m) {
        int answer = 0;
        INFO = info;
        N = n;
        M = m;
        set = new HashSet<>(); 
        
        dfs(0, 0, 0);
        
        return ANS == Integer.MAX_VALUE ? -1 : ANS;
    }
    
    public static void dfs(int idx, int n, int m) {
        if(idx > INFO.length || n >= ANS) {
            return;
        }
        
        if(idx == INFO.length) {
            ANS = Math.min(ANS, n);
            return;
        }
        
        List<Integer> data = List.of(idx, n, m);
        if(set.contains(data)) {
            return;
        }
        
        if(INFO[idx][0] + n < N){
            dfs(idx + 1, n + INFO[idx][0], m);                   
        }
        if(INFO[idx][1] + m < M) {
            dfs(idx + 1, n, m + INFO[idx][1]);
        }
        set.add(data);
    }
}