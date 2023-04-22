import java.util.*;

class Solution {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Map<Character, Integer> map;
    public int[] solution(String[] park, String[] routes) {
        int[] pos = new int[2];
        for(int i = 0; i < park.length; i++){
            for(int j = 0; j < park[i].length(); j++){
                if(park[i].charAt(j) == 'S'){
                    pos[0] = i;
                    pos[1] = j;
                    break;
                }
            }
        }
        
        map = new HashMap<>();
        map.put('N', 0);
        map.put('S', 1);
        map.put('W', 2);
        map.put('E', 3);        
        
        for(String route : routes){
            char direction = route.charAt(0);
            char distance = route.charAt(2);
            
            pos = move(park, direction, distance, pos);
        }        
        return pos;
    }
    
    public int[] move(String[] park, char dir, char dist, int[] pos){
        int x = pos[0];
        int y = pos[1];
        for(int i = 0; i < dist - '0'; i++){
            int nx = x + dx[map.get(dir)];
            int ny = y + dy[map.get(dir)];
            if(!isInRange(nx, ny, park.length, park[0].length()) || park[nx].charAt(ny) == 'X'){            
                return pos;
            }
            x = nx;
            y = ny;
        }
        return new int[]{x, y};
    }
    
    private boolean isInRange(int x, int y, int n, int m){
        return x >= 0 && y >= 0 && x < n && y < m;
    }
}