#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int n, result = 0;
    vector<int> arr(1001);
    vector<int> dp(1001);
    cin >> n;
    for (int i = 0; i < n; i++) cin >> arr[i];
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < i; j++) {
            if (arr[i] > arr[j]) {
                dp[i] = max(dp[j], dp[i]);
            }
        }
        dp[i] += arr[i];
        result = max(result, dp[i]);
    }
    cout << result;
}