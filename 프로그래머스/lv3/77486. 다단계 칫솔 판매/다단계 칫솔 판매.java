import java.util.HashMap;
import java.util.Map;

class Solution {
    Map<String, String> referralMap = new HashMap<>();
    Map<String, Integer> profits = new HashMap<>();

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        for (int i = 0; i < referral.length; i++) {
            referralMap.put(enroll[i], referral[i]);
            profits.put(enroll[i], 0);
        }
        for (int i = 0; i < seller.length; i++) {
            shareProfit(amount[i] * 100, seller[i]);
        }
        int[] answer = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = profits.get(enroll[i]);
        }

        return answer;
    }

    private void shareProfit(double profit, String seller) {
        if (seller.equals("-")) {
            return;
        }
        int nextProfit = (int) (profit / 10);
        if(nextProfit == 0){
            profits.put(seller, profits.get(seller) + (int) profit);
            return;
        }
        profits.put(seller, profits.get(seller) + (int) (profit - nextProfit));
        shareProfit(nextProfit, referralMap.get(seller));
    }
}