#include <string>
#include <vector>
#include <iostream>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int n;
    cin >> n;
    vector<vector<int>> dp(1001, vector<int>(1001));
    for (int i = 0; i <= 9; i++) {
        dp[0][i] = 1;
    }
    for (int i = 1; i <= n; i++) {
        for (int j = 0; j <= 9; j++) {
            if (j == 0) {
                dp[i][0] = 1;
                continue;
            }
            dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            dp[i][j] %= 10007;
        }
    }

    cout << dp[n][9] % 10007;
}