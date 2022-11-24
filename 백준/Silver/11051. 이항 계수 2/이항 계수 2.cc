#include <iostream>
using namespace std;

int dp[1001][1001] = {0};
int main() {
    int n;
    int k;
    cin >> n >> k;
    for (int i = 1; i <= n; i++) {
        for (int j = 0; j <= k; j++) {
            if (j == 0 || i == j)
                dp[i][j] = 1;
            else
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % 10007;
        }
    }

    cout << dp[n][k];
}