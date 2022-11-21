#include <iostream>
#include <vector>

using namespace std;

int main() {
    int t;
    cin >> t;

    int n, m;
    vector<vector<int>> dp(31, vector<int>(31, 0));
    for (int i = 1; i <= 30; i++) {
        dp[1][i] = i;
    }

    for (int i = 2; i <= 30; i++) {
        for (int j = i; j <= 30; j++) {
            if (j == i)
                dp[i][j] = 1;
            else
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1];
        }
    }

    for (int i = 0; i < t; i++) {
        cin >> n >> m;
        cout << dp[n][m] << '\n';
    }
}