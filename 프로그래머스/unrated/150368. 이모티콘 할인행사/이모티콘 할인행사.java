import java.util.ArrayList;

class Solution {
    ArrayList<ArrayList<Integer>> discountCases = new ArrayList<>();
    ArrayList<int[]> answers = new ArrayList<>();

    public int[] solution(int[][] users, int[] emoticons) {
        findDiscountCases(0, emoticons.length, new ArrayList<>());
        for (ArrayList<Integer> discountCase : discountCases) {
            int service = 0;
            int total = 0;
            for (int[] user : users) {
                int wantDiscount = user[0];
                int limitPrice = user[1];
                int sum = 0;
                for (int i = 0; i < discountCase.size(); i++) {
                    int discount = discountCase.get(i);
                    if (discount >= wantDiscount) {
                        sum += emoticons[i] * (100 - discount) / 100;
                    }
                }
                if (sum >= limitPrice) {
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

    private void findDiscountCases(int index, int count, ArrayList<Integer> temp) {
        if (index == count) {
            discountCases.add((ArrayList<Integer>) temp.clone());
            return;
        }

        for (int i = 1; i <= 4; i++) {
            temp.add(i * 10);
            findDiscountCases(index + 1, count, temp);
            temp.remove(temp.size() - 1);
        }
    }
}