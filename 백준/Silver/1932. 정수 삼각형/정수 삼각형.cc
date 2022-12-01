#include <iostream>
#include <queue>

using namespace std;
int dp[501][501];

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int N;
    cin >> N;
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= i; j++) {
            cin >> dp[i][j];
        }
    }
    int answer = dp[1][1];
    for (int i = 2; i <= N; i++) {
        for (int j = 1; j <= i; j++) {
            if (j == 1) {
                dp[i][j] += dp[i - 1][j];
            } else if (j == i) {
                dp[i][j] += dp[i - 1][j - 1];
            } else {
                dp[i][j] += max(dp[i - 1][j - 1], dp[i - 1][j]);
            }
            answer = max(dp[i][j], answer);
        }
    }
    cout << answer;
}
