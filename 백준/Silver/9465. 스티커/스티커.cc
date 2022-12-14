#include <string>
#include <vector>
#include <iostream>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int T, N;

    cin >> T;

    for (int i = 0; i < T; i++) {
        cin >> N;
        vector<vector<int>> sticker(100001, vector<int>(2));
        vector<vector<int>> dp(100001, vector<int>(2));
        for (int k = 0; k < 2; k++) {
            for (int j = 1; j <= N; j++) {
                cin >> sticker[j][k];
            }
        }

        dp[1][0] = sticker[1][0];
        dp[1][1] = sticker[1][1];
        dp[2][0] = sticker[1][1] + sticker[2][0];
        dp[2][1] = sticker[1][0] + sticker[2][1];

        for (int j = 3; j <= N; j++) {
            dp[j][0] = sticker[j][0] + max(max(dp[j - 2][0], dp[j - 2][1]), dp[j - 1][1]);
            dp[j][1] = sticker[j][1] + max(max(dp[j - 2][0], dp[j - 2][1]), dp[j - 1][0]);
        }

        cout << max(dp[N][0], dp[N][1]) << "\n";
    }
}