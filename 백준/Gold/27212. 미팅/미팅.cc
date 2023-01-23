#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;
int W[17][17];
int A[1001];
int B[1001];
int N, M, C;
long long dp[1001][1001];

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    cin >> N >> M >> C;
    for (int i = 1; i <= C; i++) {
        for (int j = 1; j <= C; j++) {
            cin >> W[i][j];
        }
    }
    for (int i = 1; i <= N; i++) {
        cin >> A[i];
    }
    for (int i = 1; i <= M; i++) {
        cin >> B[i];
    }
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= M; j++) {
            dp[i][j] = max(max(dp[i - 1][j - 1] + W[A[i]][B[j]], dp[i - 1][j]), dp[i][j - 1]);
        }
    }

    cout << dp[N][M];
}