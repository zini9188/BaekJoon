class Solution {
    public int solution(int n, int m, int[] section) {
        int maxPainted = 0;
        int answer = 0;
        
        for(int i = 0; i < section.length; i++){
            int target = section[i];
            if(maxPainted <= target){
                maxPainted = target + m;
                answer++;
            }
        }
        
        return answer;
    }
}