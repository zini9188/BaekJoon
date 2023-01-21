#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int n, result = 1;
    cin >> n;
    vector<int> stones(100001);
    vector<vector<int>> dp(1000001, vector<int>(2, 0));
    for (int i = 0; i < n; i++) {
        cin >> stones[i];
    }
    if (stones[0] == 1) {
        dp[0][0] = 1;
    } else {
        dp[0][1] = 1;
    }
    for (int i = 1; i < n; i++) {
        if (stones[i] == 1) {
            dp[i][0] = dp[i - 1][0] + 1;
            dp[i][1] = dp[i - 1][1] - 1 >= 0 ? dp[i - 1][1] - 1 : 0;
            result = max(max(abs(dp[i][0] - dp[i][1]), dp[i][0]), result);
        } else {
            dp[i][1] = dp[i - 1][1] + 1;
            dp[i][0] = dp[i - 1][0] - 1 >= 0 ? dp[i - 1][0] - 1 : 0;
            result = max(max(abs(dp[i][0] - dp[i][1]), dp[i][1]), result);
        }
    }
    cout << result;
}