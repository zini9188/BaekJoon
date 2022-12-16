#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;
vector<int> apeach(11);
vector<int> lion(11);
vector<int> answer(11);
int maxSub = 0;

void dfs(int index, int arrow, int lionScore, int apeachScore) {
    if (index > 10) { // 마지막 과녁 도착
        if (lionScore > apeachScore) { // 라이언의 점수가 더 크면
            lion[10] = arrow; // 남은 화살 0점에 몰아 넣기
            int sub = lionScore - apeachScore;
            if (sub > maxSub) { // 최대값 갱신이 가능한경우
                maxSub = lionScore - apeachScore;
                answer = lion;
            } else if (sub == maxSub) { // 최대값이 같은 경우
                for (int i = 10; i >= 0; i--) { // 더 작은 곳부터 비교하기
                    if (lion[i] == answer[i]) continue;
                    else { // 더 큰 작은 값이 있는 경우 정답 변경
                        if (lion[i] > answer[i]) {
                            answer = lion;
                        }
                        break;
                    }
                }
            }
        }
        return;
    }
    
    // 화살 맞추기가 가능하면
    if (arrow > apeach[index]) { 
        lion[index] = apeach[index] + 1;
        dfs(index + 1, arrow - apeach[index] - 1, lionScore + 10 - index, apeachScore);
        lion[index] = 0;
    }
    
    // 화살을 맞추지 않고 인덱스 증가
    int currentScore = apeach[index] > 0 ? 10 - index : 0;
    dfs(index + 1, arrow, lionScore, currentScore + apeachScore);
}


vector<int> solution(int n, vector<int> info) {
    apeach = info;
    dfs(0, n, 0, 0);
    if (maxSub == 0) return {-1};
    return answer;
}