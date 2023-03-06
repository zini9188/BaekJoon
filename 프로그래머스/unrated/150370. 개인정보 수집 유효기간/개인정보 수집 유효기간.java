import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        // today = 오늘
        // terms = 파기해야할 개월 수
        // privacies = 등록된 날짜
        int todayToDays = transDateToDays(today);
        Map<String, Integer> termMap = new HashMap<>();
        for (String term : terms) {
            StringTokenizer tokenizer = new StringTokenizer(term);
            termMap.put(tokenizer.nextToken(), Integer.valueOf(tokenizer.nextToken()));
        }

        ArrayList<Integer> answerList = new ArrayList<>();
        for (int i = 0; i < privacies.length; i++) {
            StringTokenizer tokenizer = new StringTokenizer(privacies[i]);
            String date = tokenizer.nextToken();
            String kindOfTerm = tokenizer.nextToken();
            int days = transDateToDays(date);
            int afterDays = afterMonths(days, termMap.get(kindOfTerm));
            if (todayToDays >= afterDays) {
                answerList.add(i + 1);
            }
        }
        return answerList.stream().mapToInt(a -> a).toArray();
    }

    private int transDateToDays(String date) {
        StringTokenizer tokenizer = new StringTokenizer(date, ".");
        int year = Integer.parseInt(tokenizer.nextToken()) - 2000;
        int month = Integer.parseInt(tokenizer.nextToken());
        int day = Integer.parseInt(tokenizer.nextToken());
        return (year * 336) + (month * 28) + day;
    }

    private int afterMonths(int days, int months) {
        return days + (months * 28);
    }
}