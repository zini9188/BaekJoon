import java.util.*;

class Solution {
    
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    
    public int solution(int[][] land) {
        int answer = 0;        
        // 위치 별 석유 양을 저장할 배열
        List<Integer> amount = new ArrayList<>(List.of(0, 0));
        int n = land.length;
        int m = land[0].length;
               
        // 맵에 있는 석유를 모두 찾아서 개수를 표시한다        
        boolean[][] visited = new boolean[n][m];        
        int idx = 2;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                // 석유가 있으면 bfs
                if(land[i][j] == 1 && !visited[i][j]){
                    Queue<int[]> q = new ArrayDeque<>();   
                    q.add(new int[]{i, j});
                    visited[i][j] = true;
                    land[i][j] = idx;
                    int am = 1;
                    while(!q.isEmpty()){
                        int[] cur = q.poll();
                        
                        for(int dir = 0; dir < 4; dir++){
                            int nx = cur[0] + dx[dir];
                            int ny = cur[1] + dy[dir];
                            
                            if(outRange(nx, ny, n, m) || visited[nx][ny] || land[nx][ny] == 0){
                                continue;
                            }
                            
                            visited[nx][ny] = true;
                            q.add(new int[]{nx, ny});
                            am++;
                            land[nx][ny] = idx;
                        }
                    }
                    // 석유 있으면 해당 idx에 넣기
                    if(am > 0){
                        amount.add(am);
                    }
                    idx++;
                }                
            }
        }
        
        for(int i = 0; i < m; i++){
            boolean[] visitedIdx = new boolean[amount.size()];
            int cnt = 0;
            for(int j = 0; j < n; j++){
                idx = land[j][i];
                if(!visitedIdx[idx]){
                    cnt += amount.get(idx);
                    visitedIdx[idx] = true;
                }
            }
            
            if(answer < cnt){
                answer = cnt;
            }
        }
        
        
        return answer;
    }
    
    private static boolean outRange(int x, int y, int n, int m){
        return x < 0 || y < 0 || x >= n || y >= m;
    }
}