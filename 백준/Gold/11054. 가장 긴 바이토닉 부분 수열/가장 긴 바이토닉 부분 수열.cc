#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int N, answer = 0;
    vector<int> array(1001), dp(1001), rdp(1001);

    cin >> N;
    for (int i = 0; i < N; i++)
        cin >> array[i];

    fill(dp.begin(), dp.begin() + N, 1);
    fill(rdp.begin(), rdp.begin() + N, 1);
    
    for (int i = 1; i < N; i++) {
        for (int j = i - 1; j >= 0; j--) {
            if (array[i] > array[j]) dp[i] = max(dp[j] + 1, dp[i]);
        }
    }
    
    for (int i = N - 1; i >= 0; i--) {
        for (int j = N - 1; j > i; j--) {
            if (array[i] > array[j])rdp[i] = max(rdp[j] + 1, rdp[i]);
        }
    }
    
    for (int i = 0; i < N; i++) {
        answer = max(dp[i] + rdp[i] - 1, answer);
    }

    cout << answer;
}