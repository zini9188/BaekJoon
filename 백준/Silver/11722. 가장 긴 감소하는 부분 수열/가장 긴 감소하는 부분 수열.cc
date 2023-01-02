#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

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