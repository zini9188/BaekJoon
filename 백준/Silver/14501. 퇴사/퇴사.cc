#include <iostream>
#include <vector>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int N;
    cin >> N;
    vector<pair<int, int>> inputs(16);
    vector<int> dp(16);
    for (int i = 1; i <= N; i++) {
        cin >> inputs[i].first >> inputs[i].second;
    }

    for (int i = 1; i <= N + 1; i++) {
        int nextDay = i + inputs[i].first;
        int earn = inputs[i].second;
        dp[i] = max(dp[i - 1], dp[i]);
        if (nextDay <= N + 1)
            dp[nextDay] = max(earn + dp[i], dp[nextDay]);
    }

    cout << dp[N + 1];
}

