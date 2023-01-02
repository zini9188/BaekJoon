#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;
// 해당 문제는 99퍼에서 틀렸는데
// ans를 0으로 초기화시킨게 문제였다/
// 입력이 하나 들어오는 경우 for문이 돌지 않아
// ans가 최대값으로 초기화가 되지 않아 값이 0이 나온다.
int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int n, ans = 1;
    cin >> n;

    vector<int> A(1001);
    for (int i = 1; i <= n; i++) {
        cin >> A[i];
    }

    vector<int> dp(1001, 1);
    for (int i = n - 1; i > 0; i--) {
        for (int j = n; j > i; j--) {
            if (A[i] > A[j]) {
                dp[i] = max(dp[i], dp[j] + 1);
            }
        }
        ans = max(dp[i], ans);
    }
    cout << ans;
}
