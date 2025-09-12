import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {
        String answer = "";
        
        List<Long> converted = new ArrayList<>();
        for(String ban : bans) {
            converted.add(convert(ban));
        }
        Collections.sort(converted);
        
        for(long convert : converted) {
            if(n >= convert) {
                n++;
            } else {
                break;
            }
        }
        
        return convertToString(n);
    }
    
    private long convert(String str){
        long num = 0;
        for(int i = 0; i < str.length(); i++) {
            int v = str.charAt(i) - 'a' + 1;
            num = num * 26 + v;
        }
        return num;
    }
    
    private String convertToString(long num) {
        long n = num;
        StringBuilder res = new StringBuilder();
        
        while(n-- > 0) {
            char c = (char)('a' + (int)(n % 26));
            res.append(c);
            n /= 26;
        }
        return res.reverse().toString();
    }
}