import java.util.*;
class Solution {
    public int solution(int n, int w, int num) {
        int answer = 0;
        
        // n 상자 수 
        // w 가로수 
        // num 찾고자 하는 수 
        
        int[][] arr = new int [(n / w) + 1][w];
        
        int x = 0;
        int y = 0;
        int tx = 0;
        int ty = 0;
        boolean flag = false;
        for(int i = 0; i < n; i++) {
            arr[x][y] = i + 1;
            
            if(i + 1 == num) {
                tx = x;
                ty = y;
            }
            
            if(flag) {
                y--;
            } else {
                y++;
            }
            
            if(y == w) {
                x++;
                y = w - 1;
                flag = !flag;
            } else if(y < 0) {
                flag = !flag;
                x++;
                y = 0;
            }
        }
        
        while(tx < arr.length) {
            if(arr[tx][ty] == 0) {
                break;
            }
            tx++;
            answer++;
        }
        
        return answer;        
    }
}