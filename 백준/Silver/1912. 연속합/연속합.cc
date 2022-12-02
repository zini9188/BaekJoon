#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
#define MAX_SIZE 100001

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int N;
    cin >> N;
    vector<int> v(MAX_SIZE);
    for (int i = 0; i < N; i++) {
        cin >> v[i];
    }
    vector<int> dp(MAX_SIZE);
    dp[0] = v[0];
    int answer = dp[0];
    for (int i = 1; i < N; i++) {
        dp[i] = max(dp[i - 1] + v[i], v[i]);
        answer = max(answer, dp[i]);
    }
    cout << answer;
}
