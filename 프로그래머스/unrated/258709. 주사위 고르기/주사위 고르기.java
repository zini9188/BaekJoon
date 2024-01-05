import java.util.*;

class Solution {
    int size; // 주사위 개수
    List<Integer> [] sumList; // 경우에 따른 주사위 값의 합
    int [] p; // NPT 조합 배열

    public int[] solution(int[][] dice) {
        size = dice.length;
        sumList = new List[2];
        sumList[0] = new ArrayList<>();
        sumList[1] = new ArrayList<>();

        p = new int[size];
        int cnt = 0;
        // size / 2 만큼 추첨
        while (++cnt <= size / 2) p[size - cnt] = 1;

        int[] answer = new int[size / 2];
        int maxWin = 0;

        do {
            // A: 0 B: 1 의 주사위 담당
            dfs(0, 0, 0, dice);
            dfs(0, 0, 1, dice);
            // B 주사위 경우 합을 정렬 (lowerBound를 사용하기 위해)
            Collections.sort(sumList[1]);

            int win = 0;
            for (Integer a : sumList[0]) {
                // A가 이기는 경우의 수 계산
                int lower = lowerBound(sumList[1], a);
                win += lower;
            }

            // A가 이기는 경우가 가장 큰 경우 구하기
            if (win > maxWin) {
                maxWin = win;
                int c = 0;
                for (int i=0; i<size; i++) {
                    if (p[i] == 0) {
                        answer[c++] = i+1;
                    }
                }
            }

            // 다음 경우를 위해서 초기화
            sumList[0].clear();
            sumList[1].clear();
        } while (permutation(p));

        return answer;
    }
    // target에 대한 lowerBound 즉 내가 이기는 경우
    private int lowerBound(List<Integer> data, int target) {
        int start = 0;
        int end = data.size();

        while (start < end) {
            int mid = (start + end) / 2;

            if (data.get(mid) >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return end;
    }
    // 주사위 수의 합 계산
    private void dfs(int depth, int sum, int select, int[][] dice) {
        if (depth >= size) {
            sumList[select].add(sum);
            return;
        }
        if (p[depth] != select) {
            dfs(depth + 1, sum, select, dice);
            return;
        }
        for (int i = 0; i < 6; i++) {
            dfs(depth + 1, sum + dice[depth][i], select, dice);
        }
    }
    // 조합
    private boolean permutation(int[] p) {
        int N = p.length;
        int i = N - 1;
        while (i > 0 && p[i - 1] >= p[i]) --i;

        if (i == 0) return false; // 다음 순열은 없음 (가장 큰 순열의 형태)

        int j = N - 1;
        while (p[i - 1] >= p[j]) --j;

        swap(p, i - 1, j);

        int k = N - 1;
        while (i < k) swap(p, i++, k--);
        return true;
    }

    private void swap(int[] p, int a, int b) {
        int tmp = p[a];
        p[a] = p[b];
        p[b] = tmp;
    }
}