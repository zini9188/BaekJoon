import java.lang.*;
class Solution {
    public String solution(String str1, String str2) {
        StringBuilder answer = new StringBuilder();
        int len = str1.length();
        
        for(int i = 0; i < len; i++){
            answer.append(str1.charAt(i));
            answer.append(str2.charAt(i));
        }
        
        return answer.toString();
    }
}