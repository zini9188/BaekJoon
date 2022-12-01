#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

string A, B;
int dp[1001][1001];

void input() {
    cin >> A >> B;
}

int solution() {

    for (int i = 1; i <= A.length(); i++) {
        for (int j = 1; j <= B.length(); j++) {
            if (A[i - 1] == B[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1] + 1;
            } else {
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }

    return dp[A.length()][B.length()];
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    input();
    cout << solution();
}
