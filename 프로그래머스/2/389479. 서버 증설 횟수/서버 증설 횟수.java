import java.util.*;
class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        
        int increased = 0;
        Queue<int[]> st = new ArrayDeque<>();
        for(int i = 0; i < players.length; i++) { 
            if(players[i] < m) {
                continue;
            }
            
            while(!st.isEmpty() && st.peek()[0] <= i) {
                increased -= st.peek()[1];
                st.poll();
            }
            // 필요한 서버대수.
            int tmp = players[i] / m;
            if(tmp > increased) {
                tmp -= increased;            
                answer += tmp;
                // k시간 후 서버 종료.
                st.add(new int[]{i + k, tmp});
                increased += tmp;                
            }
        }
        
        return answer;
    }
}