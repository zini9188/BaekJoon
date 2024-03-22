import java.util.*;
class Solution {
    
    static char[][] maze;
    static Point entrance;        
    static int M, N;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, 1, -1};
    
    public int solution(String[] maps) {
        int answer = 0;
        M = maps.length;
        N = maps[0].length();
        maze = new char[M][N];
        for(int i = 0; i < maps.length; i++){
            maze[i] = maps[i].toCharArray();
            
            for(int j = 0; j < N; j++){
                if(maps[i].charAt(j) == 'S'){
                  entrance = new Point(i, j, 0);  
                }
            }       
        }
        
        answer = bfs(entrance.x, entrance.y);    
        
        return answer;      
    }
    
    public int bfs(int x, int y){
        int[][][] dist = new int[2][M][N];
        for(int[][] dd : dist){
            for(int [] d : dd){
                Arrays.fill(d, 9999);
            }
        }
            
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(x, y, 0));
        dist[0][x][y] = 0;
        while(!q.isEmpty()){
            Point p = q.poll();
                
            for(int i = 0; i < 4; i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                int nl = p.l;
                if(outRange(nx, ny) || maze[nx][ny] == 'X'){
                    continue;
                }
                
                if(maze[nx][ny] == 'L'){
                    nl = 1;
                }
                
                if(nl == 1 && maze[nx][ny] == 'E'){
                    return dist[nl][p.x][p.y] + 1;
                }
                
                if(dist[nl][nx][ny] > dist[p.l][p.x][p.y] + 1){
                    dist[nl][nx][ny] = dist[p.l][p.x][p.y] + 1;
                    q.add(new Point(nx, ny, nl));
                }
            }
        }        
        
        return -1;
    }
    
    static boolean outRange(int x, int y){
        return x < 0 || y < 0 || x >= M || y >= N;
    }
    
    
    static class Point{
        int x, y, l;
        
        Point(int a, int b, int ll){
            x = a;
            y = b;
            l = ll;
        }
    }    
}
