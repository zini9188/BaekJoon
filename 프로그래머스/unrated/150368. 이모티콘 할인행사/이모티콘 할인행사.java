import java.util.ArrayList;

class Solution {
    int[] discount = {10, 20, 30, 40};
    ArrayList<ArrayList<Integer>> discountCases = new ArrayList<>();
    ArrayList<int[]> answers = new ArrayList<>();

    public int[] solution(int[][] users, int[] emoticons) {
        int len = emoticons.length;
        dfs(0, len, new ArrayList<>());
        int[] answer = {0, 0};
        for (ArrayList<Integer> discountCase : discountCases) {
            int service = 0;
            int total = 0;
            for (int[] user : users) {
                int userRate = user[0];
                int userLimit = user[1];
                int sum = 0;
                for (int i = 0; i < discountCase.size(); i++) {
                    if (discountCase.get(i) >= userRate) {
                        sum += emoticons[i] * (100 - discountCase.get(i)) / 100;
                    }
                }
                if (sum >= userLimit) {
                    service++;
                } else {
                    total += sum;
                }
            }
            answers.add(new int[]{service, total});
        }
        answers.sort((o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o2[0] - o1[0]);
        return answers.get(0);
    }

    private void dfs(int index, int count, ArrayList<Integer> temp) {
        if (index == count) { // 설정된 할인 비율로 값 찾기
            discountCases.add((ArrayList<Integer>) temp.clone());
            return;
        }

        for (int i = 0; i < 4; i++) {
            temp.add(discount[i]);
            dfs(index + 1, count, temp);
            temp.remove(temp.size() - 1);
        }
    }
}