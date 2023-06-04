class Solution {
    public int solution(int a, int b) {
        int answer = 0;
        int operate1 = Integer.parseInt(String.valueOf(a) + String.valueOf(b));
        int operate2 = 2 * a * b;
        
        return Math.max(operate1, operate2);
    }
}