class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 100001;
        
        int n = diffs.length;
        int left = 1, right = 100000;
        while(left <= right) {
            int level = (left + right) >> 1;
            
            long sum = 0;
            for(int i = 0; i < n; i++) {
                if(diffs[i] <= level) {
                    sum += times[i];
                } else {
                    int timePrev = i == 0 ? 0 : times[i - 1];      
                    int mul = diffs[i] - level;
                    sum += (long)((timePrev + times[i]) * mul + times[i]); 
                }
            }
            
            if(sum > limit) {
                left = level + 1;
            } else {
                right = level - 1;
                answer = Math.min(answer, level);
            }            
        }
        
        return answer;
    }
}