#include <iostream>
using namespace std;
int dp[31][31] = {0};

void DP() {
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
}

int main() {
    int t;
    cin >> t;
    int n, m;
    DP();
    for (int i = 0; i < t; i++) {
        cin >> n >> m;
        cout << dp[n][m] << '\n';
    }
}