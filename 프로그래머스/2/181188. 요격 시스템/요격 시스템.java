import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 1;
        
        Arrays.sort(targets, (o1, o2) -> {
            if(o1[1] == o2[1]){
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });
        
        // 미사일
        int len = targets.length;
        int right = targets[0][1];
        
        for(int[] target: targets){
            if(target[0] >= right){
                right = target[1];
                answer++;
            }
        }
        
        return answer;
    }
}