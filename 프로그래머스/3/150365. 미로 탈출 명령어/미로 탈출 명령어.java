import java.util.*;
class Solution {
    
    static int K;
    static String answer ="";
    static int r, c,n,m;
    
    // 사전순 . => d l r u 순으로 움직인다. 
    static int[] dx = {1, 0, 0, -1}, dy = {0, -1, 1, 0};
    static char[] d = {'d', 'l', 'r', 'u'};
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.K = k;
        this.r = r;
        this.c = c;
        this.n = n;
        this.m = m;
        dfs(x,y,0,"");
        
        return answer.equals("") ? "impossible" : answer;
    }
    
    public static boolean outRange(int x, int y) {
        return x <= 0 || y <= 0 || x > n || y > m;
    }
    
    // (x, y), w: 거리, 
    public static boolean dfs(int x, int y, int w, String dir) {        
        int dd = Math.abs(x - r) + Math.abs(y - c);
        if(dd % 2 == 0 && (K - w) % 2 == 1) {
            return false;
        }
        if(dd % 2 == 1 && (K - w) % 2 == 0) {
            return false;
        }
        
        if(w == K) {
            if(x == r && y == c) {
                answer = dir;
                return true;
            }
            return false;
        }
        
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            int ld = Math.abs(nx - r) + Math.abs(ny - c);
            if(outRange(nx, ny) || ld > K - w) {
                continue;
            }
            if(dfs(nx, ny, w + 1, dir + d[i])) {
                return true;
            }            
        }
        return false;
    }
}