#include <string>
#include <vector>
#include <iostream>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int N;
    vector<int> cards(10001);
    vector<int> dp(100001);
    cin >> N;

    for (int i = 1; i <= N; i++) {
        cin >> cards[i];
        dp[i] = cards[i];
    }

    for (int i = 1; i <= N; i++) {
        for(int j = 1; j < i; j++){
            dp[i] = max(dp[i], dp[i - j] + dp[j]);
        }
    }

    cout << dp[N];
}