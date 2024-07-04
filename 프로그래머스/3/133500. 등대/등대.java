import java.util.*;

class Solution {
    
    private List<List<Integer>> graph;
    private int[][] dp;
    private boolean[] visited;
    
    public int solution(int n, int[][] lighthouse) {
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] lh : lighthouse) {
            graph.get(lh[0]).add(lh[1]);
            graph.get(lh[1]).add(lh[0]);
        }

        dp = new int[n + 1][2];
        visited = new boolean[n + 1];
        dfs(1);

        return Math.min(dp[1][0], dp[1][1]);
    }
    
    private void dfs(int cur) {
        visited[cur] = true;
        dp[cur][0] = 1;

        for (Integer child : graph.get(cur)) {
            if (visited[child]) {
                continue;
            }

            dfs(child);

            dp[cur][0] += Math.min(dp[child][0], dp[child][1]);
            dp[cur][1] += dp[child][0];
        }
    }
}