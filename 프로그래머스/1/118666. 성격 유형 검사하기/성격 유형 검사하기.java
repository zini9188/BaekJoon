import java.util.*;
class Solution {
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        // survey : 성격 유형
        // choices : 검사자가 선택한 i + 1 질문의 선택지
        int[] score = new int[] {3,2,1,0,1,2,3};
        
        int n = survey.length;
        
        char[][] seq = new char[][] { {'R', 'T'}, {'C', 'F'}, {'J', 'M'}, {'A', 'N'}} ;
        Map<Character, Integer> map = new HashMap<>();
        for(char[] seqseq : seq) {
            for(char se : seqseq) {
                map.put(se, 0);
            }
        }
        
        for(int i = 0; i < n; i++) {
            char t1 = survey[i].charAt(0);
            char t2 = survey[i].charAt(1);
            int s = score[choices[i] - 1];
            
            if(s == 0) {
                continue;
            } else if((choices[i] -  1) < 4) {
                map.put(t1, map.get(t1) + s);
            } else {
                map.put(t2, map.get(t2) + s);                
            }
        }
    
        for(char[] seqseq : seq) {
            int a = map.get(seqseq[0]);
            int b = map.get(seqseq[1]);
            if(a >= b) { 
                answer += seqseq[0];
            } else if(a < b) {
                answer += seqseq[1];
            }
        }
        
        return answer;
    }
}