#include <string>
#include <vector>
#include <iostream>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int n, k;
    vector<int> dp(10001);
    vector<int> coin(101);
    
    cin >> n >> k;    
    for (int i = 0; i < n; i++) cin >> coin[i];    
    dp[0] = 1;
    for (int i = 1; i <= k; i++) {
        if (i % coin[0] == 0) dp[i] = dp[i - coin[0]];
    }

    for (int i = 1; i < n; i++) {
        for (int j = coin[i]; j <= k; j++) {
            dp[j] += dp[j - coin[i]];
        }
    }
    cout << dp[k];
}