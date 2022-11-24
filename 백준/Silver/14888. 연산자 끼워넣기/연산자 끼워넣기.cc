#include <iostream>
using namespace std;
int n, myMin = 1000000001;
int myMax = -1000000001;
int numArr[11];
int operators[4];

void dfs(int result, int idx) {
    // idx가 n이되면 max값과 min값을 조정 후 종료
    if (idx == n) {
        if (result > myMax) {
            myMax = result;
        }
        if (result < myMin) {
            myMin = result;
        }
        return;
    }

        for (int i = 0; i < 4; i++) {
        // 해당 연산자가 사용 가능하면
        if (operators[i] > 0) {
            // 해당 연산자의 사용 개수를 줄이고 재귀
            operators[i]--;
            if (i == 0) {
                dfs(result + numArr[idx], idx + 1);
            } else if (i == 1) {
                dfs(result - numArr[idx], idx + 1);
            } else if (i == 2) {
                dfs(result * numArr[idx], idx + 1);
            } else {
                dfs(result / numArr[idx], idx + 1);
            }
            // 사용한 연산자의 개수를 증가
            operators[i]++;
        }
    }
    return;
}

int main() {
    // 1. 첫째 줄에 수의 개수 N(2 ≤ N ≤ 11)가 주어진다.
    cin >> n;
    // 2. 둘째 줄에는 A1, A2, ..., AN이 주어진다. (1 ≤ Ai ≤ 100)
    for (int i = 0; i < n; i++)
        cin >> numArr[i];
    // 3. 셋째 줄에는 합이 N-1인 4개의 정수가 주어지는데, 차례대로 덧셈(+)의 개수,
    //    뺄셈(-)의 개수, 곱셈(×)의 개수, 나눗셈(÷)의 개수이다.
    for (int i = 0; i < 4; i++)
        cin >> operators[i];

    dfs(numArr[0], 1);
    cout << myMax << '\n'
         << myMin;
}