import java.util.*;
class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int)(right - left) + 1];
        
        ArrayList<Integer> arrays = new ArrayList<>();
        
        int index = 0;
        for(long i = left; i <= right; i++){
            long row = i / n;
            long col = i % n;
            arrays.add((int)Math.max(row, col) + 1);
            answer[index] = arrays.get(index++);
        }
        
        return answer;
    }
}