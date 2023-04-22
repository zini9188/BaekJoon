import java.util.*;

class Solution {
    static Map<Character, int[]> map;
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
        map.put('N', new int[]{-1, 0});
        map.put('S', new int[]{1, 0});
        map.put('W', new int[]{0, -1});
        map.put('E', new int[]{0, 1});        
        
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
            int[] np = map.get(dir);
            int nx = x + np[0];
            int ny = y + np[1];
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