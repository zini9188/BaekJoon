class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        // t : 문자열
        // p : 문자열
        // t에서 p와 길이가 같은 부분 문자열 중, 이 부분 문자열이 나타내는 수가 p가 나타내는 수보다 작은 것이 나오는 횟수를 반환
        // p의 길이를 n으로 두고
        // t에서 n의 길이를 갖는 부분 문자열을 만들면서
        // p의 값보다 작으면 answer 값을 증가시키는 방법으로 진행해보겠습니다.
        int plen = p.length();
        int tlen = t.length();
        for(int i = 0; i <= tlen - plen; i++){
            String compare = t.substring(i, i + plen);
            if(compare.compareTo(p) <= 0){
                answer++;
            }
        }
        
        return answer;
    }
}