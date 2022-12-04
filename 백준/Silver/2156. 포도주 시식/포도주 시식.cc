#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
int dp[10001];

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int N;
    cin >> N;
    vector<int> wine(10001);
    for (int i = 1; i <= N; i++) cin >> wine[i];
    // 1. 해당 번호의 포도주를 마시는 경우
    // 1-1. 앞 번호의 포도주를 먹는 경우 -> dp[n - 3] + wine[n - 1] + wine[n]
    // 1-2. 앞 번호의 포도주를 먹지 않는 경우 -> dp[n - 2] + wine[n]
    // 2. 해당 번호의 포도주를 먹지 않는 경우 -> dp[n - 1]
    dp[1] = wine[1]; // 첫잔까지 최대 값은 첫잔만 먹는 것
    dp[2] = dp[1] + wine[2]; // 두번째 잔까지 최대 값은 둘다 먹는 것
    for (int i = 3; i <= N; i++) {
        dp[i] = max(dp[i - 1], max(dp[i - 2] + wine[i], dp[i - 3] + wine[i - 1] + wine[i]));
    }
    cout << dp[N];
}