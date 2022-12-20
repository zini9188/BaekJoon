#include <string>
#include <vector>
#include <iostream>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int n, k;
    cin >> n >> k;

    vector<int> dp(10001);
    vector<int> coin(101);

    for (int i = 0; i < n; i++) {
        cin >> coin[i];
    }
    dp[0] = 1;

    for (int i = 1; i <= k; i++) {
        if (i % coin[0] == 0) dp[i] = dp[i - coin[0]];
    }

    for (int i = 1; i < n; i++) {
        int cnt = 0;
        for (int j = 1; j <= k; j++) {
            if (j >= coin[i]) cnt = dp[j - coin[i]];
            dp[j] += cnt;
        }
    }
    cout << dp[k];
}
