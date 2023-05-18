class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        // cards1 : 첫 번째 카드 뭉치
        // cards2 : 두 번째 카드 뭉치
        // goal : cards1, cards2로 만들 수 있는 단어?
        int left = 0, right = 0;
        
        for(int i = 0; i < goal.length; i++){
            String target = goal[i];
            
            if(left < cards1.length && cards1[left].equals(target)){
                left++;
            }else if(right < cards2.length && cards2[right].equals(target)){
                right++;
            }else{
                return "No";
            }
        }
        
        return "Yes";
    }
}