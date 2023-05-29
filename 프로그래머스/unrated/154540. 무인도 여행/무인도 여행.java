import java.util.*;

class Solution {
    
    private static int row;
    private static int col;
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1}; 
    private static boolean[][] visited;
    
    private static class Point {
        int x,y;
        
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    public int[] solution(String[] maps) {
        row = maps.length;
        col = maps[0].length();
        visited  = new boolean[row][col];
        char[][] mapsToArray = Arrays.stream(maps).map(String::toCharArray).toArray(char[][]::new);
        ArrayList<Integer> answer = new ArrayList<>();
        
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(!visited[i][j] && mapsToArray[i][j] != 'X'){
                    int days = bfs(mapsToArray, i, j);
                    answer.add(days);
                }                
            }
        }
        
        answer.sort((o1, o2) -> o1 - o2);        
        return answer.isEmpty() ? new int[]{-1} : answer.stream().mapToInt(o -> o).toArray();
    }
    
    private int bfs(char[][] maps, int sx, int sy){
        Queue<Point> points = new LinkedList<>();
        points.add(new Point(sx, sy));
        visited[sx][sy] = true;        
        int food = 0;
        
        while(!points.isEmpty()){
            Point point = points.poll();
            int x = point.x;
            int y = point.y;
            food += maps[x][y] - '0';
            
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(isInRange(nx, ny) && !visited[nx][ny] && maps[nx][ny] != 'X'){
                    visited[nx][ny] = true;
                    points.add(new Point(nx, ny));
                }
            }
        }        
        return food;
    }
    
    private boolean isInRange(int x, int y){
        return x >= 0 && y >= 0 && x < row && y < col;
    }
}