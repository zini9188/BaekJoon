class Solution {
    public int solution(int a, int b, int n) {
        int answer = 0;
        while(n >= a){
            int give = n / a;
            int receive = give * b;
            answer += receive;
            n = n - (give * a) + receive;
        }
        return answer;
    }
}