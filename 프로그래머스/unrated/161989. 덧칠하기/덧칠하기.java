class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        boolean[] wall = new boolean[n + 1];
        for(int i = 0; i < section.length; i++){
            wall[section[i]] = true;
        }
        
        for(int i = 0; i < section.length; i++){
            int target = section[i];
            if(wall[target]){ 
                answer++;
                for(int j = target; j < target + m && j <= n; j++){
                    wall[j] = false;
                }
            }
        }
        
        return answer;
    }
}