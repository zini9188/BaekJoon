import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        int need = 10;
        int days = discount.length;
        
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < want.length; i++){
            map.put(want[i], number[i]);
        }
        
        for(int i = 0; i <= discount.length - 10; i++){
            // 10일 연속으로 상품을 살 수 있는지 확인
            Map<String, Integer> basket = new HashMap<>();
            basket.putAll(map);
            int count = 10;
            for(int j = i; j < i + 10; j++){
                if(!basket.containsKey(discount[j])) break;
                int left = basket.get(discount[j]);
                if(left - 1 < 0) break;                
                basket.put(discount[j], left - 1);
                count--;
            }
            if(count == 0) answer++;
        }
        
        return answer;
    }
}