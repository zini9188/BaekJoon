import java.util.*;
class Solution {
    public String solution(String s, String skip, int index) {
        StringBuilder answer = new StringBuilder();
        
        Map<Character, Boolean> map = new HashMap<>();
        
        for(char a = 'a'; a <= 'z'; a++){
            map.put(a, false);
        }
        
        for(char c : skip.toCharArray()){
            map.put(c, true);
        }
        
        
        for(char c : s.toCharArray()){
            int count = 0;
            while(count < index){
                c = convert(c);         
                if(!map.get(c)){
                    count++;
                }       
            }
            answer.append(c);            
        }
        
        
        return answer.toString();
    }
    
    private char convert(char c){
        if(++c > 'z') return 'a';
        return c;
    }
}